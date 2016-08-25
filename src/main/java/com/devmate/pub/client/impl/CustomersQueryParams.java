package com.devmate.pub.client.impl;

public class CustomersQueryParams {
    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_OFFSET = 0;
    private static final boolean DEFAULT_INCLUDE_LICENSE = false;

    private String email;
    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private String address;
    private String key;
    private String identifier;
    private String invoice;
    private Long orderId;
    private Long activationId;

    private int limit;
    private int offset;
    private boolean includeLicenses;

    private CustomersQueryParams() {
        this.limit = DEFAULT_LIMIT;
        this.offset = DEFAULT_OFFSET;
        this.includeLicenses = DEFAULT_INCLUDE_LICENSE;
    }

    public static CustomersQueryParams with() {
        return new CustomersQueryParams();
    }

    public static CustomersQueryParams defaultParams() {
        return new CustomersQueryParams();
    }

    public String getEmail() {
        return email;
    }

    public CustomersQueryParams emailContains(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomersQueryParams firstNameContains(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomersQueryParams lastNameContains(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CustomersQueryParams companyContains(String company) {
        this.company = company;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomersQueryParams phoneContains(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CustomersQueryParams addressContains(String address) {
        this.address = address;
        return this;
    }

    public String getKey() {
        return key;
    }

    public CustomersQueryParams key(String key) {
        this.key = key;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public CustomersQueryParams identifierContains(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getInvoice() {
        return invoice;
    }

    public CustomersQueryParams invoiceContains(String invoice) {
        this.invoice = invoice;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public CustomersQueryParams orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getActivationId() {
        return activationId;
    }

    public CustomersQueryParams activationId(Long activationId) {
        this.activationId = activationId;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public CustomersQueryParams limit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public CustomersQueryParams offset(int offset) {
        this.offset = offset;
        return this;
    }

    public boolean includeLicenses() {
        return includeLicenses;
    }

    public CustomersQueryParams includeLicenses(boolean includeLicenses) {
        this.includeLicenses = includeLicenses;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomersQueryParams{");
        sb.append("email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append(", identifier='").append(identifier).append('\'');
        sb.append(", invoice='").append(invoice).append('\'');
        sb.append(", orderId=").append(orderId);
        sb.append(", activationId=").append(activationId);
        sb.append(", limit=").append(limit);
        sb.append(", offset=").append(offset);
        sb.append(", includeLicenses=").append(includeLicenses);
        sb.append('}');
        return sb.toString();
    }
}
