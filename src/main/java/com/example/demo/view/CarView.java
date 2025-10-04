package com.example.demo.view;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarView {
    private long id;
    private String name;
    private String year;
    private String fuel;
    private long price;
}
