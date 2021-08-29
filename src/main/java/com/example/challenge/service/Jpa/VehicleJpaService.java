package com.example.challenge.service.Jpa;

import com.example.challenge.model.Vehicle;
import com.example.challenge.repository.VehicleRepository;
import com.example.challenge.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleJpaService implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleJpaService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle create(Vehicle object) {
        return vehicleRepository.save(object);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(Integer integer) {
        return vehicleRepository.findById(integer).orElse(null);
    }

    @Override
    public Vehicle update(Vehicle object) {
        return vehicleRepository.save(object);
    }

    @Override
    public void deleteById(Integer integer) {
        vehicleRepository.deleteById(integer);
    }
}
