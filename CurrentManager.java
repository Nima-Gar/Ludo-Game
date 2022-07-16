package com.example.ludo;

import java.util.ArrayList;
import java.util.List;

public class CurrentManager {
    public static List<String> currentUser = new ArrayList<>();


    public static void currentFiller(String username, String password, String points){
        currentUser.removeAll(currentUser);

        currentUser.add(username);
        currentUser.add(password);
        currentUser.add(points);
    }

}
