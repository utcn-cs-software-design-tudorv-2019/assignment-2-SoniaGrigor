package model.persistence.my_utility;

import java.io.*;

import static model.persistence.my_utility.ProjectConstants.LOGGED_USER_FILE;

public class UtilityAuthorization {

    public static int getLoggedUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(LOGGED_USER_FILE));
            String currentLine = reader.readLine();
            reader.close();
            return Integer.parseInt(currentLine);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void setLoggedUser(int id) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(LOGGED_USER_FILE, false));
        writer.write(String.valueOf(id));
        writer.close();

    }

    public static int getUserRole(String username) {
        if (username.contains("admin") || username.contains("teacher")) {
            return 1;
        } else {
            return 2;
        }
    }
}
