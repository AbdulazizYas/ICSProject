import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Commons {
	public static final String primaryColor = "#2F5061";
	public static final String secondaryColor = "#C4DBE0";
	public static final String accentColor = "#4297A0";
	public static final String btn = "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.20) , 2, 0.0 , 0 , 2 );"
									+ "-fx-cursor: hand;";
	public static final String bgPrimary = "-fx-background-color: "+primaryColor;
	public static final String bgSecondary = "-fx-background-color: "+secondaryColor;
	public static final String bgAccent = "-fx-background-color: "+accentColor;
	public static final String cursor = "-fx-cursor: hand;";
	public static final boolean customBar = false;
	
	public static void openAlert(String title,String msg, AlertType type) {
		Alert alert = new Alert(type);
		 alert.setTitle(title);
		 alert.setContentText(msg);
		 alert.showAndWait();
	}
}
