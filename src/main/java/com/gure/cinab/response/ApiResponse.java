/**
 * The ApiResponse class is a utility for standardizing API responses.
 * It encapsulates a message and an optional data object to provide consistent feedback from the server to clients.
 * @since 1.0.6
 */
package com.gure.cinab.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ApiResponse {

    /**
     * A message describing the status or outcome of the operation.
     * For example, "Success", "Error", or "Upload failed!".
     */
    @JsonProperty("message")
    private String message;

    /**
     * Optional data object providing additional details about the response.
     * This can include specific results, error details, or any other relevant information.
     */
    @JsonProperty("data")
    private Object data;
}
