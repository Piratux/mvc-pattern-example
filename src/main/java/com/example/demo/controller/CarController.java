package com.example.demo.controller;


import com.example.demo.service.CarService;
import com.example.demo.view.CarView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public List<CarView> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/cars")
    public CarView addCar(@RequestBody CarView car) {
        return carService.saveCar(car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @PutMapping("/cars/{id}")
    public CarView updateCar(@PathVariable Long id, @RequestBody CarView car) {
        return carService.updateCar(id, car);
    }
}