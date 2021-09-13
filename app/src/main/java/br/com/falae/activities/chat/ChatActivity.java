package br.com.falae.activities.chat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.util.Logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.falae.R;
import br.com.falae.adapters.MessagesListAdapter;
import br.com.falae.adapters.UserListAdapter;
import br.com.falae.echoserver.engine.ClientConnectionWrapper;
import br.com.falae.echoserver.engine.Server;
import br.com.falae.echoserver.engine.ServerService;
import br.com.falae.echoserver.engine.ServerThread;
import br.com.falae.models.User;
import br.com.falae.singletons.Info;

public class ChatActivity extends AppCompatActivity {

    private ClientConnectionWrapper receiver;
    private ArrayAdapter<String> adapter;
    private ComunicationThread sender;
    private List<String> messages = new ArrayList<String>();
    private final Integer port  = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new ServerThread(this).start();


        User client = (User) Info.getInstance().getInfo("CHOOSENCHAT");


        Log.d("IP", client.getIp());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Socket clientSocket = new Socket(client.getIp(), port);
//                    DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
//                    BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    System.out.println("--------> CONEXÃO ESTABELECIDA");
//
//                    receiver = new ClientConnectionWrapper(clientSocket, os, is);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();






        ListView listView = (ListView) findViewById(R.id.list_view_chat);
        adapter = new MessagesListAdapter(this, R.layout.messages_list, this.messages);

        listView.setAdapter(adapter);

        final Button sendMessageButton = (Button) findViewById(R.id.btn_send_message);
        final EditText textBox = (EditText) findViewById(R.id.msg_box);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = textBox.getText().toString();

                if(!text.trim().equals("")) {

                    if(sender == null ){
                        sender = new ComunicationThread(client);
                        Thread t = new Thread(sender);
                        t.start();

                        try {
                            Toast.makeText(getApplicationContext(),"Iniciando conexão com o servidor", 12);
                            t.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    sender.sendMessage(text);
                    messages.add("You: "+text+"  at("+getTime()+")");
                    incommingMessage();
                }

                textBox.setText("");
            }
        });
    }

    public void addMessage(String msg) {
        messages.add(msg);
        this.incommingMessage();
    }

    private void incommingMessage() {
        adapter.notifyDataSetChanged();
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }


    class ComunicationThread implements   Runnable{

        private User client;
        private Socket clientSocket;
        private BufferedReader input;


        public ComunicationThread(User client) {
            this.client = client;
        }
        @Override
        public void run() {

            try {
                //clientSocket = new Socket(client.getIp(), port);
                clientSocket = new Socket("10.0.2.2", 8080);
                Log.d("OK","CONEXÃO COM O SERVIDOR ESTABELECIDA");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        void sendMessage(String msg) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (null != clientSocket) {
                            PrintWriter out = new PrintWriter(new BufferedWriter(
                                    new OutputStreamWriter(clientSocket.getOutputStream())),
                                    true);

                            out.println(msg);
                            // TODO: remover a gambiarra
                            //Uma mensagem é enviada, mas a próxima não
                            out.println(msg);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

}


