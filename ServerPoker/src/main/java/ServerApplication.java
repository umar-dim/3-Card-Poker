import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main file that starts the appliccation and 
 * runs the GUI
 */
public class ServerApplication extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
			// System.out.println("test");
			// Read file fxml and draw interface.
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/ServerWelcome.fxml"));
			primaryStage.setTitle("3-Card Poker Server");
			Scene s1 = new Scene(root, 700,700);
            s1.getStylesheets().add("/styles/style1.css");
			primaryStage.setScene(s1);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}	
    }
    
}
