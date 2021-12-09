package com.epam.brest.rest.controller.exception;

import java.util.Arrays;
import java.util.List;

public class ErrorResponse {

    /**
     * Field message.
     */

    private String message;

    /**
     * Field details.
     */

    private List<String> details;

    /**
     * Constructor.
     */

    public ErrorResponse() {
        super();
    }

    /**
     * Constructor.
     *
     * @param details List<String>.
     * @param message String.
     */

    public ErrorResponse(final String message,
                         final List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    /**
     * Constructor.
     *
     * @param message String.
     * @param ex Exception.
     */

    public ErrorResponse(final String message, final Exception ex) {
        super();
        this.message = message;
        if (ex != null) {
            this.details = Arrays.asList(ex.getMessage());
        }
    }

    /**
     * Getter for message.
     *
     * @return message String.
     */

    public String getMessage() {
        return message;
    }

    /**
     * Setter for message.
     *
     * @param message String.
     */

    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Getter for details.
     *
     * @return details List<String>.
     */

    public List<String> getDetails() {
        return details;
    }

    /**
     * Getter for details.
     *
     * @param details List<String>.
     */

    public void setDetails(final List<String> details) {
        this.details = details;
    }
}
