package com.gure.cinab.dto;

import com.gure.cinab.model.Category;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {

    private long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageDTO> images;
}
