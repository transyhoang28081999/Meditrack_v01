package com.example.backend.securities.auth;

import com.example.backend.enums.UserProfileEnum;
import com.example.backend.models.UserProfile;
import com.example.backend.repositories.UserProfileRespository;
import com.example.backend.securities.user.User;
import com.example.backend.securities.user.UserRepository;
import com.example.backend.securities.user.UserService;
import com.example.backend.services.interfaces.MedicalRecordService;
import com.example.backend.services.interfaces.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserRepository userRepository;
    private final UserProfileService userProfileService;
    private final UserProfileRespository userProfileRespository;
    private final MedicalRecordService medicalRecordService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        String message = service.register(request);
        //Create user profile
        User user = userRepository.findByUserEmail(request.getUserEmail()).orElseThrow();
        userProfileService.createUserProfile(user);

        //Create medical record
        UserProfile userProfile = userProfileRespository.findSingleByUserID(user.getUserID(), UserProfileEnum.MAIN).orElseThrow();
        medicalRecordService.createMedicalRecord(userProfile);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
