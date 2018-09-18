package api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;
import java.util.Objects;

public class CustomerResponse {

    private final List<Customer> customers;

    public CustomerResponse(List<Customer> customers) {
        this.customers = customers;
    }

    @JsonProperty
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customers);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerResponse other = (CustomerResponse) obj;
        return new EqualsBuilder().append(customers, other.customers).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
