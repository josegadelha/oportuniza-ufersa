package br.edu.ufersa.oportuniza.api.controllers;

import br.edu.ufersa.oportuniza.api.dtos.StudentCreate;
import br.edu.ufersa.oportuniza.api.dtos.StudentPatch;
import br.edu.ufersa.oportuniza.api.dtos.StudentResponse;
import br.edu.ufersa.oportuniza.api.dtos.StudentUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

    @GetMapping
    public ResponseEntity<List<StudentResponse>> list() {
        return null;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> findById(
            @PathVariable Long studentId
    ) {
        return null;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(
            @RequestBody @Valid StudentCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentResponse> partialUpdate(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long studentId
    ) {
        return null;
    }
}