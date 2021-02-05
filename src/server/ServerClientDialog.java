package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;

public class ServerClientDialog implements Runnable {
    private final Socket client;

    public ServerClientDialog(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {

            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            String requestAndroid = inputStream.readUTF();
            outputStream.writeUTF(checkCommand(requestAndroid));
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * * This method checks what command was sent from Android
     */
    public String checkCommand(String requestAndroid) {
        String responseToAndroid = "false";
        String[] array = requestAndroid.split(":");
        String retval = array[0];
        switch (retval) {
            case "1":
                try {
                   // responseToAndroid = String.valueOf(PostgresDB.checkUser(array[1], array[2]));
                    break;
                } catch (Exception e) {
                    return requestAndroid;
                }
            case "2":
               // responseToAndroid = String.valueOf(PostgresDB.createUser(array[1], array[2], array[3]));
                break;

        }
        return responseToAndroid;
    }

}
