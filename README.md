# DevMate Client

This is a simple [DevMate](http://devmate.com/) [Public API](https://docs.devmate.com/docs/api) client written in Java.

* [Getting Started](#getting-started)
* [Usage Example](#usage-example)

## Getting Started

How to include library to your project?

- for Maven
```xml
<dependency>
    <groupId>com.devmate.pub</groupId>
    <artifactId>client</artifactId>
    <version>1.0</version>
</dependency>
```

- for Gradle
```groovy
compile group: 'com.devmate.pub', name: 'client', version: '1.0'
```

## Usage Example

```java
// DevMate public API token
// You can generate it in Settings -> API Integration
final String token = "1234567890abcdef";

// Initialize client with builder
DevMateClient devMateClient = DevMateClientBuilder.buildDefault(token);

// Get single customer by ID
int someId = 123;
// Data container with target object and meta data
Data<Customer, CustomersMeta> singleCustomerData = devMateClient
        .customers()
        .getCustomerById(someId);

Customer singleCustomer = singleCustomerData.getData();

// Update customer details
Customer customerWithNewDetails = Customer.newBuilder(singleCustomer)
        .firstName("New Name")
        .note("Something new")
        .build();

// Returns updated data
Data<Customer, CustomersMeta> updatedCustomerData = devMateClient
        .customers()
        .updateCustomer(customerWithNewDetails);

// Create new customer
Customer createdCustomer = devMateClient
        .customers()
        .createCustomer(Customer.newBuilder()
                .email("some@email.com")
                .firstName("Dead")
                .lastName("Beef")
                .build())
        .getData();

// Create new license for customer
// License Type ID is needed, you can find it in Product -> Settings -> License Types -> ID (details field)
int customerId = createdCustomer.getId();
int licenseTypeId = 123;

License license = devMateClient
        .customers()
        .createLicenseForCustomer(customerId, License.newBuilder()
                .licenseTypeId(licenseTypeId)
                .build())
        .getData();

// Now you can get created license info, e.g. activation key
String activationKey = license.getActivationKey();

// Get list of customers with filter parameters
Data<List<Customer>, CustomersMeta> customersData = devMateClient
        .customers()
        .getCustomers(with()
                .firstNameContains("Dea")
                .key(activationKey)
                .limit(20)
                .includeLicenses(true));

int totalCustomers = customersData.getMeta().getTotal();

// Reset first activation by activation key
devMateClient.licenses().resetFirstActivation(activationKey);

// Close the DevMate client
devMateClient.close();
```