import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

//this pane for custom top bar 
public class TopBarPane extends BorderPane implements BasePane{
	
	private Stage stage;
	//offset used to move the stage when the top bar is being dragged
	private double xOff = 0; 
	private double yOff = 0;
	private Label title ;
	//the symbols of the top bar
	private Label X = new Label("X");
	private Label maximize = new Label("\u25FC");
	private Label minimize = new Label("\u2501");
	
	public TopBarPane(Stage stage,String title) {
		this.stage = stage;
		this.title = new Label("Questioner || "+title); //this title is received by SelectionPane or CreateScene
	
		//build the layout of this pane
		buildPane();
	}
	
	@Override
	public void buildPane() {
		//HBox for the buttons of (X, square,-)
		HBox buttons = new HBox(10);
		
		X.setPadding(new Insets(5,20,5,20));
		maximize.setPadding(new Insets(5,20,5,20));
		minimize.setPadding(new Insets(5,20,5,20));
		
		//set the default style for each button
		X.setStyle(Commons.cursor + "-fx-font-size:16; -fx-text-fill:#FFF");
		maximize.setStyle(Commons.cursor + "-fx-font-size:16; -fx-text-fill:#FFF");
		minimize.setStyle(Commons.cursor + "-fx-font-size:16; -fx-text-fill:#FFF");
		
		//set the style when cursor hover each button
		X.setOnMouseEntered(e -> X.setStyle(Commons.cursor+ "-fx-background-color:#DF362D;-fx-font-size:16; -fx-text-fill:#FFF"));
		X.setOnMouseExited(e -> X.setStyle(Commons.cursor+"-fx-background-color: transparent;-fx-font-size:16; -fx-text-fill:#FFF"));
		maximize.setOnMouseEntered(e -> maximize.setStyle(Commons.cursor+ Commons.bgPrimary+"55;-fx-font-size:16; -fx-text-fill:#FFF"));
		maximize.setOnMouseExited(e -> maximize.setStyle(Commons.cursor+"-fx-background-color: transparent;-fx-font-size:16; -fx-text-fill:#FFF"));
		minimize.setOnMouseEntered(e -> minimize.setStyle(Commons.cursor+ Commons.bgPrimary+"55;-fx-font-size:16; -fx-text-fill:#FFF"));
		minimize.setOnMouseExited(e -> minimize.setStyle(Commons.cursor+"-fx-background-color: transparent;-fx-font-size:16; -fx-text-fill:#FFF"));
				
		X.setOnMouseClicked(e -> Platform.exit()); // if X is clicked exit the app
		
		maximize.setOnMouseClicked(e -> {
			if(stage.isMaximized())stage.setMaximized(false); 
			else stage.setMaximized(true); // if maximize is clicked and it is not maximize then maximize it
		});
		
		minimize.setOnMouseClicked(e -> stage.setIconified(true));// make the stage hidden in taskbar
		
		//style the title of the scene that at the left of the pane
		this.title.setFont(Font.font(Commons.font, FontWeight.BOLD, 12));
		this.title.setTextFill(Color.valueOf("#FFF"));
		this.title.setPadding(new Insets(0,0,0,10));
		BorderPane.setAlignment(this.title, Pos.CENTER);
		
		//set icon for the stage
		ImageView icon = new ImageView("logo2.png");
		icon.setFitWidth(18);icon.setFitHeight(18);
		this.title.setGraphic(icon);
		
		//style and structure the TopBarPAne
		this.prefWidthProperty().bind(stage.widthProperty());
		this.setPrefHeight(32);
		this.setStyle(Commons.bgPrimary+"cc");
		this.setRight(buttons);
		//this.setCenter(checkPane);
		this.setLeft(this.title);
		
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

