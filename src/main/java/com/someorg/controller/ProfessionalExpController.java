package com.someorg.controller;

import com.someorg.model.ProfessionalExperience;
import com.someorg.service.ProfessionalExpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/someorg/v1")
@RequiredArgsConstructor
public class ProfessionalExpController {

    private final ProfessionalExpService professionalExpService;

    @PostMapping("/user/{email_id}/professionalexp")
    public ResponseEntity<?> addExperience(@PathVariable("email_id") String emailId,
                                           @RequestBody ProfessionalExperience exp) {
        professionalExpService.addExperience(emailId, exp);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{email_id}/professionalexp")
    public ResponseEntity<?> getExperiences(@PathVariable("email_id") String emailId) {
        List<ProfessionalExperience> experiences = professionalExpService.getExperienceByUser(emailId);
        return ResponseEntity.ok().body(Map.of("professionalexp", experiences));
    }
}
