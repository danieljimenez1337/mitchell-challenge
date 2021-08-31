package com.example.challenge.service.Jpa;

import com.example.challenge.model.Vehicle;
import com.example.challenge.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleJpaServiceTest {

    @InjectMocks
    VehicleJpaService service;

    @Mock
    VehicleRepository repository;

    Vehicle testVehicle;

    @BeforeEach
    void setUp() {
        testVehicle = new Vehicle(2000, "Toyota", "Corolla");
    }

    @Test
    void create() {
        when(repository.save(any())).thenReturn(testVehicle);

        Vehicle savedVehicle = repository.save(testVehicle);

        assertEquals(savedVehicle.getYear(), testVehicle.getYear());
        assertEquals(savedVehicle.getModel(), testVehicle.getModel());
        assertEquals(savedVehicle.getMake(), testVehicle.getMake());
        verify(repository).save(any());
    }

    @Test
    void findAll() {
        List<Vehicle> data = new ArrayList<>();
        data.add(new Vehicle());
        data.add(new Vehicle());
        when(repository.findAll()).thenReturn(data);

        List<Vehicle> vehicleList = service.findAll();

        assertNotNull(vehicleList);
        assertEquals(vehicleList.size(), 2);
        verify(repository).findAll();
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(Optional.of(testVehicle));

        Optional<Vehicle> vehicle = service.findById(1);

        assertTrue(vehicle.isPresent());
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Optional<Vehicle> vehicle = service.findById(1);

        assertFalse(vehicle.isPresent());
    }

    @Test
    void update() {
        testVehicle.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(testVehicle));
        when(repository.save(any())).thenReturn(testVehicle);

        Optional<Vehicle> vehicle = service.update(testVehicle);

        assertTrue(vehicle.isPresent());
        verify(repository).save(any());
    }

    @Test
    void updateDoesNotExist() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        Optional<Vehicle> vehicle = service.update(testVehicle);

        assertFalse(vehicle.isPresent());
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        when(repository.findById(1)).thenReturn(Optional.of(testVehicle));
        service.deleteById(1);

        verify(repository).deleteById(any());
    }

    @Test
    void deleteByIdDoesNotExist() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        service.deleteById(1);

        verify(repository, times(0)).deleteById(any());
    }
}