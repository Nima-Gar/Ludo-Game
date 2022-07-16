package com.example.ludo;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public int id;
    public String username;
    public String password;
    public int points;

    static List<Account> accounts = new ArrayList<>();

    public static int pointsOf(String user){
        int points = 0;
        for(Account account: accounts)
            if (account.username.equals(user))
                points = account.points;

        return points;
    }
}
