package Game;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


/**
 * Created by Miguel Angel on 13/06/2017.
 */
public class ProviderManager {

    static HashMap<String, UserSocket> usersConnections = new HashMap<>();
    static HashMap<String, String> userRelationGame = new HashMap<>();
    static ServerSocket providerSocket;
    static Socket client = null;
    static ObjectOutputStream out;
    static ObjectInputStream in;
    static Message messageAction;
    static Gson jsonSerializer = new Gson();

    public static void main(String[] args) {

        try {

            //1. creating a server socket
            providerSocket = new ServerSocket(2004, 10);

            //2. Wait for any connection
            System.out.println("Waiting for connection");

            while (true) {

                client = providerSocket.accept();
                System.out.println("Connection received from " + client.getInetAddress().getHostName());

                //3. get Input and Output streams
                out = new ObjectOutputStream(client.getOutputStream());
                out.flush();
                in = new ObjectInputStream(client.getInputStream());
                System.out.println("Connection successful");

                boolean communicationActived = true;

                while (communicationActived) {
                    messageAction = jsonSerializer.fromJson((String)in.readObject(), Message.class);

                    switch (messageAction.getAction()) {

                        case "RegisterUser":
                            saveConnection(messageAction, client, out, in);
                            out.writeObject(jsonSerializer.toJson(true));

                            //closeConnection(out, in);
                            communicationActived = false;
                            break;

                        case "Invitation":

                            saveConnection(messageAction, client, out, in);

                            if (isSocketCreated(messageAction.getUserGuest())) {

                                UserSocket socketH = usersConnections.get(messageAction.getUserHome());
                                UserSocket socketG = usersConnections.get(messageAction.getUserGuest());

                                GameManager game = new GameManager(socketH, socketG);
                                game.startGameFlow();
                                communicationActived = !communicationActived;
                            }
                            out.writeObject(jsonSerializer.toJson(true));
                            //closeConnection(out, in);
                            communicationActived = false;
                            break;

                        default:
                            System.out.println("Essa opción no existe");
                    }
                }

            }

        } catch (Exception ex) {

        }

    }

    private static boolean isSocketCreated(String username)
    {
        Socket socketGuest = usersConnections.get(username).userConnection;
        return socketGuest != null;
    }

    private static void closeConnection(ObjectOutputStream out, ObjectInputStream in) throws IOException {

        out.close();
        in.close();
    }

    private static void saveConnection(Message messageAction, Socket client, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        UserSocket socketToSave = new UserSocket(messageAction.getUserHome() ,client, out, in);
        usersConnections.put(messageAction.getUserHome(), socketToSave);
    }

}
