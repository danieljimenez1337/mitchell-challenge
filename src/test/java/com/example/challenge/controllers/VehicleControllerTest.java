package com.example.challenge.controllers;

import com.example.challenge.model.Vehicle;
import com.example.challenge.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

    @Mock
    VehicleService vehicleService;

    @InjectMocks
    VehicleController controller;

    List<Vehicle> vehicleList;
    MockMvc mockMvc;

    final String PATH = "/api/v1/vehicle";

    @BeforeEach
    void setUp() {
        vehicleList = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle(2000, "Toyota", "Corolla");
        vehicle1.setId(1);
        vehicleList.add(vehicle1);

        Vehicle vehicle2 = new Vehicle(2000, "Toyota", "Corolla");
        vehicle2.setId(2);
        vehicleList.add(vehicle2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void getVehicles() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vehicleList);

        when(vehicleService.findAll()).thenReturn(vehicleList);

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonString));
        verify(vehicleService).findAll();
    }

    @Test
    void testGetVehiclesById() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vehicleList.get(0));

        when(vehicleService.findById(1)).thenReturn(Optional.of(vehicleList.get(0)));

        mockMvc.perform(get(PATH + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonString));

        verify(vehicleService).findById(1);
    }

    @Test
    void testGetVehiclesByIdNotFound() throws Exception {

        when(vehicleService.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get(PATH + "/1"))
                .andExpect(status().isNotFound());

        verify(vehicleService).findById(1);
    }

    @Test
    void createVehicle() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vehicleList.get(0));

        when(vehicleService.create(any())).thenReturn(vehicleList.get(0));

        mockMvc.perform(post(PATH)
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonString));

        verify(vehicleService).create(any());
    }

    @Test
    void createVehicleInvalidBody() throws Exception {
        String jsonString = "{}";

        mockMvc.perform(post(PATH)
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

        verify(vehicleService, times(0)).create(any());
    }

    @Test
    void deleteVehicle() throws Exception {
        mockMvc.perform(delete(PATH + "/1"))
                .andExpect(status().isNoContent());
        verify(vehicleService).deleteById(any());
    }
    @Test
    void deleteVehicleInvalidPathType() throws Exception {
        mockMvc.perform(delete(PATH + "/test"))
                .andExpect(status().isBadRequest());

        verify(vehicleService, times(0)).deleteById(any());
    }

    @Test
    void updateVehicle() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vehicleList.get(0));

        when(vehicleService.update(any())).thenReturn(Optional.of(vehicleList.get(0)));

        mockMvc.perform(put(PATH)
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonString));

        verify(vehicleService).update(any());
    }

    @Test
    void updateVehicleInvalidBody() throws Exception {
        String jsonString = "{}";

        mockMvc.perform(put(PATH)
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest());

        verify(vehicleService, times(0)).update(any());
    }
}