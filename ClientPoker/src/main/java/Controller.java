import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller class for the fxml files and to update the GUI
 * and all the event handlelers 
 * 
 * One Controller for all the Scene in the Client side application 
 */
public class Controller {
    
    // welcome fx:ids

        @FXML
        private Label CardPokerLabel;
    
        @FXML
        private Button ConnectButton;
    
        @FXML
        private Label EnterIPLabel;
    
        @FXML
        private TextField ipTextfield;
    
        @FXML
        private Label portLabel;
    
        @FXML
        private TextField portTextfield;
    
        @FXML
        private BorderPane rootPane;
    
        @FXML
        private Label welcomeLabel;

        private Stage stage;
        private Scene scene;
        private Parent root;

    
    // @FXML
    // private Label infoLabel;

    private ClientSideThread ClientConnection ;
    public ClientSideThread getClientConnection() {
        return ClientConnection;
    }

    public void setClientConnection(ClientSideThread clientConnection) {
        ClientConnection = clientConnection;
    }

    private PokerInfo pokerInfo; 

    /**
     * Welcome scene
     * @param e
     * @throws IOException
     */
    public void ipTextMethod(ActionEvent e) throws IOException{
        System.out.println(ipTextfield.getText()); 
    }

    /**
     * Welcome Scene
     * @param e
     * @throws IOException
     */
    public void portTextMethod(ActionEvent e) throws IOException{
        System.out.println(portTextfield.getText()); 

    }


    public void updateGUI(){
        // update the GUI 
        
    }


    /**
     * Welcome Scene
     * @param e
     * @throws IOException
     */
    public void connectButtonMethod(ActionEvent e) throws IOException{
        System.out.println("Connect Button Pressed"); 
        int port = Integer.valueOf(portTextfield.getText()); 
        String host = ipTextfield.getText();

        
        
        try{
            ClientConnection = new ClientSideThread(data -> {
                Platform.runLater(()->{
                    // update the gui ????
                    /* general steps for updating the gui
                    * pull info from server : DONE
                    * check to see who won 
                    * display what happened that round 
                    * add a next button??
                    * display win or loss scene
                    * replay or exit
                    * 
                    */
                    pokerInfo = (PokerInfo) data;
                    if(pokerInfo.isPlayerWon()){
                        
                    }
                    
                    
                    System.out.println("runlater was called");
                });
            }, host, port );

            try {
                ClientConnection.clientStart();
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            ClientConnection.start();
            
            // disable the button textfields 
            ConnectButton.setDisable(true);
            ipTextfield.setDisable(true);
            portTextfield.setDisable(true);
            
            loadNextScene();
            
        }
        catch(Exception ex){
            System.out.println("Error the connection was not made");
        }
    }

    private void loadNextScene() {
        try {
            if(ClientConnection == null){
                System.out.println("ClientConnection is null");
            }
            else{
                
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/fxml/playScene.fxml"));
                Scene newScene = new Scene(root);
                // newScene.getStylesheets().add("/styles/style2.css");
                Stage curStage = (Stage) rootPane.getScene().getWindow();
                curStage.setScene(newScene);
            }
        } 
        catch (IOException e) {

        }

    }

}
