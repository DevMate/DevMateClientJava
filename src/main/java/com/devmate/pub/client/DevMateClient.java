package com.devmate.pub.client;

import java.io.Closeable;

public interface DevMateClient extends Closeable {
    /**
     * Provide DevMate Public API customers service.
     * @return customers API service.
     */
    CustomersApi customers();

    /**
     * Provide DevMate Public API licenses service.
     * @return licenses API service.
     */
    LicensesApi licenses();

    /**
     * Closes any and all underlying connections and release resources.
     */
    void close();
}
