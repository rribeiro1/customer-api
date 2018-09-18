package api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ErrorResponse {

    private final List<Error> errors;

    public ErrorResponse(final List<Error> errors) {
        this.errors = errors;
    }

    public ErrorResponse(final Error error) {
        this.errors = Arrays.asList(error);
    }

    @JsonValue
    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorResponse other = (ErrorResponse) obj;
        return new EqualsBuilder().append(errors, other.errors).isEquals();
    }
}
