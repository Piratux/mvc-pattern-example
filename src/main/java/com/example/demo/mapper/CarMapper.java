package com.example.demo.mapper;

import com.example.demo.entity.CarEntity;
import com.example.demo.view.CarView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "year", source = "entity.makeYear")
    CarView entityToView(CarEntity entity);

    @Mapping(target = "makeYear", source = "view.year")
    CarEntity viewToEntity(CarView view);
}


