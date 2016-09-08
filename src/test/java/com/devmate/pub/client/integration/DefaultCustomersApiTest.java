package com.devmate.pub.client.integration;

import com.devmate.pub.client.exceptions.DevMateNotFoundException;
import com.devmate.pub.client.impl.CustomersQueryParams;
import com.devmate.pub.client.models.Customer;
import com.devmate.pub.client.models.CustomersMeta;
import com.devmate.pub.client.models.Data;
import com.devmate.pub.client.models.Error;
import com.devmate.pub.client.models.HistoryRecord;
import com.devmate.pub.client.models.License;
import com.devmate.pub.client.models.Product;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.devmate.pub.client.TestUtils.equalToObjectInJson;
import static com.devmate.pub.client.TestUtils.toJson;
import static com.devmate.pub.client.impl.CustomersQueryParams.with;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DefaultCustomersApiTest extends BaseIntegrationTest {
    private static final Date DATE = new Date(1471478400000L);

    @Test
    public void getCustomerById() throws Exception {
        Data<Customer, CustomersMeta> data = Data.of(defaultCustomerBuilder()
                .licenses(asList(defaultLicenseBuilder().build()))
                .build());

        stubFor(get(urlEqualTo("/v2/customers/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(toJson(data))));

        Data<Customer, CustomersMeta> actualData = testDevMateClient().customers().getCustomerById(1);
        assertThat(actualData, is(data));

        verify(getRequestedFor(urlEqualTo("/v2/customers/1"))
                .withHeader(AUTHORIZATION, new ContainsPattern(TOKEN)));
    }

    @Test(expected = DevMateNotFoundException.class)
    public void getNonExistingCustomerById() throws Exception {
        Data<Customer, CustomersMeta> data = Data.errors(Arrays.asList(new Error("some", "error")));

        stubFor(get(urlEqualTo("/v2/customers/1"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(toJson(data))));

        testDevMateClient().customers().getCustomerById(1);
    }

    @Test
    public void getCustomersWithFilters() throws Exception {
        Data<List<Customer>, CustomersMeta> data = Data.of(
                asList(defaultCustomerBuilder()
                        .licenses(asList(defaultLicenseBuilder().build()))
                        .build()),
                new CustomersMeta(1)
        );

        CustomersQueryParams queryParams = with()
                .firstNameContains("first_name")
                .lastNameContains("last_name")
                .emailContains("email")
                .companyContains("company")
                .phoneContains("1234567890")
                .addressContains("address")
                .identifierContains("aa:aa:aa:aa:aa:aa")
                .invoiceContains("invoice")
                .key("1234567890")
                .activationId(1234567890L)
                .orderId(1234567890L)
                .limit(20)
                .offset(20)
                .includeLicenses(true);

        stubFor(get(urlMatching("/v2/customers.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(toJson(data))));

        Data<List<Customer>, CustomersMeta> actualData = testDevMateClient().customers().getCustomers(queryParams);
        assertThat(actualData, is(data));

        verify(getRequestedFor(urlMatching("/v2/customers.*"))
                .withHeader(AUTHORIZATION, new ContainsPattern(TOKEN))
                .withQueryParam("filter[first_name]", new ContainsPattern(queryParams.getFirstName()))
                .withQueryParam("filter[last_name]", new ContainsPattern(queryParams.getLastName()))
                .withQueryParam("filter[email]", new ContainsPattern(queryParams.getEmail()))
                .withQueryParam("filter[company]", new ContainsPattern(queryParams.getCompany()))
                .withQueryParam("filter[phone]", new ContainsPattern(queryParams.getPhone()))
                .withQueryParam("filter[identifier]", new ContainsPattern(queryParams.getIdentifier()))
                .withQueryParam("filter[invoice]", new ContainsPattern(queryParams.getInvoice()))
                .withQueryParam("filter[key]", new ContainsPattern(queryParams.getKey()))
                .withQueryParam("filter[activation_id]", new ContainsPattern(valueOf(queryParams.getActivationId())))
                .withQueryParam("filter[order_id]", new ContainsPattern(valueOf(queryParams.getOrderId())))
                .withQueryParam("limit", new ContainsPattern(valueOf(queryParams.getLimit())))
                .withQueryParam("offset", new ContainsPattern(valueOf(queryParams.getOffset())))
                .withQueryParam("with", new ContainsPattern("licenses")));
    }

    @Test
    public void createCustomer() throws Exception {
        Customer customerToCreate = defaultCustomerBuilder().id(null).build();
        Data<Customer, CustomersMeta> data = Data.of(Customer.newBuilder(customerToCreate).id(1).build());

        stubFor(post(urlEqualTo("/v2/customers"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(toJson(data))));

        Data<Customer, CustomersMeta> actualData = testDevMateClient().customers().createCustomer(customerToCreate);
        assertThat(actualData, is(data));

        verify(postRequestedFor(urlEqualTo("/v2/customers"))
                .withHeader(AUTHORIZATION, new ContainsPattern(TOKEN))
                .withRequestBody(equalToObjectInJson(Data.of(customerToCreate))));
    }

    @Test
    public void updateCustomerById() throws Exception {
        Customer customerToUpdate = defaultCustomerBuilder().build();
        Data<Customer, CustomersMeta> data = Data.of(customerToUpdate);

        stubFor(put(urlEqualTo("/v2/customers/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(toJson(data))));

        Data<Customer, CustomersMeta> actualData = testDevMateClient().customers().updateCustomer(customerToUpdate);
        assertThat(actualData, is(data));

        verify(putRequestedFor(urlEqualTo("/v2/customers/1"))
                .withHeader(AUTHORIZATION, new ContainsPattern(TOKEN))
                .withRequestBody(equalToObjectInJson(Data.of(customerToUpdate))));
    }

    @Test
    public void createLicenseForCustomerById() throws Exception {
        License licenseToCreate = defaultLicenseBuilder().build();
        Data<License, CustomersMeta> data = Data.of(licenseToCreate);

        stubFor(post(urlEqualTo("/v2/customers/1/licenses"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(toJson(data))));

        Data<License, CustomersMeta> actualData = testDevMateClient()
                .customers()
                .createLicenseForCustomer(1, licenseToCreate);

        assertThat(actualData, is(data));

        verify(postRequestedFor(urlEqualTo("/v2/customers/1/licenses"))
                .withHeader(AUTHORIZATION, new ContainsPattern(TOKEN))
                .withRequestBody(equalToObjectInJson(Data.of(licenseToCreate))));
    }

    private static Customer.Builder defaultCustomerBuilder() {
        return Customer.newBuilder()
                .id(1)
                .email("test@email.com")
                .firstName("First")
                .lastName("Second")
                .company("Company")
                .phone("1234567890")
                .address("Test Address")
                .note("Test Note")
                .dateAdded(DATE);
    }

    private static License.Builder defaultLicenseBuilder() {
        return License.newBuilder()
                .id(1)
                .campaign("Test Campaign")
                .status(License.Status.ACTIVE)
                .licenseTypeId(1)
                .licenseTypeName("Test License Type")
                .invoice("Test Invoice")
                .activationsTotal(100)
                .activationsUsed(50)
                .activationKey("id123456789098odr")
                .isSubscription(true)
                .dateCreated(DATE)
                .expirationDate(DATE)
                .products(asList(Product.newBuilder()
                        .id(1)
                        .name("Test Product")
                        .bundleId("com.test.bundle")
                        .useOfflineLicense(true)
                        .build()))
                .history(asList(HistoryRecord.newBuilder()
                        .type(HistoryRecord.Type.ACTIVATION)
                        .userName("Test User")
                        .note("Test Note")
                        .timestamp(DATE)
                        .id(1)
                        .licenseId(1L)
                        .activationId(1L)
                        .activationName("Act Name")
                        .activationEmail("act@mail.test")
                        .offlineLicense("Offline")
                        .productName("Test Product")
                        .identifiers("aa:aa:aa:aa:aa:aa")
                        .deactivated(true)
                        .build()));
    }
}