package com.devmate.pub.client;

import com.devmate.pub.client.impl.DefaultDevMateClient;

public final class DevMateClientBuilder {
    private static final String DEVMATE_PUBLIC_API_URL = "https://public-api.devmate.com";

    public static DevMateClient buildDefault(String token) {
        return new DefaultDevMateClient(DEVMATE_PUBLIC_API_URL, token);
    }
}
