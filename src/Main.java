import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Stage stage;
	private Scene main;
	private Button back;
	private Button create;
	private Button edit;
	private Button view;
	private Button delete;
	private Label welcome;
	private Label choose;
	private Label copyright;
	private ArrayList<Question> qsList;
	private HBox pane;
	private GridPane buttons;
	private BorderPane welcomePane;
	private BorderPane allRightSide;
	private Background[] Bgs;
	public static final String primaryColor = "#0C6980";
	public static final String secondaryColor = "#C4DBE0";
	public static final String accentColor = "#00A8A8";
	public static final String btn = "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.20) , 2, 0.0 , 0 , 2 );"
									+ "-fx-cursor: hand;";
	
	@Override
	public void start(Stage stage) throws Exception {
		
		this.stage = stage;
		
		qsList = new ArrayList<>();
		
		back = new Button("Back");
		
		create = new Button("Create");
		edit = new Button("Edit");
		view = new Button("View");
		delete = new Button("Delete");
		
		welcome = new Label("Welcome \n to Questions Bank App!");
		choose = new Label("Choose your action");
		copyright = new Label("Questioner � Developed by Abdulaziz & Yousef");
		
		welcomePane = new BorderPane();
		pane = new HBox();
		buttons = new GridPane();
		allRightSide = new BorderPane();
		
		Bgs = getBGs();
		
		
		// -------  handle events ---------//
		back.setOnAction(e -> stage.setScene(this.main));
		create.setOnAction(e -> stage.setScene(new CreateScene(qsList,back)));
		edit.setOnAction(e -> stage.setScene(new EditScene(qsList,back)));
		
		/*-----------  Designing  ---------------*/
		designLayout();
		
		
		
		
		/*===========  Layouting =============*/
		buildLayout();

		
		

		main = new Scene(pane,700,500);
		stage.setScene(main);
		stage.setMinHeight(500);
		stage.setMinWidth(700);
		stage.show();
		
		

		
		
	}
	
	public Background[] getBGs() {
		BackgroundFill bg1 = new BackgroundFill(
				Color.valueOf(primaryColor),
                new CornerRadii(0),
                new Insets(0)
				);
		BackgroundFill bg2 = new BackgroundFill(
				Color.valueOf("#fdfdfd"),
                new CornerRadii(0),
                new Insets(0)
				);
		BackgroundFill bg3 = new BackgroundFill(
				Color.valueOf(accentColor),
                new CornerRadii(10),
                new Insets(0)
				);
		return new Background[] {new Background(bg1),new Background(bg2),new Background(bg3)};
	}
	
	public void designLayout() {
		
		welcome.setTextAlignment(TextAlignment.CENTER);
		welcome.setTextFill(Color.valueOf("#fefefe"));
		welcome.setFont(Font.font("Candara",FontWeight.BOLD, 35));
		welcome.setWrapText(true);
		
		choose.setAlignment(Pos.CENTER);
		choose.setTextFill(Color.valueOf("#FFF"));
		choose.setPadding(new Insets(10,5,10,5));
		choose.setStyle("-fx-background-color: " + primaryColor + "; -fx-background-radius: 50;"
						+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.25) , 5, 0.0 , 0 , 1 );");
		
		copyright.setAlignment(Pos.CENTER);
		
		//welcomePane.setBackground(Bgs[0]);
		welcomePane.setStyle("-fx-background-color: linear-gradient(to top right,"+primaryColor+ " 25%, "+primaryColor+"aa 75%);");
		
		allRightSide.setBackground(Bgs[1]);
		allRightSide.setStyle("-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.25) , 5, 0.0 , 2 , 0 );");
		
		create.setFont(Font.font(34));
		view.setFont(Font.font(34));
		delete.setFont(Font.font(34));
		edit.setFont(Font.font(34));
		
		create.setStyle(btn);
		view.setStyle(btn);
		delete.setStyle(btn);
		edit.setStyle(btn);
		
		create.setBackground(Bgs[2]);
		view.setBackground(Bgs[2]);
		delete.setBackground(Bgs[2]);
		edit.setBackground(Bgs[2]);
		
		create.setTextFill(Color.valueOf("#FFF"));
		view.setTextFill(Color.valueOf("#FFF"));
		delete.setTextFill(Color.valueOf("#FFF"));
		edit.setTextFill(Color.valueOf("#FFF"));
	}
	
	public void buildLayout() {
	 
		buttons.add(create, 0, 0,2,1);
		buttons.add(edit, 0, 1);
		buttons.add(delete, 1, 1);
		buttons.add(view, 0, 2,2,1);
		buttons.setAlignment(Pos.CENTER);
		buttons.setVgap(10);
		buttons.setHgap(10);
		
		welcomePane.prefWidthProperty().bind(stage.widthProperty().divide(2));
		allRightSide.prefWidthProperty().bind(stage.widthProperty().divide(2));
		
		create.prefWidthProperty().bind(allRightSide.prefWidthProperty());
		edit.prefWidthProperty().bind(allRightSide.prefWidthProperty().divide(1.5));
		view.prefWidthProperty().bind(allRightSide.prefWidthProperty());
		delete.prefWidthProperty().bind(allRightSide.prefWidthProperty().divide(1.5));
		
		create.prefHeightProperty().bind(stage.heightProperty().divide(6));
		edit.prefHeightProperty().bind(stage.heightProperty().divide(6));
		view.prefHeightProperty().bind(stage.heightProperty().divide(6));
		delete.prefHeightProperty().bind(stage.heightProperty().divide(6));
		
		choose.prefWidthProperty().bind(allRightSide.prefWidthProperty().divide(2));
		
		allRightSide.setTop(choose);
		allRightSide.setCenter(buttons);
		allRightSide.setBottom(copyright);
		allRightSide.setPadding(new Insets(15));
		BorderPane.setAlignment(choose, Pos.CENTER);
		
		welcomePane.setCenter(welcome);
		
		pane.getChildren().addAll(welcomePane,allRightSide);
		pane.setAlignment(Pos.CENTER);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
//	class BackHandler implements EventHandler<ActionEvent>{
//
//		@Override
//		public void handle(ActionEvent e) {
//			
//			
//		}
//		
//	}

	

}



