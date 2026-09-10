package br.edu.ufersa.oportuniza.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ufersa.oportuniza.api.dtos.StudentCreate;
import br.edu.ufersa.oportuniza.api.dtos.StudentPatch;
import br.edu.ufersa.oportuniza.api.dtos.StudentResponse;
import br.edu.ufersa.oportuniza.api.dtos.StudentUpdate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class StudentController {

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> list() {
        return null;
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentResponse> findById(
            @PathVariable Long studentId
    ) {
        return null;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponse> create(
            @RequestBody @Valid StudentCreate dto,
            UriComponentsBuilder uriBuilder
    ) {
        return null;
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentUpdate dto
    ) {
        return null;
    }

    @PatchMapping("/students/{studentId}")
    public ResponseEntity<StudentResponse> partialUpdate(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentPatch dto
    ) {
        return null;
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long studentId
    ) {
        return null;
    }
}