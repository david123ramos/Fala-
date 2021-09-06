package br.com.falae.echoserver.engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientConnectionWrapper {
    private Socket clientSocket = null;
    private DataOutputStream os = null;
    private BufferedReader is = null;

    public ClientConnectionWrapper(Socket clientSocket, DataOutputStream os, BufferedReader is) {
        this.clientSocket = clientSocket;
        this.os = os;
        this.is = is;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public DataOutputStream getOs() {
        return os;
    }

    public void setOs(DataOutputStream os) {
        this.os = os;
    }

    public BufferedReader getIs() {
        return is;
    }

    public void setIs(BufferedReader is) {
        this.is = is;
    }
}
