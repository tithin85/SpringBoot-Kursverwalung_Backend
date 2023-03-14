package com.example.gruppebjava.application.rest;

import com.example.gruppebjava.core.domain.ERole;
import com.example.gruppebjava.core.domain.Role;
import com.example.gruppebjava.core.domain.User;
import com.example.gruppebjava.payload.request.LoginRequest;
import com.example.gruppebjava.payload.request.SignupRequest;
import com.example.gruppebjava.payload.response.JwtResponse;
import com.example.gruppebjava.payload.response.MessageResponse;
import com.example.gruppebjava.core.repo.RoleRepo;
import com.example.gruppebjava.core.repo.UserRepo;
import com.example.gruppebjava.security.jwt.JwtUtils;
import com.example.gruppebjava.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepo userRepo;

  @Autowired
  RoleRepo roleRepo;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @GetMapping("/count")
  public long getUserCount() {
    System.out.println("User-Count: " + userRepo.count());
    return userRepo.count();
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepo.count() < 1) {
      if (userRepo.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Fehler: Der Username ist bereits vergeben!"));
      }

      if (userRepo.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Fehler: Die Email wird bereits genutzt!"));
      }
      // Create new user's account
      User user = new User(signUpRequest.getUsername(),
              signUpRequest.getEmail(),
              encoder.encode(signUpRequest.getPassword()));

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      if (strRoles == null) {
        Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fehler: Die Rolle wurde nicht gefunden."));
        roles.add(userRole);
      } else {
        strRoles.forEach(role -> {
          switch (role) {
            case "admin":
              Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                      .orElseThrow(() -> new RuntimeException("Fehler: Die Rolle wurde nicht gefunden."));
              roles.add(adminRole);

              break;
            case "mod":
              Role modRole = roleRepo.findByName(ERole.ROLE_MODERATOR)
                      .orElseThrow(() -> new RuntimeException("Fehler: Die Rolle wurde nicht gefunden."));
              roles.add(modRole);

              break;
            default:
              Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                      .orElseThrow(() -> new RuntimeException("Fehler: Die Rolle wurde nicht gefunden."));
              roles.add(userRole);
          }
        });
      }

      user.setRoles(roles);
      userRepo.save(user);

      return ResponseEntity.ok(new MessageResponse("Die Registrierung war erfolgreich!"));
    } else {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Es wurde bereits ein User eingerichtet!"));
    }
  }
}
