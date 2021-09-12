package br.com.falae.singletons;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe usada para salvar informações arbitrárias que podem ser
 * compartilhadas entre os fragmentos.
 */
public class Info {

    private static Info myself;
    private Map<Object, Object> map;

    private Info() {
        this.map = new HashMap<>();
    }

    public static Info getInstance() {
        if(myself == null) {
            myself = new Info();
        }
        return myself;
    }

    public void putInfo(Object key, Object value) {
         this.map.put(key, value);
    }

    public Object getInfo(Object key) {
        return this.map.get(key);
    }

    public static String getIPAddress() throws Exception {
        String resultIpv6 = "";
        String resultIpv4 = "";

        for (Enumeration en = NetworkInterface.getNetworkInterfaces();
             en.hasMoreElements();) {

            NetworkInterface intf = (NetworkInterface) en.nextElement();
            for (Enumeration enumIpAddr = intf.getInetAddresses();
                 enumIpAddr.hasMoreElements();) {

                InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                if(!inetAddress.isLoopbackAddress()){
                    if (inetAddress instanceof Inet4Address) {
                        resultIpv4 = inetAddress.getHostAddress().toString();
                    } else if (inetAddress instanceof Inet6Address) {
                        resultIpv6 = inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        return ((resultIpv4.length() > 0) ? resultIpv4 : resultIpv6);
    }
}
