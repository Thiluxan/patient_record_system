package com.project.patient.controller;

import com.project.patient.modal.Patient;
import com.project.patient.request.PatientRequest;
import com.project.patient.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    private Patient buildEntity(Long id) {
        Patient p = new Patient();
        p.setId(id);
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setEmail("john.doe@example.com");
        return p;
    }

    private PatientRequest buildRequest() {
        PatientRequest req = new PatientRequest();
        req.setFirstName("John");
        req.setLastName("Doe");
        req.setEmail("john.doe@example.com");
        return req;
    }

    @Test
    void getAllPatients_shouldReturnList() {
        when(patientService.findAll()).thenReturn(List.of(buildEntity(1L)));

        List<Patient> result = patientController.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void createPatient_shouldReturnCreatedPatient() {
        PatientRequest request = buildRequest();
        Patient saved = buildEntity(1L);

        when(patientService.create(any(PatientRequest.class))).thenReturn(saved);

        Patient result = patientController.create(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
    }

    @Test
    void getPatientById_shouldThrow_whenMissing() {
        when(patientService.findById(999L))
                .thenThrow(new EntityNotFoundException("Patient not found"));

        assertThrows(
                EntityNotFoundException.class,
                () -> patientController.getById(999L)
        );
    }

    @Test
    void getPatientById_shouldReturnPatient_whenExists() {
        Patient existing = buildEntity(1L);
        when(patientService.findById(1L)).thenReturn(existing);

        Patient result = patientController.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
    }

    @Test
    void updatePatient_shouldReturnUpdatedPatient() {
        PatientRequest request = buildRequest();
        request.setFirstName("Updated");

        Patient updated = buildEntity(1L);
        updated.setFirstName("Updated");

        when(patientService.update(any(Long.class), any(PatientRequest.class)))
                .thenReturn(updated);

        Patient result = patientController.update(1L, request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated", result.getFirstName());
    }

    @Test
    void deletePatient_shouldCallServiceDelete() {
        patientController.delete(1L);

        verify(patientService, times(1)).delete(1L);
    }
}
