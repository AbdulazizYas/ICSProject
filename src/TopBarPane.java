import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TopBarPane extends BasePane{
	
	private Stage stage;
	private double xOff = 0;
	private double yOff = 0;
	private Label title ;
	private Label X = new Label("X");
	private Label maximize = new Label("\u25A0");
	private Label minimize = new Label("\u2501");
	
	public TopBarPane(Stage stage,String title) {
		this.stage = stage;
		this.title = new Label("Questioner || "+title);
		
		buildLayout();
	}
	
	@Override
	protected void buildLayout() {
		HBox buttons = new HBox(10);
		X.setFont(Font.font("Calbiri", 16));
		maximize.setFont(Font.font("Calbiri", 16));
		minimize.setFont(Font.font("Calbiri", 16));
		
		X.setPadding(new Insets(5,20,5,20));
		maximize.setPadding(new Insets(5,20,5,20));
		minimize.setPadding(new Insets(5,20,5,20));
		
		
		X.setStyle(Constants.cursor);
		maximize.setStyle(Constants.cursor);
		minimize.setStyle(Constants.cursor);
		
		X.setOnMouseEntered(e -> X.setStyle(Constants.cursor+ "-fx-background-color:#DF362D"));
		X.setOnMouseExited(e -> X.setStyle(Constants.cursor+"-fx-background-color: transparent"));
		
		maximize.setOnMouseEntered(e -> maximize.setStyle(Constants.cursor+ Constants.bgPrimary+"55"));
		maximize.setOnMouseExited(e -> maximize.setStyle(Constants.cursor+"-fx-background-color: transparent"));
		
		minimize.setOnMouseEntered(e -> minimize.setStyle(Constants.cursor+ Constants.bgPrimary+"55"));
		minimize.setOnMouseExited(e -> minimize.setStyle(Constants.cursor+"-fx-background-color: transparent"));
		
		
		X.setOnMouseClicked(e -> stage.close());
		maximize.setOnMouseClicked(e -> {
			if(stage.isMaximized())stage.setMaximized(false);
			else stage.setMaximized(true);
		});
		minimize.setOnMouseClicked(e -> stage.setIconified(true));
		
		X.setTextFill(Color.valueOf("#FFF"));
		maximize.setTextFill(Color.valueOf("#FFF"));
		minimize.setTextFill(Color.valueOf("#FFF"));
		
		this.prefWidthProperty().bind(stage.widthProperty());
		this.setPrefHeight(32);
		this.setStyle(Constants.bgPrimary+"aa");
		this.setRight(buttons);
		this.setLeft(this.title);
		
		this.title.setFont(Font.font("Calbiri", FontWeight.BOLD, 12));
		this.title.setTextFill(Color.valueOf("#FFF"));
		this.title.setPadding(new Insets(0,0,0,10));
		BorderPane.setAlignment(this.title, Pos.CENTER);
		
		buttons.getChildren().addAll(minimize,maximize,X);
	
		this.setOnMousePressed(e -> {
			xOff = e.getSceneX();
			yOff = e.getSceneY();
		});
		this.setOnMouseDragged(e -> {
			if(stage.isMaximized()) stage.setMaximized(false);
			stage.setX(e.getScreenX() - xOff);
			stage.setY(e.getScreenY() - yOff);
		});
		
	}

}
