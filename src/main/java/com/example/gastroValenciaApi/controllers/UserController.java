package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.dtos.UserDTO;
import com.example.gastroValenciaApi.services.UserService;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO dto, HttpServletRequest request) {
        FirebaseToken firebaseToken = (FirebaseToken) request.getAttribute("firebaseUser");

        if (firebaseToken == null) {
            return ResponseEntity.status(401).build();
        }

        // Sobrescribimos email y UID desde el token verificado
        dto.setEmail(firebaseToken.getEmail());
        dto.setFirebaseUid(firebaseToken.getUid());

        UserDTO saved = userService.saveUser(dto);
        return ResponseEntity.ok(saved);
    }

    //Modificar usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        dto.setId(id); // aseguramos que el ID del path se use
        UserDTO updated = userService.updateUser(dto);
        return ResponseEntity.ok(updated);
    }

//Borrar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            String message = userService.deleteUser(id);
            return ResponseEntity.ok(Map.of("message", message));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/auth/verify")
    public ResponseEntity<?> verifyAndSaveUser(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            UserDTO user = userService.authenticateAndSaveIfNotExists(token);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "El token no es válido o ha expirado."));
        }
    }

    @GetMapping("/firebase/{firebaseUid}")
    public ResponseEntity<UserDTO> getByFirebaseUid(@PathVariable String firebaseUid) {
        return userService.getByFirebaseUid(firebaseUid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO dto) {
        UserDTO saved = userService.registerUserWithoutAuth(dto);
        return ResponseEntity.ok(saved);
    }

}