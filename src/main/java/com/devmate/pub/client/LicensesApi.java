package com.devmate.pub.client;

public interface LicensesApi {
    /**
     * Reset first license activation by activation key.
     * @param activationKey license activation key which will be used for reset. Example : id012345678987odr.
     * @throws com.devmate.pub.client.exceptions.DevMateNotFoundException if license hasn't been found in DevMate by given activation key.
     * @throws com.devmate.pub.client.exceptions.DevMateClientErrorException on other client errors.
     * @throws com.devmate.pub.client.exceptions.DevMateServerErrorException on server errors.
     */
    void resetFirstActivation(String activationKey);
}
