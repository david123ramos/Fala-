package br.com.falae.server.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ServerConfig {
    public static final String host = "172.22.0.1";
    public static final List<Integer> ports =
            new ArrayList<>(Arrays.asList(new Integer[] {8219, 8220, 8221, 8222, 8223} ));

    public static Integer removePort(int port) {
        return ServerConfig.ports.remove(port);
    }
}
