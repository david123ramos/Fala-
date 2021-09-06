package br.com.falae.activities.main;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmInfoRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.zip.Inflater;

import br.com.falae.activities.chat.ChatActivity;
import br.com.falae.echoserver.engine.Server;
import br.com.falae.echoserver.engine.ServerService;
import br.com.falae.services.FirebaseService;
import br.com.falae.singletons.Info;
import br.com.falae.R;
import br.com.falae.adapters.UserListAdapter;
import br.com.falae.factories.UserFactory;
import br.com.falae.models.User;
//import br.com.falae.sniffer.NetworkSniffTask;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_second, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        NetworkSniffTask nst = new NetworkSniffTask(getContext() ,view);
//        ArrayList<String> ips = new ArrayList<>();
//        nst.execute();


        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        final Info info = Info.getInstance();
        String nick = (String) info.getInfo("NICKNAME");
        TextView textView = (TextView) view.findViewById(R.id.textview_second);

        //TODO remover o texto de boas vindas.
        textView.setTextSize(40);
        textView.setText("Olá, "+nick+"!");

        FirebaseService fbs = new FirebaseService();
        //final List<User> onlineUsers = UserFactory.getMockUsers(2);

        FirebaseService.getOnlineUsers().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()) {
                    // TODO: remover o ip do usuário da aplicação.
                    List<User> onlineUsers = queryDocumentSnapshots.toObjects(User.class);

                    ListView listView = (ListView) getView().findViewById(R.id.list_view);

                    listView.setAdapter(new UserListAdapter(getActivity(), R.layout.user_list, onlineUsers));


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Info i = Info.getInstance();
                            User client = onlineUsers.get(position);
                            i.putInfo("CHOOSENCHAT", client);
                            Intent chatActivity = new Intent(getActivity(), ChatActivity.class);
                            startActivity(chatActivity);
                        }
                    });
                }
            }
        });

    }
}
