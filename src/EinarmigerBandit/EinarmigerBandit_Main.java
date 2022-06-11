package EinarmigerBandit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EinarmigerBandit_Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("sb_bandit.fxml"));

		Scene meineScene = new Scene(root, 600,400);
		
		stage.setTitle("Slot Machine");
		stage.setScene(meineScene);

		stage.show();
		
	}

}
