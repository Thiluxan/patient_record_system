package com.project.patient.controller;

import com.project.patient.modal.Patient;
import com.project.patient.request.PatientRequest;
import com.project.patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @Operation(summary = "Get all patients")
    @GetMapping
    public List<Patient> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get a patient by ID")
    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create a new patient")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@Valid @RequestBody PatientRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Update a patient")
    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @Valid @RequestBody PatientRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Delete a patient")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

