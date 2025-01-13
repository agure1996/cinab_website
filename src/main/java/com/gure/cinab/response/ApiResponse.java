/**
 * The ApiResponse class is a utility for standardizing API responses.
 * It encapsulates a message and an optional data object to provide consistent feedback from the server to clients.
 * @since 1.0.6
 */
package com.gure.cinab.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse {

    /**
     * A message describing the status or outcome of the operation.
     * For example, "Success", "Error", or "Upload failed!".
     */
    private String message;

    /**
     * Optional data object providing additional details about the response.
     * This can include specific results, error details, or any other relevant information.
     */
    private Object data;
}
