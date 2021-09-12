package br.com.falae.activities.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import br.com.falae.services.FirebaseService;
import br.com.falae.singletons.Info;
import br.com.falae.R;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.startTalking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText x = (EditText)  getView().findViewById(R.id.nickname);
                String nickname = x.getText().toString();

                Info info = Info.getInstance();

                info.putInfo("NICKNAME", nickname);
                FirebaseService fs = new FirebaseService();
                String myip = null;
                try {
                    myip = Info.getIPAddress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                info.putInfo("IP", myip);
                fs.saveUser(nickname, myip);


                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }



}
