package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Customer {

    @JsonProperty("id")
    private final UUID id;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("email")
    private final String email;

    private Customer(Builder builder) {
        this.id = Objects.requireNonNull(builder.id);
        this.name = Objects.requireNonNull(builder.name);
        this.email = Objects.requireNonNull(builder.email);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() { return email; }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return new EqualsBuilder().append(id, other.id)
                .append(name, other.name)
                .append(email, other.email)
                .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static Builder builder () {
        return new Builder();
    }

    public static class Builder {

        private UUID id;
        private String name;
        private String email;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
