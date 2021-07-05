package br.com.falae;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import br.com.falae.adapters.UserListAdapter;
import br.com.falae.factories.UserFactory;
import br.com.falae.models.User;

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

        final List<User> onlineUsers = UserFactory.getMockUsers();

        ListView listView = (ListView) getView().findViewById(R.id.list_view);

        listView.setAdapter(new UserListAdapter(getActivity(), R.layout.user_list, onlineUsers));



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Info i = Info.getInstance();
                i.putInfo("CHOOSENCHAT", onlineUsers.get(position));

                Intent chatActivity = new Intent(getActivity(), ChatActivity.class);
                startActivity(chatActivity);

            }
        });



    }
}
