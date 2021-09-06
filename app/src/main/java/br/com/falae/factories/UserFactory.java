package br.com.falae.factories;

import java.util.ArrayList;
import java.util.List;

import br.com.falae.models.User;

public class UserFactory {


    public static List<User> getMockUsers() {
        List<User> users = new ArrayList<>();

        User u1 = new User("David", "01.02.010.2");
        User u2 = new User("Gena", "01.02.010.2");
        User u3 = new User("Hebe", "01.02.010.2");
        User u4 = new User("Giba", "01.02.010.2");
        User u5 = new User("Lua", "01.02.010.2");
        User u6 = new User("Fravio", "01.02.010.2");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        return users;
    }

    public static List<User> getMockUsers(int qtd) {
        List<User> users = new ArrayList<>();

        for(int i =0; i < qtd; i++) {
            User u = new User("USER "+String.valueOf(i), "01.02.010.2");
            users.add(u);
        }
        return users;

    }



}
