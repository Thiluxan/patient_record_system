package com.project.patient.service;

import com.project.patient.modal.Patient;
import com.project.patient.repository.PatientRepository;
import com.project.patient.request.PatientRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private PatientRequest buildRequest() {
        PatientRequest req = new PatientRequest();
        req.setFirstName("John");
        req.setLastName("Doe");
        req.setAddress("123 Main St");
        req.setCity("Colombo");
        req.setState("Western");
        req.setZipCode("10000");
        req.setPhoneNumber("0771234567");
        req.setEmail("john.doe@example.com");
        return req;
    }

    private Patient buildEntity(Long id) {
        Patient p = new Patient();
        p.setId(id);
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setAddress("123 Main St");
        p.setCity("Colombo");
        p.setState("Western");
        p.setZipCode("10000");
        p.setPhoneNumber("0771234567");
        p.setEmail("john.doe@example.com");
        return p;
    }

    @Test
    void create_shouldSaveAndReturnPatient() {
        PatientRequest request = buildRequest();
        Patient saved = buildEntity(1L);

        when(patientRepository.save(any(Patient.class))).thenReturn(saved);

        Patient result = patientService.create(request);

        assertNotNull(result.getId());
        assertEquals("John", result.getFirstName());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void findById_shouldReturnPatient_whenExists() {
        Patient existing = buildEntity(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(existing));

        Patient result = patientService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
    }

    @Test
    void findById_shouldThrow_whenNotExists() {
        when(patientRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> patientService.findById(999L));
    }

    @Test
    void update_shouldUpdateAndReturnPatient() {
        Patient existing = buildEntity(1L);
        PatientRequest request = buildRequest();
        request.setFirstName("UpdatedName");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Patient result = patientService.update(1L, request);

        assertEquals("UpdatedName", result.getFirstName());
        verify(patientRepository, times(1)).save(existing);
    }

    @Test
    void findAll_shouldReturnList() {
        when(patientRepository.findAll()).thenReturn(List.of(buildEntity(1L), buildEntity(2L)));

        List<Patient> result = patientService.findAll();

        assertEquals(2, result.size());
        verify(patientRepository, times(1)).findAll();
    }
}
