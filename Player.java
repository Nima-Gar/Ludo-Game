package com.example.ludo;

public class Player {

    int finishedGame = 0;
    String name;
    int points;

    final double homeX1, homeY1, homeX2, homeY2, homeX3, homeY3, homeX4, homeY4;
    final double startPointX, startPointY;

    String[][] movements = new String[4][17];


    private static int playersCounter = 0;
    private final static Player p1 = new Player(335.5, 69, 291.5, 112, 374.7, 112, 335, 154, 286.2, 234);
    private final static Player p2 = new Player(662.5, 69, 619, 112, 701, 112, 662.5, 154, 536.8, 63.85);
    private final static Player p3 = new Player(662.5, 395, 619, 439, 701.5, 439, 662.5, 480, 706.4, 315);
    private final static Player p4 = new Player(335.7, 395, 291, 439, 375, 439, 335, 480.5, 455.8, 484.05);
    static Player player1;
    static Player player2;
    static Player player3;
    static Player player4;


    private Player(double homeX1, double homeY1, double homeX2, double homeY2, double homeX3, double homeY3, double homeX4, double homeY4, double startPointX, double startPointY) {
        this.homeX1 = homeX1;
        this.homeY1 = homeY1;
        this.homeX2 = homeX2;
        this.homeY2 = homeY2;
        this.homeX3 = homeX3;
        this.homeY3 = homeY3;
        this.homeX4 = homeX4;
        this.homeY4 = homeY4;

        this.startPointX = startPointX;
        this.startPointY = startPointY;


        if (homeX1 == 335.5)
            for (int counter = 0; counter < 4; counter++)
                movements[counter] =  new String[] {"inc/___4", "inc/dec", "___/inc5", "inc/___2", "___/dec5", "inc/inc", "inc/___5", "___/dec2", "dec/___5", "dec/inc", "___/inc5", "dec/___2", "___/dec5", "dec/dec", "dec/___5", "___/dec1", "inc/___5"};


        else if (homeX1 == 662.5 && homeY1 == 69)
            for (int counter = 0; counter < 4; counter++)
                movements[counter] = new String[] {"___/dec4", "inc/inc", "inc/___5", "___/inc2", "dec/___5", "dec/inc", "___/inc5", "dec/___2", "___/dec5", "dec/dec", "dec/___5", "___/dec2", "inc/___5", "inc/dec", "___/dec5", "inc/___1", "___/dec5"};


        else if (homeX1 == 662.5 && homeY1 == 395)
            for (int counter=0 ; counter < 4 ; counter++)
                movements[counter] = new String[] {"dec/___4", "dec/inc", "___/inc5", "dec/___2", "___/dec5", "dec/dec", "___/dec5", "___/dec2", "inc/___5", "inc/dec", "___/dec5", "inc/___2", "___/inc5", "inc/inc", "inc/___5", "___/inc1", "dec/___5"};


        else if (homeX1 == 335.7)
            for (int counter=0 ; counter < 4 ; counter++)
                movements[counter] = new String[] {"___/dec4", "dec/dec", "dec/___5", "___/dec2", "inc/___5", "inc/dec", "___/dec5", "inc/___2", "___/inc5", "inc/inc", "inc/___5", "___/inc2", "dec/___5", "dec/inc", "___/inc5", "dec/___1", "___/dec5"};

    }


    public static Player getInstance() {
        playersCounter++;

        switch (playersCounter){
            case 1 :
                return p1;
            case 2:
                return p2;
            case 3:
                return p3;
            case 4:
                return p4;
        }
        return null;
    }

    public void setter(String name, int points){
        this.name = name;
        this.points = points;
    }

    public static boolean playerChecker(Player player){
        return player != null;
    }

}
