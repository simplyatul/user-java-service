package com.someorg.controller;

import com.someorg.model.Education;
import com.someorg.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/someorg/v1")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping("/user/{email_id}/education")
    public ResponseEntity<?> addEducation(@PathVariable("email_id") String emailId,
                                          @RequestBody Education education) {
        educationService.addEducation(emailId, education);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{email_id}/education")
    public ResponseEntity<?> getEducations(@PathVariable("email_id") String emailId) {
        List<Education> educations = educationService.getEducationByUser(emailId);
        return ResponseEntity.ok().body(Map.of("education", educations));
    }
}
