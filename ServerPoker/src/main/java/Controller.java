import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller class for the fxml files and to update the GUI
 * and all the event handlelers 
 * 
 * One Controller class to control all the scene 
 */
public class Controller implements Initializable {
    private ServerCode server ; 

    @FXML
    private TextField serverPortTextField;
    @FXML
    private Button connectButton; 
    @FXML
    private VBox ServerPowerBox;
    @FXML
    private RadioButton on;
    @FXML
    private RadioButton off;
    @FXML
    private int clientCount = 0;
    @FXML
    private VBox clientVBox;
    @FXML
    private Label clientConnectedPrompt;
    @FXML
    private Label serverPowerLabel;
    @FXML
    private Label serverPortLabel;
    @FXML
    private Label CardPokerText;
    @FXML
    private Label welcomeText;
    @FXML
    private BorderPane rootpaneScene1; 
    @FXML
    private BorderPane rootpaneScene2;
    
    private Controller2 ctr = new Controller2();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServerPlay.fxml"));

    
    
    
    
    public void connectButtonClicked(ActionEvent e) throws IOException{
        // change scenes  
        connectButton.setDisable(true);
        loadNextScene();
        ctr = loader.getController();
        ctr.initialize();
        server = new ServerCode(data -> {
            Platform.runLater(()->{
                // update the gui ????

                // PokerInfo pokerInfo = (PokerInfo) data;
                // updateGUI(pokerInfo); 
                // FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServerPlay.fxml"));
                // Controller2 ctr = loader.getController();
                // ctr.updateGui(data.toString());
                // ctr.updateGui(data.toString());
                ctr.updateGui("les goo");

                
                System.out.println("runnlater was called");
                
            });
        }); 

        // makeFadeOut() ;
    }

    // public String updateGUI(PokerInfo pokerInfo){
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ServerPlay.fxml"));
    //     Controller2 ctr =  loader.getController();
    //     String value = "" ; 
    //     if (pokerInfo.getStatus() == "innitilaized"){
    //         value = "Player " + pokerInfo.getClientCount() + " joined the game."; 
    //     } else if (pokerInfo.getStatus() == "bet made"){
    //         value = "Player "+ pokerInfo.getClientCount()+" made an ante wager of $" + pokerInfo.getAnteWager() ; 
    //         if (pokerInfo.isPairPlusBetMade()){
    //             value += " and a pair plus wager of $" + pokerInfo.getPairPlus() ; 
    //         }else {
    //             value += " and did not make a pair plus wager.\n" ;
    //         }
    //     } else if (pokerInfo.getStatus() == "cards drawn"){
    //         value = "Player "+ pokerInfo.getClientCount()+" has " + pokerInfo.getPlayerCardsAString();
    //         value += "Player "+ pokerInfo.getClientCount()+" dealer has " + pokerInfo.getDealerCardsAString();
    //     } else if (pokerInfo.getStatus() == "Play"){
    //         if (pokerInfo.isPlayerWon()){
    //             value = "Player "+ pokerInfo.getClientCount()+" has won $" + pokerInfo.getWinnings()+".";
    //             if (pokerInfo.isPairPlusBetMade()){
    //                 value += "\nPlayer "+ pokerInfo.getClientCount()+" has won Pair Plus Prize $" + pokerInfo.getPairPlusWinnings()+".";
    //             }
    //         }else {

    //         value = "Player " +  pokerInfo.getClientCount() + " lost $" + Integer.toString(pokerInfo.getAnteWager()+pokerInfo.getWager())+"."; 
    //         if (pokerInfo.isPairPlusBetMade()){
    //             value += "\nPlayer "+ pokerInfo.getClientCount()+" has won Pair Plus Prize $" + pokerInfo.getPairPlusWinnings()+".";
    //         }
    //     }
    //     } else if (pokerInfo.getStatus() == "Fold"){
    //         value = "Player " +  pokerInfo.getClientCount() + " folded and lost $" + Integer.toString(pokerInfo.getAnteWager()+pokerInfo.getWager()+pokerInfo.getPairPlus())+"."; 
    //     } else if (pokerInfo.getStatus() == "Play Again"){
    //         value = "Player " +  pokerInfo.getClientCount() + " wants to play again."; 
    //     } else if (pokerInfo.getStatus() == "Quit"){
    //         value = "Player " +  pokerInfo.getClientCount() + " has quit the game.";
    //     }

    //     ctr.updateGui(value);

    //     return value;  
    // }

    private void loadNextScene () {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource ("/fxml/ServerPlay.fxml"));
            Scene newScene = new Scene (root);
            newScene.getStylesheets().add("/styles/style2.css");
            Stage curStage = (Stage) rootpaneScene1.getScene ().getWindow();
            curStage.setScene (newScene);
            System.out.println("test");
        } catch (Exception e){

        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serverPortTextField.setText("5555");
    }

    // primary stage on close request
}
