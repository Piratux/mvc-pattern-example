package com.example.demo;

import com.example.demo.service.CarService;
import com.example.demo.view.CarView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private CarService service;

    CarView createCar() {
        return CarView.builder()
                .id(1L)
                .name("Toyota Corolla")
                .year("2022")
                .fuel("Petrol")
                .price(20000L)
                .build();
    }

    @Test
    void testGetAllCars() throws Exception {
        CarView car = createCar();
        List<CarView> cars = List.of(car);
        Mockito.when(service.getAllCars()).thenReturn(cars);

        mvc.perform(get("/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Toyota Corolla")));
    }

    @Test
    void testSaveCar() throws Exception {
        CarView car = createCar();
        Mockito.when(service.saveCar(car)).thenReturn(car);

        String carJson = """
                {
                    "name": "abc",
                    "year": "2020",
                    "fuel": "Petrol",
                    "price": 5000
                }""";
        mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCar() throws Exception {
        Mockito.doNothing().when(service).deleteCar(5L);
        mvc.perform(delete("/cars/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCar() throws Exception {
        CarView car = createCar();
        CarView carUpdated = createCar();
        carUpdated.setName("updated");
        Mockito.when(service.updateCar(5L, car)).thenReturn(carUpdated);

        String carJson = """
                {
                    "name": "updated",
                    "year": "2020",
                    "fuel": "Petrol",
                    "price": 5000
                }""";
        mvc.perform(put("/cars/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk());
    }

}
