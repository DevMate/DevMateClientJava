package com.devmate.pub.client.impl;

import com.devmate.pub.client.CustomersApi;
import com.devmate.pub.client.models.Customer;
import com.devmate.pub.client.models.CustomersMeta;
import com.devmate.pub.client.models.Data;
import com.devmate.pub.client.models.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;
import static javax.ws.rs.client.Entity.json;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

public class DefaultCustomersApi extends AbstractApi implements CustomersApi {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCustomersApi.class);
    private static final String SERVICE_PATH = "/v2/customers";
    private static final String LICENSES_PATH = "/licenses";
    private static final GenericType<Data<Customer, CustomersMeta>> CUSTOMER_TYPE;
    private static final GenericType<Data<List<Customer>, CustomersMeta>>  CUSTOMERS_TYPE;
    private static final GenericType<Data<License, CustomersMeta>> LICENSE_TYPE;

    static {
        CUSTOMER_TYPE = new GenericType<Data<Customer, CustomersMeta>>() {};
        CUSTOMERS_TYPE = new GenericType<Data<List<Customer>, CustomersMeta>>() {};
        LICENSE_TYPE = new GenericType<Data<License, CustomersMeta>>() {};
    }

    public DefaultCustomersApi(WebTarget rootTarget) {
        super(rootTarget);
    }

    @Override
    protected String servicePath() {
        return SERVICE_PATH;
    }

    @Override
    public Data<Customer, CustomersMeta> getCustomerById(int id) {
        LOG.debug("Get customer by id : {}", id);
        return target()
                .path(idToPath(id))
                .request(APPLICATION_JSON_TYPE)
                .get(CUSTOMER_TYPE);
    }

    @Override
    public Data<List<Customer>, CustomersMeta> getCustomers(CustomersQueryParams customersQueryParams) {
        LOG.debug("Get customers with query parameters : {}", customersQueryParams);
        return targetWithQueryParams(checkNotNull(customersQueryParams, "Given params is null."))
                .request(APPLICATION_JSON_TYPE)
                .get(CUSTOMERS_TYPE);
    }

    @Override
    public Data<Customer, CustomersMeta> createCustomer(Customer customer) {
        LOG.debug("Create customer : ", customer);
        return target()
                .request(APPLICATION_JSON_TYPE)
                .post(json(Data.of(checkNotNull(customer, "Given customer is null"))), CUSTOMER_TYPE);
    }

    @Override
    public Data<Customer, CustomersMeta> updateCustomer(Customer customer) {
        LOG.debug("Update customer : ", customer);
        customer = checkNotNull(customer, "Given customer is null");
        return target()
                .path(idToPath(checkNotNull(customer.getId(), "Given customer id is null")))
                .request(APPLICATION_JSON_TYPE)
                .put(json(Data.of(customer)), CUSTOMER_TYPE);
    }

    @Override
    public Data<License, CustomersMeta> createLicenseForCustomer(int customerId, License license) {
        LOG.debug("Create license : {} for customer : {}", license, customerId);
        return target()
                .path(idToPath(customerId))
                .path(LICENSES_PATH)
                .request(APPLICATION_JSON_TYPE)
                .post(json(Data.of(checkNotNull(license, "Given license is null"))), LICENSE_TYPE);
    }

    private WebTarget targetWithQueryParams(CustomersQueryParams params) {
        WebTarget target = target();
        target = setQueryParamIfIsNotNull(target, filterName("email"), params.getEmail());
        target = setQueryParamIfIsNotNull(target, filterName("first_name"), params.getFirstName());
        target = setQueryParamIfIsNotNull(target, filterName("last_name"), params.getLastName());
        target = setQueryParamIfIsNotNull(target, filterName("company"), params.getCompany());
        target = setQueryParamIfIsNotNull(target, filterName("phone"), params.getPhone());
        target = setQueryParamIfIsNotNull(target, filterName("address"), params.getAddress());
        target = setQueryParamIfIsNotNull(target, filterName("key"), params.getKey());
        target = setQueryParamIfIsNotNull(target, filterName("identifier"), params.getIdentifier());
        target = setQueryParamIfIsNotNull(target, filterName("order_id"), params.getOrderId());
        target = setQueryParamIfIsNotNull(target, filterName("activation_id"), params.getActivationId());
        target = setQueryParamIfIsNotNull(target, filterName("invoice"), params.getInvoice());

        if (params.includeLicenses()) {
            target = target.queryParam("with", "licenses");
        }

        return target.queryParam("limit", params.getLimit()).queryParam("offset", params.getOffset());
    }

    private static String filterName(String fieldName) {
        return format("filter[%s]", fieldName);
    }
}
