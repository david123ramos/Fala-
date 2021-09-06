package br.com.falae.echoserver.engine;


import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.falae.models.User;
import br.com.falae.server.config.ServerConfig;
import br.com.falae.singletons.Info;

public class Server extends AsyncTask<User, Void, ClientConnectionWrapper> {

    private static  final int port = 5423;
    private Socket clientSocket = null;
    private DataOutputStream os = null;
    private BufferedReader is = null;

    private void connectToClient(User client) {

        try {

            clientSocket = new Socket("127.0.0.1", port);
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("--------> CONEXÃO ESTABELECIDA");

        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + client.getIp());
        } catch (IOException e) {
            System.err.println("Impossível se conectar ao ip: " + client.getIp());
        }

    }


    @Override
    protected ClientConnectionWrapper doInBackground(User... users) {
        android.os.Debug.waitForDebugger();

        this.connectToClient(users[0]);
        ClientConnectionWrapper ccw = new ClientConnectionWrapper(clientSocket, os, is);
        return ccw;
    }
}
