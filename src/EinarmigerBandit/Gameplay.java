package EinarmigerBandit;

public class Gameplay  {
	
	static int play(int creditAmount, String stringNumberDice1, String stringNumberDice2, String stringNumberDice3) {
		
		// check for profit
		if (stringNumberDice1 == stringNumberDice2 && 
				stringNumberDice2 == stringNumberDice3) {
			creditAmount = creditAmount + 100;
		}

		else if (stringNumberDice1 == stringNumberDice2 || 
				stringNumberDice2 == stringNumberDice3 || 
				stringNumberDice1 == stringNumberDice3) {
			creditAmount = creditAmount + 20;
		}		
		return creditAmount-10;
	}
	
	public static String randomNumber() {
		
		String numbers [] = {"one", "two", "three", "four", "five", "six"};
	// normalerweise müßte bei Math.random()*6 noch 1 addiert werden um auf die 
	// Zahlen 1-6 zu kommen, da für das Array allerdings wieder 1 abgezogen werden 
	// müßte fällt es weg
	return numbers[(int)(Math.random()*6)];
	}

}
