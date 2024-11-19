import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class playController {

    @FXML
    private Label anteWagerLabel;

    @FXML
    private ImageView dealer1;

    @FXML
    private ImageView dealer2;

    @FXML
    private ImageView dealer3;

    @FXML
    private Label dealerCardsLabel;

    @FXML
    private MenuItem exit;

    @FXML
    private Button foldButton;

    @FXML
    private MenuItem fresh;

    @FXML
    private Label gameInfoLabel;

    @FXML
    private Label gameStatusLabel;

    @FXML
    private TextField gameStatusTextField;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem newLook;

    @FXML
    private Menu options;

    @FXML
    private TextField pairPlusTextField;

    @FXML
    private TextField anteWagerText; 

    @FXML
    private Button playButton;

    @FXML
    private Label playWagerLabel;

    @FXML
    private ImageView player1;

    @FXML
    private ImageView player2;

    @FXML
    private ImageView player3;

    @FXML
    private Label playerCards;

    @FXML
    private TextField winningsBox;

    @FXML
    private Label winningsLabel;

    @FXML
    void exitClicked(ActionEvent event) {

    }

    @FXML
    void foldButtonClicked(ActionEvent event) {

    }

    @FXML
    void frreshClicked(ActionEvent event) {

    }

    @FXML
    void newLookClicked(ActionEvent event) {

    }

    @FXML
    void optionsClicked(ActionEvent event) {

    }

    @FXML
    void playButtonClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/welcomeScene.fxml"));
        Controller ctr = loader.getController();
        PokerInfo p = ctr.getClientConnection().getPokerInfo(); 
        p.setAnteWager(Integer.valueOf(anteWagerText.getText()));
        p.setPairPlusWager(Integer.valueOf(pairPlusTextField.getText()));
        if (Integer.valueOf(pairPlusTextField.getText()) == 0 ){
            p.setPairPlusBetMade(false);
        } else {
            p.setPairPlusBetMade(true);

        }
        ctr.getClientConnection().setPokerInfo(p);
        
        playButton.setText("Play");
        System.out.println("deal called");
    }

}
