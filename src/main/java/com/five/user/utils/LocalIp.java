//package com.five.user.utils;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.validation.constraints.NotNull;
//import java.net.*;
//import java.util.Enumeration;
//
///**
// * Created by haoye on 17-6-11.
// */
//@Component
//@ConfigurationProperties(prefix = "server")
//public class LocalIp {
//    @NotNull
//    private String address;
//    @NotNull
//    private String port;
//
//    public String getLocalIp() {
//        return this.address + ":" + this.port;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public void setPort(String port) {
//        this.port = port;
//    }
//}
