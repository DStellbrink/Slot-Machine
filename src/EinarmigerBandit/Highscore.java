package EinarmigerBandit;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

public class Highscore {
	
	private static String dataPath = "highscore/score.dat";
	static String fileName = "score.dat";

	public static void newHighscore (int credit) {

		fileExists();
			
		try (RandomAccessFile datei = new RandomAccessFile(dataPath,"rw")){	
			datei.writeInt(credit);
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was a problem saving the highscore.", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public static int getHighscore () {
		
		int tempHighscore = 0;
		
		fileExists();
		
		try (RandomAccessFile file = new RandomAccessFile(dataPath,"r")){
			if (file.length() >0)
				tempHighscore = file.readInt();		
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There was a problem loading the highscore.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return tempHighscore;
	}
	
	private static void fileExists() {
		
		File dateiTest = new File(dataPath);
		if (dateiTest.exists() == false) {
			try {
				dateiTest.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
