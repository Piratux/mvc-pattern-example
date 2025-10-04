package com.example.demo.service.impl;


import com.example.demo.mapper.CarMapper;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import com.example.demo.view.CarView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarView> getAllCars() {
        return carRepository.findAll().stream().map(carMapper::entityToView).toList();
    }

    public CarView saveCar(CarView car) {
        return carMapper.entityToView(carRepository.save(carMapper.viewToEntity(car)));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public CarView updateCar(Long id, CarView car) {
        car.setId(id);
        return carMapper.entityToView(carRepository.save(carMapper.viewToEntity(car)));
    }
}