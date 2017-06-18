package Game;

import Model.Eetakemon;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miguel Angel on 13/06/2017.
 */
public class GameManager {

    private UserSocket userHome;
    private UserSocket userGuest;
    private Gson jsonSerializer;
    private boolean isGameRunning = true;

    public GameManager(UserSocket userHome, UserSocket userGuest) {
        this.userHome = userHome;
        this.userGuest = userGuest;
        jsonSerializer = new Gson();
    }

    public boolean sendInvitation() throws IOException, ClassNotFoundException {

        userGuest.out.writeObject("You have a invitation game from " + userHome.getUsername());
        return jsonSerializer.fromJson((String) userGuest.in.readObject(), boolean.class);
    }

    public void reciveEetakemons() throws IOException, ClassNotFoundException {

        Eetakemon eetakemonHome = jsonSerializer.fromJson((String) userHome.in.readObject(), Eetakemon.class);
        Eetakemon eetakemonGuest = jsonSerializer.fromJson((String) userGuest.in.readObject(), Eetakemon.class);

        userHome.setEetakemon(eetakemonHome);
        userGuest.setEetakemon(eetakemonGuest);
    }

    public void sendEetakemons() throws IOException {

        Map<String, Eetakemon> eetakemons = new HashMap<>();

        eetakemons.put("userHome", userHome.getEetakemon());
        eetakemons.put("userGuest", userGuest.getEetakemon());

        userHome.out.writeObject(jsonSerializer.toJson(eetakemons));
        userGuest.out.writeObject(jsonSerializer.toJson(eetakemons));
    }

    public int calculateDamage() {
        return 5;
    }

    public void sendGameStatus() throws IOException {
        isGameRunning = !(userHome.getEetakemon().getPs() <= 0 || userHome.getEetakemon().getPs() <= 0);
        userHome.out.writeObject(jsonSerializer.toJson(isGameRunning));
        userGuest.out.writeObject(jsonSerializer.toJson(isGameRunning));
    }

    public void reciveAtack(UserSocket userAtacking, UserSocket userWaiting) throws IOException, ClassNotFoundException {
        ActionAtack actionUser = jsonSerializer.fromJson((String) userAtacking.in.readObject(), ActionAtack.class);
        userAtacking.setActionAtack(actionUser);

        userWaiting.getEetakemon().setPs(userWaiting.getEetakemon().getPs() - calculateDamage());
    }

    public void doTurn(UserSocket userAtacking, UserSocket userWaiting) throws IOException, ClassNotFoundException {

        reciveAtack(userAtacking, userWaiting);

        sendEetakemons();
    }

    public void sendResultEndGame() throws IOException {
        boolean isUserHomeWinner = userHome.getEetakemon().getPs() > 0;
        boolean isUserGuestWinner = userGuest.getEetakemon().getPs() > 0;

        userHome.out.writeObject(isUserGuestWinner);
        userGuest.out.writeObject(isUserGuestWinner);
    }

    public void registerResult() {

    }

    public void startGameFlow() throws IOException, ClassNotFoundException {

        boolean isAccepted = sendInvitation();
        userHome.out.writeObject(jsonSerializer.toJson(true));

        if (isAccepted) {
            reciveEetakemons();
            sendEetakemons();

            boolean userHomeTurn = true;
            boolean userGuestTurn = false;
            while (isGameRunning) {
                if (userHomeTurn) {
                    doTurn(userHome, userGuest);
                    sendGameStatus();
                }

                if (userGuestTurn) {
                    doTurn(userGuest, userHome);
                    sendGameStatus();
                }

                userHomeTurn = !userHomeTurn;
                userGuestTurn = !userGuestTurn;
            }

            sendResultEndGame();
            registerResult();
        }
    }
}
