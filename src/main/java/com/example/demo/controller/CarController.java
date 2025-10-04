package com.example.demo.controller;


import com.example.demo.mapper.CarMapper;
import com.example.demo.repository.CarRepository;
import com.example.demo.view.CarView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @GetMapping("/cars")
    public List<CarView> getAllCars() {
        return carRepository.findAll().stream().map(carMapper::entityToView).toList();
    }

    @PostMapping("/cars")
    public CarView addCar(@RequestBody CarView car) {
        return carMapper.entityToView(carRepository.save(carMapper.viewToEntity(car)));
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    @PutMapping("/cars/{id}")
    public CarView updateCar(@PathVariable Long id, @RequestBody CarView car) {
        car.setId(id);
        return carMapper.entityToView(carRepository.save(carMapper.viewToEntity(car)));
    }
}