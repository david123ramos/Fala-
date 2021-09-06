package br.com.falae.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.falae.models.User;
import br.com.falae.singletons.Info;
public class FirebaseService {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static List<User> onlineUsers = new ArrayList<>();

    public FirebaseService(){

    }

    public void saveUser(String nick, String ip){
        Map<String, String> user = new HashMap<>();
        user.put("ip",  ip);
        user.put("nick", nick);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("ok");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Deu ruim");
                    }
                });
    }
    public void removeUser(String ip){}



    public static Task<QuerySnapshot> getOnlineUsers() {
       return db.collection("users")
                .get();
    }
}
