package com.lambda.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SCAUtil {
    static ArrayList < String > members = new ArrayList < > ();
    static ArrayList < String > enemies = new ArrayList < > ();
    static int index = 0;


    public static List < String > getEnemies() {
        try {
            URL url = new URL("https://sca.zenaxis.org/enemies");
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                enemies.add(line);
                
                log("Enemy: " + line);
            }

        } catch (IOException e) {
            log("API seems to be down... Keeping cached data");
        }

        return enemies;
    }

    public static List < String > getMembers() {
        try {
        URL url = new URL("https://sca.zenaxis.org/members");
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            members.add(line);
            
            log("Member: " + line);
        }
        scanner.close();

    } catch (IOException e) {
        log("API seems to be down... Keeping cached data");
    }

        return members;
    }

    static List < String > goodList = getMembers();
    static List < String > badList = getEnemies();
    public static boolean isFriendly(String username) {
        for (String GoodDude: goodList) {
            if (GoodDude.toLowerCase(Locale.ROOT).contains(username.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEnemy(String username) {
        for (String BadDude: badList) {
            if (BadDude.toLowerCase(Locale.ROOT).contains(username.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    public static void reload() {
        goodList = getMembers();
        badList = getEnemies();
    }

    public static String getName(String username) {
        for (String GoodDude: goodList) {
            if (GoodDude.toLowerCase(Locale.ROOT).contains(username.toLowerCase(Locale.ROOT))) {
                return GoodDude;
            }
        }

        for (String BadDude: badList) {
            if (BadDude.toLowerCase(Locale.ROOT).contains(username.toLowerCase(Locale.ROOT))) {
                return BadDude;
            }
        }

        return null;
    }

    public static void log(String msg) {
        System.out.println(msg);
    }


}