package com.five.user.utils;

import org.springframework.context.annotation.Bean;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by haoye on 17-6-11.
 */
public class LocalIp {

    public static String getLocalIp() throws Exception {
        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements())
        {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            System.out.println();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements())
            {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address)
                {
                    if (netInterface.getName().equals("eth0") && !ip.getHostAddress().equals("")) {
                        return ip.getHostAddress();
                    }
                    if (netInterface.getName().equals("wlan0") && !ip.getHostAddress().equals("")) {
                        return ip.getHostAddress();
                    }
                }
            }
        }
        return InetAddress.getLocalHost().getHostAddress();
    }
}
