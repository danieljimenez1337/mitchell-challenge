package com.example.challenge.service.Jpa;

import com.example.challenge.model.Vehicle;
import com.example.challenge.repository.VehicleRepository;
import com.example.challenge.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleJpaService implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleJpaService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle create(Vehicle object) {

        //removes id
        Vehicle vehicle = new Vehicle(
                object.getYear(),
                object.getMake(),
                object.getModel()
        );
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findById(Integer integer) {
        return vehicleRepository.findById(integer);
    }

    @Override
    public Optional<Vehicle> update(Vehicle object) {
        return vehicleRepository.findById(object.getId())
                .map(old -> vehicleRepository.save(object));
    }

    @Override
    public void deleteById(Integer integer) {
        if (vehicleRepository.findById(integer).isPresent()){
            vehicleRepository.deleteById(integer);
        }
    }
}
