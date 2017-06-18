package Game;

import Model.Eetakemon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Miguel Angel on 13/06/2017.
 */
public class UserSocket {

    private String username;
    private Eetakemon eetakemon;
    private ActionAtack actionAtack;

    public Socket userConnection;
    public ObjectOutputStream out;
    public ObjectInputStream in;


    public UserSocket() {
    }

    public UserSocket(String username, Socket socket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        this.username = username;
        userConnection = socket;
        this.out = out;
        this.in = in;
    }

    public UserSocket(String username, Socket socket) throws IOException {
        this.username = username;
        userConnection = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Model.Eetakemon getEetakemon() {
        return eetakemon;
    }

    public void setEetakemon(Model.Eetakemon eetakemon) {
        this.eetakemon = eetakemon;
    }

    public ActionAtack getActionAtack() {
        return actionAtack;
    }

    public void setActionAtack(ActionAtack actionAtack) {
        this.actionAtack = actionAtack;
    }
}
