module EinarmigerBanditFX{
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;

	opens EinarmigerBandit to javafx.controls, javafx.base, javafx.graphics, javafx.fxml;
}