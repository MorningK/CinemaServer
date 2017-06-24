package com.five.user.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by haoye on 17-6-24.
 */
@Component
public class ServerIp {
    @Value("${serverAddress}")
    private String address;
    @Value("${serverPort}")
    private String port;

    public String getLocalIp() {
        return this.address + ":" + this.port;
    }
}

