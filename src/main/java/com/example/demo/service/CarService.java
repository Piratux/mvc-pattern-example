package com.example.demo.service;

import com.example.demo.view.CarView;

import java.util.List;

public interface CarService {
    List<CarView> getAllCars();
    CarView saveCar(CarView car);
    void deleteCar(Long id);
    CarView updateCar(Long id, CarView car);
}
