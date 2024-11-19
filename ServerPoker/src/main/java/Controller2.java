import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class Controller2 {

    @FXML
    private ListView<String> liveFeed;

    @FXML
    private Label liveFeedLabel;

    @FXML
    private VBox rootpaneScene2;

    public void updateGui(String string){
        liveFeed.getItems().add(string);
    }

}