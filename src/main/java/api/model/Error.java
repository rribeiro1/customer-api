package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Error {

    @JsonProperty("codigo")
    private final String code;
    @JsonProperty("mensagem")
    private final String message;

    public Error(String code, String message) {
        this.code = Objects.requireNonNull(code);
        this.message = Objects.requireNonNull(message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Error other = (Error) obj;
        return new EqualsBuilder().append(code, other.code).append(message, other.message).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
