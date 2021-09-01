package com.example.challenge.controllers;

import com.example.challenge.model.Vehicle;
import com.example.challenge.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles(){
        return ResponseEntity.ok().body(vehicleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehiclesById(@PathVariable("id") int id){
        return ResponseEntity.of(vehicleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle){
        Vehicle createdVehicle = vehicleService.create(vehicle);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVehicle.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdVehicle);
    }

    @DeleteMapping(path = "{vehicleId}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("vehicleId") int id){
        vehicleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle){
        return ResponseEntity.of(vehicleService.update(vehicle));
    }
}
