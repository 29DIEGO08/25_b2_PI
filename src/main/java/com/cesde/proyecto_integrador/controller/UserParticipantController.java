package com.cesde.proyecto_integrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.cesde.proyecto_integrador.dto.ProfileDTO;
import com.cesde.proyecto_integrador.model.User;
import com.cesde.proyecto_integrador.service.UserParticipantService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "participant User", description = "APIs for managing student profile information")
@RestController
@RequestMapping("/api/participant")
public class UserParticipantController {

    @Autowired
    private UserParticipantService userParticipantService;

    // Profile
    @Operation(summary = "Get participant profile", description = "Retrieves the authenticated participant's profile information")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved profile")
    @ApiResponse(responseCode = "401", description = "Unauthorized - authentication required")
    @ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO>  getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userParticipantService.getProfile(user.getId())) ;
    }

    @Operation(summary = "Update participant profile", description = "Updates the authenticated participant's profile information")
    @ApiResponse(responseCode = "200", description = "Profile updated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - authentication required")
    @ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@AuthenticationPrincipal User user, @RequestBody ProfileDTO profile) {
        userParticipantService.updateProfile(user.getId(), profile);
        return ResponseEntity.ok("Profile updated successfully");
    }
}
