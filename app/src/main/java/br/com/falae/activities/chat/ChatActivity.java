package br.com.falae.activities.chat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.falae.R;
import br.com.falae.adapters.UserListAdapter;
import br.com.falae.echoserver.engine.ClientConnectionWrapper;
import br.com.falae.echoserver.engine.Server;
import br.com.falae.echoserver.engine.ServerService;
import br.com.falae.models.User;
import br.com.falae.singletons.Info;

public class ChatActivity extends AppCompatActivity {

    private ClientConnectionWrapper receiver;
    private ArrayAdapter<String> adapter;
    private List<String> messages = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ServerService ss = new ServerService();
        startService(new Intent(this, ServerService.class));

        User client = (User) Info.getInstance().getInfo("CHOOSENCHAT");
        AsyncTask<User, Void, ClientConnectionWrapper> d = new Server().execute(client);
        try {
            receiver = d.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  final ListView listView = (ListView) findViewById(R.id.list_view);
        //  adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_chat, messages);
        //  listView.setAdapter(adapter);

        final Button sendMessageButton = (Button) findViewById(R.id.btn_send_message);
        final EditText textBox = (EditText) findViewById(R.id.msg_box);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = textBox.getText().toString();
                messages.add(text);
                //  adapter.notifyDataSetChanged();
            }
        });


    }

}
