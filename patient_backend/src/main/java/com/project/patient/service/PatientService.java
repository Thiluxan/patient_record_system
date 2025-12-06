package com.project.patient.service;

import com.project.patient.modal.Patient;
import com.project.patient.repository.PatientRepository;
import com.project.patient.request.PatientRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> findAll() {
        return repository.findAll();
    }

    public Patient findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }

    public Patient create(PatientRequest request) {
        Patient patient = mapToEntity(request);
        return repository.save(patient);
    }

    public Patient update(Long id, PatientRequest request) {
        Patient existingRecord = findById(id);
        existingRecord.setFirstName(request.getFirstName());
        existingRecord.setLastName(request.getLastName());
        existingRecord.setAddress(request.getAddress());
        existingRecord.setCity(request.getCity());
        existingRecord.setState(request.getState());
        existingRecord.setZipCode(request.getZipCode());
        existingRecord.setPhoneNumber(request.getPhoneNumber());
        existingRecord.setEmail(request.getEmail());
        return repository.save(existingRecord);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Patient not found: " + id);
        }
        repository.deleteById(id);
    }

    private Patient mapToEntity(PatientRequest request) {
        Patient p = new Patient();
        p.setFirstName(request.getFirstName());
        p.setLastName(request.getLastName());
        p.setAddress(request.getAddress());
        p.setCity(request.getCity());
        p.setState(request.getState());
        p.setZipCode(request.getZipCode());
        p.setPhoneNumber(request.getPhoneNumber());
        p.setEmail(request.getEmail());
        return p;
    }
}
