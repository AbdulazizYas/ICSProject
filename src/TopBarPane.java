import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class TopBarPane extends BorderPane implements BasePane{
	
	private Stage stage;
	private double xOff = 0;
	private double yOff = 0;
	private Label title ;
	private Label X = new Label("X");
	private Label maximize = new Label("\u25FC");
	private Label minimize = new Label("\u2501");
	
	public TopBarPane(Stage stage,String title) {
		this.stage = stage;
		this.title = new Label("Questioner || "+title);
	
		buildPane();
	}
	
	@Override
	public void buildPane() {
		HBox buttons = new HBox(10);
		X.setFont(Font.font(16));
		maximize.setFont(Font.font(16));
		minimize.setFont(Font.font(16));
		
		X.setPadding(new Insets(5,20,5,20));
		maximize.setPadding(new Insets(5,20,5,20));
		minimize.setPadding(new Insets(5,20,5,20));
		
		
		X.setStyle(Commons.cursor);
		maximize.setStyle(Commons.cursor);
		minimize.setStyle(Commons.cursor);
		
		X.setOnMouseEntered(e -> X.setStyle(Commons.cursor+ "-fx-background-color:#DF362D"));
		X.setOnMouseExited(e -> X.setStyle(Commons.cursor+"-fx-background-color: transparent"));
		
		maximize.setOnMouseEntered(e -> maximize.setStyle(Commons.cursor+ Commons.bgPrimary+"55"));
		maximize.setOnMouseExited(e -> maximize.setStyle(Commons.cursor+"-fx-background-color: transparent"));
		
		minimize.setOnMouseEntered(e -> minimize.setStyle(Commons.cursor+ Commons.bgPrimary+"55"));
		minimize.setOnMouseExited(e -> minimize.setStyle(Commons.cursor+"-fx-background-color: transparent"));
		
		
		X.setOnMouseClicked(e -> Platform.exit());
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
		this.setStyle(Commons.bgPrimary+"aa;");
		this.setRight(buttons);
		this.setLeft(this.title);
		
		this.title.setFont(Font.font(Commons.font, FontWeight.BOLD, 12));
		this.title.setTextFill(Color.valueOf("#FFF"));
		this.title.setPadding(new Insets(0,0,0,10));
		BorderPane.setAlignment(this.title, Pos.CENTER);
		
		//Set Dragging the stage for the custom top bar
		this.setOnMousePressed(e -> {
			this.xOff = e.getSceneX();
			this.yOff = e.getSceneY();
		});
		this.setOnMouseDragged(e -> {
			stage.setX(e.getScreenX() - xOff);
            stage.setY(e.getScreenY() - yOff);
		});
		
		buttons.getChildren().addAll(minimize,maximize,X);
	
		
	}

}

