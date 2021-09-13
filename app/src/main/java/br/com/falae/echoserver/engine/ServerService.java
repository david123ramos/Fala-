package br.com.falae.echoserver.engine;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Provider;
import java.sql.SQLOutput;
import java.util.Scanner;

import br.com.falae.server.config.ServerConfig;
import br.com.falae.singletons.Info;

public class ServerService extends Service {

    private ServerSocket serverSocket;
    private Gson gson = new Gson();
    private final Integer myport = 5423;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("=======> SERVIDOR INICIADO NA PORTA: "+5423);
        //System.out.println(serverSocket.getLocalSocketAddress().toString());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.os.Debug.waitForDebugger();  // this line is key

        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {

                    serverSocket = new ServerSocket(myport);
                    System.out.println("=======> SERVIDOR INICIADO NA PORTA: "+5423);
                    System.out.println(serverSocket.getLocalSocketAddress().toString());


                } catch (IOException e) {
                    e.printStackTrace();
                }
                //while (true) {
                Socket client = null;
//                try {
//
//                    client = serverSocket.accept();
//                    DataOutputStream dOut = new DataOutputStream(client.getOutputStream());
//                    Scanner input = new Scanner(client.getInputStream());
//
//                    Message m = gson.fromJson(input.nextLine(), Message.class);
//
//
//                    switch (m.getEvent()) {
//
//                        case "MYNAMEIS":
//
//                            //TODO: SE COMUNICAR COM THREAD DA UI PARA A ATUALIZAÇÃO DE PESSOAS ONLINE
//
//                            break;
//                    }
//                    input.close();
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                //}
                //return super.onStartCommand(intent, flags, startId);
            }
        });


        thread.start();

       return START_STICKY;
    }


    private boolean isAvailable(int port) {

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }

    private Integer getMyPort(int start) throws Exception {


        if(start  >= ServerConfig.ports.size()) {
            throw new Exception("Impossível se conectar");
        }

        if(isAvailable(ServerConfig.ports.get(start))) {
            return ServerConfig.ports.get(start);
        }
        return getMyPort(++start);
    }


    private void broadcast() throws IOException {
        for(Integer port : ServerConfig.ports) {

            if(!isAvailable(port)) {

                Socket socket = new Socket(ServerConfig.host, port);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                //Message m = new Message("MYNAMEIS", Info.getInstance().getInfo("NICKNAME") );
                //dataOutputStream.writeUTF(gson.toJson(m));
                dataOutputStream.flush();
                dataOutputStream.close();
                socket.close();
            }

        }
    }


}
