package com.example.challenge.controllers;

import com.example.challenge.model.Vehicle;
import com.example.challenge.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getVehicles(){
        return vehicleService.findAll();
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.create(vehicle);
    }

    @DeleteMapping(path = "{vehicleId}")
    public void deleteVehicle(@PathVariable("vehicleId") int id){
        vehicleService.deleteById(id);
    }

    @PutMapping
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.update(vehicle);
    }
}
