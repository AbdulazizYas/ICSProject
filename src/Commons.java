import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//theme1 {#9DA993,#E3E8E9,#BCA88E} | theme2 {11698e,f8f1f1,16c79a}
public class Commons {
	public static final boolean customBar = true;
	public static boolean darkMode = false;
	public static final String primaryColor = darkMode? "#393e46": "#045762";
	public static final String secondaryColor = darkMode?"#232931":"#f3f2da";
	public static final String accentColor = darkMode?"#4ecca3":"#4e8d7c";
	public static final String shadow = "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.20) , 2, 0.0 , 0 , 3 );";
	public static final String innerShadow = "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.25) , 2, 0.25 , 0 , 2 );";
	public static final String btn = shadow + "-fx-cursor: hand;";
	public static final String bgPrimary = "-fx-background-color: "+primaryColor;
	public static final String bgSecondary = "-fx-background-color: "+secondaryColor;
	public static final String bgAccent = "-fx-background-color: "+accentColor;
	public static final String title = "-fx-background-color: "+primaryColor+"; -fx-text-fill: #fdfdfd;-fx-background-radius: 50;";
	public static final String cursor = "-fx-cursor: hand;";
	public static final String bgWhite = "-fx-background-color: " +  secondaryColor +";";
	public static final String textField = "-fx-font-size: 18; -fx-border-color: transparent; -fx-border-width: 0; -fx-border-radius: 16; -fx-border: gone; -fx-background-color: "+ (darkMode?primaryColor:"#fdfdfd")+"; -fx-text-fill: "+(darkMode? "#fdfdfd" : "#202020")+";";
	public static final String primaryGradient = /*darkMode? bgPrimary :*/ "-fx-background-color: linear-gradient(to top,"+primaryColor+ " 2%, "+primaryColor+"cc 98%);";
	public static final String font = "Arial";
	public static final int sceneWidth = 950;
	public static final int sceneHeight = 600;
	
	public static void openAlert(String title,String msg, AlertType type) {
		Alert alert = new Alert(type);
		 alert.setTitle(title);
		 alert.setContentText(msg);
		 alert.showAndWait();
	}
	
	public static void updateAllCombos(ArrayList<Question> qsList) {
		Main.editScene.getSelectPane().getCombo().setItems(SelectionPane.getFormattedList(qsList));
		Main.viewScene.getSelectPane().getCombo().setItems(SelectionPane.getFormattedList(qsList));
		Main.deleteScene.getSelectPane().getCombo().setItems(SelectionPane.getFormattedList(qsList));
	}
}
