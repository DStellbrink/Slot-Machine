package EinarmigerBandit;

import java.net.URL;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

// All Images from freepik.com
// Slot Machine Icons:
// http://www.freepik.com">Designed by Freepik

// Slot-Machine Background:
// http://www.freepik.com">Designed by katemangostar

public class FXMLController implements Initializable{
	@FXML private Label credit;
	@FXML private Label dice1;
	@FXML private Label dice2;
	@FXML private Label dice3;
	@FXML private Label highscore;
	
	@FXML private ImageView iconDice1;
	@FXML private ImageView iconDice2;
	@FXML private ImageView iconDice3;
	@FXML private ImageView iconBackground; 
	
	@FXML private Button play;
	@FXML private Button newGame;
	@FXML private Button close;
	
	private int creditAmount;
	
	private String dataPath = "C:\\Users\\osliw\\eclipse-workspaceNeu\\EinarmigerBanditFX\\pictures\\";
	
	private String stringNumberDice1, stringNumberDice2, stringNumberDice3;
	
	private int count = 0;
	
	private MotionBlur blur = new MotionBlur();
	private InnerShadow shadow = new InnerShadow();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JOptionPane.showMessageDialog(null, "You've 100 Euro credit.\nA game costs 10 Euro per round.", 
				"Welcome!", JOptionPane.INFORMATION_MESSAGE);
		
		resetGame();
		
		// set events
		close.setOnAction(event -> closeGame());
		play.setOnAction(event -> playGame());
		newGame.setOnAction(event -> resetGame());	
	}

	private void playGame () {
		count = 0;
		Timeline timeline = new Timeline((new KeyFrame(Duration.millis(500), event ->{
			count++;
			// change effect
			dice1.setEffect(blur);
			dice2.setEffect(blur);
			dice3.setEffect(blur);
			play.setDisable(true);
			
			if (count == 2) {
				// change effect
				dice1.setEffect(shadow);
				dice2.setEffect(shadow);
				dice3.setEffect(shadow);
				play.setDisable(false);
				
				// determine and allocate numbers
				stringNumberDice1 = Gameplay.randomNumber();
				stringNumberDice2 = Gameplay.randomNumber();
				stringNumberDice3 = Gameplay.randomNumber();
				
				iconDice1.setImage(new Image(dataPath + stringNumberDice1 + ".png"));
				iconDice2.setImage(new Image(dataPath + stringNumberDice2 + ".png"));
				iconDice3.setImage(new Image(dataPath + stringNumberDice3 + ".png"));
				
				// update credit
				creditAmount = Gameplay.play(creditAmount, stringNumberDice1, stringNumberDice2, stringNumberDice3);
				credit.setText(" Credit: " + creditAmount + " EUR");		
			}
		})));
		timeline.setCycleCount(2);
		timeline.play();
		
		if (creditAmount == 0) {
			JOptionPane.showMessageDialog(null, "You have got no more credit!", "You Lost!", JOptionPane.INFORMATION_MESSAGE);
			play.setDisable(true);
		}
	}
	
	private void resetGame() {
		// reset all
		credit.setText(" Credit: 100 EUR");
		newHighscore(creditAmount);
		highscore.setText(" Highscore: " + Highscore.getHighscore() + "  EUR");
		creditAmount = 100;
		
		play.setDisable(false);
		
		iconDice1.setImage(new Image(dataPath + "three.png"));
		iconDice2.setImage(new Image(dataPath + "three.png"));
		iconDice3.setImage(new Image(dataPath + "three.png"));	
	}

	public void newHighscore (int credit) {	
		// at the end of the game it is checked whether there is a new high score
		if (credit > Highscore.getHighscore()== true) {
			Highscore.newHighscore(creditAmount);
			JOptionPane.showMessageDialog(null, "Congratulation! You got the Highscore", "New Highscore", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void closeGame(){
		newHighscore(creditAmount);
		Platform.exit();
	}
	
}
