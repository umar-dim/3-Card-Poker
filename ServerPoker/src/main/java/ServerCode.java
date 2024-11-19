import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;

/**
 * This is main server class
 * When an object of this class is created it will create
 * a server thread , host a server , and listens for clients
 * If a client connects to the server then it create a new
 * client thread object and stores them in an arraylist
 * 
 * This object will be created in the controller class
 * 
 */
public class ServerCode {

    private Consumer<Serializable> callback;
    private ServerCode.ServerThread server;
    private ArrayList<ClientThread> clients = new ArrayList<>();
    private int Clientcount = 1;

    /**
     * Constructor
     * 
     * @param call
     */
    ServerCode(Consumer<Serializable> call) {

        callback = call;
        server = new ServerThread(); // create a server thread
        server.start(); // start the thread
        System.out.println("server Thread is created");
    }

    /**
     * This is the server thread
     * It will host a server and listen for clients
     * If a client connects to the server then it will create a new client thread
     * and store it in an arraylist
     */
    public class ServerThread extends Thread {

        public void run() {
            try (ServerSocket mysocket = new ServerSocket(5555);) { // host the server
                System.out.println("Server is waiting for a client!");

                while (true) {
                    ClientThread newClient = new ClientThread(mysocket.accept(), Clientcount); // blocking call
                    // callback.accept("client has connected to server: " + "client #" +
                    // Clientcount);
                    System.out.println("client has connected and new client thread is made");
                    clients.add(newClient);
                    newClient.start();
                    Clientcount++;

                }
            } // end of try
            catch (Exception e) {
                System.out.println("exception");
                // callback.accept("Server socket did not launch");
            }
        }

    }

    /**
     * This is the client thread
     * When an object is created the it creats an new thread
     * for the client to run in
     */
    public class ClientThread extends Thread {

        Socket connection;
        int Clientcount;
        ObjectInputStream in;
        ObjectOutputStream out;
        private PokerInfo pokerInfo;

        /**
         * Constructor
         * 
         * @param socket
         * @param Clientcount
         */
        ClientThread(Socket socket, int Clientcount) {
            this.connection = socket;
            this.Clientcount = Clientcount;
            pokerInfo = new PokerInfo(Clientcount);
        }

        public void run() {
            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);

                // SEND POKERINFO TO THE CLIENT 
                out.writeObject(pokerInfo);
                callback.accept(updateGUI(pokerInfo));
            } catch (Exception e) {
                System.out.println("Streams not open");
            }

            // TODO : callback.accept to update gui in server
            while (true) {

                try {
                    pokerInfo = (PokerInfo) in.readObject();
                    // TODO : make call to call back 

                    callback.accept(updateGUI(pokerInfo));
                    doCalcutation(pokerInfo);
                    callback.accept(updateGUI(pokerInfo));
                    out.writeObject(pokerInfo);
                } catch (Exception e) {

                }
                // try {
                // } catch (IOException e) {
                //     // TODO Auto-generated catch block
                //     System.out.println("exception in sending object to client");
                //     e.printStackTrace();
                // }
                // try {
                //     // out.writeObject(pokerInfo);

                //     pokerInfo = (PokerInfo) in.readObject();

                //     // pokerInfo = (PokerInfo) out.writeObject(pokerInf);

                //     // callback.accept(pokerInfo);
                //     // FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServerPlay.fxml"));
                //     // Controller2 ctr = loader.getController();
                //     // ctr.updateGui(updateGUI(pokerInfo));
                //     callback.accept(updateGUI(pokerInfo));
                //     // System.out.println("Client #" + Clientcount + " has sent a message of: " +
                //     // pokerInfo.getStatus());
                //     // System.out.println(pokerInfo.getDeckofCardString());
                // } catch (Exception e) {
                //     // callback.accept("Client #" + Clientcount + " has left the server");
                //     // break;
                // }

            // }
        }
    }

    private void doCalcutation(PokerInfo pokerInfo2) {
        gameLogic game = new gameLogic(); 

        if (pokerInfo2.getStatus() == "innitilaized") {
        } else if (pokerInfo2.getStatus() == "bet made") {
            pokerInfo2 = game.drawCards(pokerInfo2); 
            pokerInfo2.setStatus("cards drawn");
        } else if (pokerInfo2.getStatus() == "cards drawn") {
        } else if (pokerInfo2.getStatus() == "Play") {
            pokerInfo2 = game.getWinnings(pokerInfo2);
            pokerInfo2.setStatus("outcome");
        } else if (pokerInfo2.getStatus() == "Fold") {
        } else if (pokerInfo2.getStatus() == "Play Again") {
            pokerInfo2 = new PokerInfo(pokerInfo2.getClientCount()); 
        } else if (pokerInfo2.getStatus() == "Quit") {
        }
    }

    public void sendDataToClient(){

    }

    // TODO make a callback.accept using this function every time we change the
    // status and
    // TODO every time we get an input form the client
    public String updateGUI(PokerInfo pokerInfo) {
        String value = "";
        if (pokerInfo.getStatus() == "innitilaized") {
            value = "Player " + pokerInfo.getClientCount() + " joined the game.";
        } else if (pokerInfo.getStatus() == "bet made") {
            value = "Player " + pokerInfo.getClientCount() + " made an ante wager of $" + pokerInfo.getAnteWager();
            if (pokerInfo.isPairPlusBetMade()) {
                value += " and a pair plus wager of $" + pokerInfo.getPairPlus();
            } else {
                value += " and did not make a pair plus wager.\n";
            }
        } else if (pokerInfo.getStatus() == "cards drawn") {
            value = "Player " + pokerInfo.getClientCount() + " has " + pokerInfo.getPlayerCardsAString();
            value += "Player " + pokerInfo.getClientCount() + " dealer has " + pokerInfo.getDealerCardsAString();
        } else if (pokerInfo.getStatus() == "Play") {
            if (pokerInfo.isPlayerWon()) {
                value = "Player " + pokerInfo.getClientCount() + " has won $" + pokerInfo.getWinnings() + ".";
                if (pokerInfo.isPairPlusBetMade()) {
                    value += "\nPlayer " + pokerInfo.getClientCount() + " has won Pair Plus Prize $"
                            + pokerInfo.getPairPlusWinnings() + ".";
                }
            } else {

                value = "Player " + pokerInfo.getClientCount() + " lost $"
                        + Integer.toString(pokerInfo.getAnteWager() + pokerInfo.getWager()) + ".";
                if (pokerInfo.isPairPlusBetMade()) {
                    value += "\nPlayer " + pokerInfo.getClientCount() + " has won Pair Plus Prize $"
                            + pokerInfo.getPairPlusWinnings() + ".";
                }
            }
        } else if (pokerInfo.getStatus() == "Fold") {
            value = "Player " + pokerInfo.getClientCount() + " folded and lost $"
                    + Integer.toString(pokerInfo.getAnteWager() + pokerInfo.getWager() + pokerInfo.getPairPlus()) + ".";
        } else if (pokerInfo.getStatus() == "Play Again") {
            value = "Player " + pokerInfo.getClientCount() + " wants to play again.";
        } else if (pokerInfo.getStatus() == "Quit") {
            value = "Player " + pokerInfo.getClientCount() + " has quit the game.";
        }

        return value;
    }
}
}