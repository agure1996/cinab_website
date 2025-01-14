package com.gure.cinab.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing image-related information.
 * This class is used to transfer data between different layers of the application,
 * such as the controller and the client, without exposing the entity class directly.
 * <p>
 * The {@code @Data} annotation from Lombok automatically generates
 * boilerplate code such as getters, setters, toString(), equals(), and hashCode().
 */
@Data
public class ImageDTO {

    /**
     * The unique identifier for the image.
     */
    private Long id;

    /**
     * The name of the image file.
     */
    private String fileName;

    /**
     * The URL used to download the image.
     */
    private String downloadUrl;
}
