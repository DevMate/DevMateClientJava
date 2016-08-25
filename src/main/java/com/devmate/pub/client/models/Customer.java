package com.devmate.pub.client.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.Date;
import java.util.List;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Customer {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("company")
    private String company;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("address")
    private String address;
    @JsonProperty("note")
    private String note;
    @JsonProperty("date_added")
    private Date dateAdded;
    @JsonProperty("licenses")
    private List<License> licenses;

    protected Customer() {}

    private Customer(Builder builder) {
        id = builder.id;
        email = builder.email;
        firstName = builder.firstName;
        lastName = builder.lastName;
        company = builder.company;
        phone = builder.phone;
        address = builder.address;
        note = builder.note;
        dateAdded = builder.dateAdded;
        licenses = builder.licenses;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Customer copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.email = copy.email;
        builder.firstName = copy.firstName;
        builder.lastName = copy.lastName;
        builder.company = copy.company;
        builder.phone = copy.phone;
        builder.address = copy.address;
        builder.note = copy.note;
        builder.dateAdded = copy.dateAdded;
        builder.licenses = copy.licenses;
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getNote() {
        return note;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append(", dateAdded=").append(dateAdded);
        sb.append(", licenses=").append(licenses);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equal(id, customer.id) &&
                Objects.equal(email, customer.email) &&
                Objects.equal(firstName, customer.firstName) &&
                Objects.equal(lastName, customer.lastName) &&
                Objects.equal(company, customer.company) &&
                Objects.equal(phone, customer.phone) &&
                Objects.equal(address, customer.address) &&
                Objects.equal(note, customer.note) &&
                Objects.equal(dateAdded, customer.dateAdded) &&
                Objects.equal(licenses, customer.licenses);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, email, firstName, lastName, company, phone, address, note, dateAdded, licenses);
    }

    public static final class Builder {
        private Integer id;
        private String email;
        private String firstName;
        private String lastName;
        private String company;
        private String phone;
        private String address;
        private String note;
        private Date dateAdded;
        private List<License> licenses;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder company(String val) {
            company = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder note(String val) {
            note = val;
            return this;
        }

        public Builder dateAdded(Date val) {
            dateAdded = val;
            return this;
        }

        public Builder licenses(List<License> val) {
            licenses = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
