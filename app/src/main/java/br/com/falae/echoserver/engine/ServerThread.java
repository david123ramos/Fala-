package br.com.falae.echoserver.engine;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

import br.com.falae.activities.chat.ChatActivity;
import br.com.falae.singletons.Info;

public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final Handler handler;
    private PrintWriter out;
    private BufferedReader in;
    private ChatActivity act;
    public ServerThread(ChatActivity act) {
        handler = new Handler(act.getMainLooper());
        this.act = act;
    }

    public void run(){
        Log.d("DEBUG", "INICIANDO SERVIDOR");

        try{
            // Open a server socket listening on port 8080
            InetAddress addr = InetAddress.getByName(Info.getIPAddress());
            System.out.println(addr.getHostAddress());
            serverSocket = new ServerSocket(8080, 0, addr);
            System.out.println("====== SERVIDOR INICIADO NA PORTA "+ 8080);

            clientSocket = serverSocket.accept();

            if(in == null && out == null) {
                in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            }

            out.println("Server: opa");

            while(in.readLine() != null) {
                String input = in.readLine();

                if (input.equals("bye")) {
                    in.close();
                    out.close();
                    in = null;
                    out = null;
                    break;
                }

                System.out.println(input);
                out.println("received: " + input);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        act.addMessage(input);
                    }
                });

            }


        } catch(Exception e) {
            Log.e("ERRO", "ERRO", e);
        }
    }


    private void runOnUiThread(Runnable r) {
        handler.post(r);
    }
}
