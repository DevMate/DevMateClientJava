package com.devmate.pub.client;

import com.devmate.pub.client.impl.CustomersQueryParams;
import com.devmate.pub.client.models.Customer;
import com.devmate.pub.client.models.CustomersMeta;
import com.devmate.pub.client.models.Data;
import com.devmate.pub.client.models.License;

import java.util.List;

public interface CustomersApi {
    /**
     * Get customer with licenses by customer ID.
     * @param id target customer ID.
     * @return data container with customer and meta data.
     * @throws com.devmate.pub.client.exceptions.DevMateNotFoundException if customer hasn't been found.
     * @throws java.lang.IllegalArgumentException if given id is negative or 0.
     * @throws com.devmate.pub.client.exceptions.DevMateClientErrorException on other client errors.
     * @throws com.devmate.pub.client.exceptions.DevMateServerErrorException on server errors.
     */
    Data<Customer, CustomersMeta> getCustomerById(int id);

    /**
     * Get customers list with given query parameters.
     * @param customersQueryParams input filter and list params.
     * @return data container with customers and meta data.
     * @throws com.devmate.pub.client.exceptions.DevMateIncorrectParamsException if incorrect parameters have been given.
     * @throws com.devmate.pub.client.exceptions.DevMateClientErrorException on other client errors.
     * @throws com.devmate.pub.client.exceptions.DevMateServerErrorException on server errors.
     */
    Data<List<Customer>, CustomersMeta> getCustomers(CustomersQueryParams customersQueryParams);

    /**
     * Create new customer in DevMate.
     * @param customer customer which is needed to create.
     * @return data container with created customer and meta data.
     * @throws com.devmate.pub.client.exceptions.DevMateIncorrectParamsException if incorrect parameters have been given.
     * @throws com.devmate.pub.client.exceptions.DevMateConflictException if customer with given email and company exists in DevMate.
     * @throws com.devmate.pub.client.exceptions.DevMateClientErrorException on other client errors.
     * @throws com.devmate.pub.client.exceptions.DevMateServerErrorException on server errors.
     */
    Data<Customer, CustomersMeta> createCustomer(Customer customer);

    /**
     * Update existing customer in DevMate.
     * @param customer updated customer details.
     * @return data container with updated customer and meta data.
     * @throws com.devmate.pub.client.exceptions.DevMateIncorrectParamsException if incorrect parameters have been given.
     * @throws com.devmate.pub.client.exceptions.DevMateConflictException if customer with given email and company exists in DevMate.
     * @throws com.devmate.pub.client.exceptions.DevMateClientErrorException on other client errors.
     * @throws com.devmate.pub.client.exceptions.DevMateServerErrorException on server errors.
     */
    Data<Customer, CustomersMeta> updateCustomer(Customer customer);

    /**
     * Update create new license for given customer.
     * @param customerId target customer ID.
     * @param license license which is needed to create.
     * @return data container with create license.
     * @throws com.devmate.pub.client.exceptions.DevMateIncorrectParamsException if incorrect parameters have been given.
     * @throws com.devmate.pub.client.exceptions.DevMateClientErrorException on other client errors.
     * @throws com.devmate.pub.client.exceptions.DevMateServerErrorException on server errors.
     */
    Data<License, CustomersMeta> createLicenseForCustomer(int customerId, License license);
}
