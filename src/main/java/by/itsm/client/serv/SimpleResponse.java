package by.itsm.client.serv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SimpleResponse {

    @JsonProperty("message")
    private String message;

    public SimpleResponse() {
    }

    public SimpleResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public SimpleResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleResponse that = (SimpleResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
