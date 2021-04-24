import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	//static fields for outside usage
	public static Stage stage;
	public static Scene main;
	// Scenes  static fields
	public static CreateScene createScene;
	public static EditScene editScene;
	public static ViewScene viewScene;
	public static DeleteScene deleteScene;
	//buttons for each scene
	private Button create;
	private Button edit;
	private Button view;
	private Button delete;
	// Questions list and File object for which questions will be saved in
	private ArrayList<Question> qsList;
	private File qsFile = new File("QuestionBank.dat");
	// Some Main layout's nodes
	private Label welcome;
	private Label choose;
	private Label copyright;
	private BorderPane pane;
	private HBox allContent;
	private GridPane buttons;
	private BorderPane welcomePane;
	private BorderPane allRightSide;
	//Custom top bar will be shown only if Commons.customBar is true (which means it is enabeled)
	private TopBarPane topBar;
	
	@Override
	public void start(Stage stage) throws Exception {
		//Initialize Main.stage for outside usage
		Main.stage = stage;
		
		//Initialize some layout's nodes
		welcomePane = new BorderPane();
		pane = new BorderPane();
		buttons = new GridPane();
		allRightSide = new BorderPane();
		allContent = new HBox();
		welcome = new Label("Welcome \n to Questions Bank App!");
		choose = new Label("Choose your action");
		copyright = new Label("Questioner � Developed by Abdulaziz & Yousef");
		
		//Initialize Scenes buttons
		create = new Button("Create");
		edit = new Button("Edit");
		view = new Button("View");
		delete = new Button("Delete");
		
		//Initialize topBar with giving the stage and the title should be shown at main Scene
		topBar = new TopBarPane(stage,"Home");
		
		//Initialize questions list with empty list
		qsList = new ArrayList<>();
		
		//Load questions from QuestionBank.dat and fill the above list 
		loadQuestions();
		
		//Initialize main scene with the root pane and specific width & height
		main = new Scene(pane,Commons.sceneWidth,Commons.sceneHeight);
		
		stage.setScene(main);
		
		// set the minimum width and height for the stage
		stage.setMinHeight(500);
		stage.setMinWidth(700);
		
		stage.setTitle("Questioner");
		stage.getIcons().add(new Image("logo.jpeg"));
		
		//to benefit from custom top bar we should make stage undecorated
		if(Commons.customBar) {
			stage.initStyle(StageStyle.UNDECORATED);
		}
		
		// -------  handle events and create the scenes ---------//
		//pass the question list to handle the operations
		createScene = new CreateScene(qsList);
		editScene = new EditScene(qsList);
		viewScene = new ViewScene(qsList);
		deleteScene = new DeleteScene(qsList);
		
		create.setOnAction(e -> stage.setScene(createScene));
		edit.setOnAction(e ->stage.setScene(editScene));
		view.setOnAction(e -> stage.setScene(viewScene));
		delete.setOnAction(e -> stage.setScene(deleteScene));

		/*===========  Call the method that build and set the structure of the main scene =============*/
		buildLayout();
		
		/*-----------  Call the method that design and style the main scene  ---------------*/
		designLayout();
	
		// show the stage
		stage.show();		
	
	}
	
	//method for designing and styling the layout
	public void designLayout() {
		//design the welcome text message
		welcome.setTextAlignment(TextAlignment.CENTER);
		welcome.setTextFill(Color.valueOf("#fefefe"));
		welcome.setFont(Font.font(Commons.font,FontWeight.BOLD, 35));
		welcome.setWrapText(true);
		//design the title above the buttons
		choose.setAlignment(Pos.CENTER);
		choose.setTextFill(Color.valueOf("#FFF"));
		choose.setPadding(new Insets(10,5,10,5));
		choose.setStyle(Commons.title + Commons.shadow);
		//style copyright
		copyright.setStyle("-fx-text-fill: " + Commons.accentColor);
		//design the left side pane 
		welcomePane.setStyle(Commons.primaryGradient);
		//style the right side pane
		allRightSide.setStyle("-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.25) , 5, 0.0 , 2 , 1 );"
				                + Commons.bgWhite);
		//style each scene button for btn style and background accent and font size 34 and color white		
		create.setStyle(Commons.btn + Commons.bgAccent + "; -fx-font-size: 34; -fx-text-fill:#FFF");
		view.setStyle(Commons.btn + Commons.bgAccent + "; -fx-font-size: 34; -fx-text-fill:#FFF");
		delete.setStyle(Commons.btn + Commons.bgAccent + "; -fx-font-size: 34; -fx-text-fill:#FFF");
		edit.setStyle(Commons.btn + Commons.bgAccent + "; -fx-font-size: 34; -fx-text-fill:#FFF");	
	}
	
	//for building and structuring the layout and set the widths and heights all nodes
	public void buildLayout() {
		//Structure the GridPane for scenes buttons
		buttons.add(create, 0, 0,2,1);
		buttons.add(edit, 0, 1);
		buttons.add(delete, 1, 1);
		buttons.add(view, 0, 2,2,1);
		buttons.setAlignment(Pos.CENTER);
		buttons.setVgap(10);
		buttons.setHgap(10);
		//to split the appearance into two scenes we divide each side pane by 2 relative to the stage
		welcomePane.prefWidthProperty().bind(stage.widthProperty().divide(2));
		allRightSide.prefWidthProperty().bind(stage.widthProperty().divide(2));
		//set the pref width for each scene button relative to the right side pane
		create.prefWidthProperty().bind(allRightSide.prefWidthProperty());
		edit.prefWidthProperty().bind(allRightSide.prefWidthProperty().divide(1.5));// this is smaller than create and view
		view.prefWidthProperty().bind(allRightSide.prefWidthProperty());
		delete.prefWidthProperty().bind(allRightSide.prefWidthProperty().divide(1.5));// this is smaller than create and view
		//set the pref height for each scene button relative to the stage
		create.prefHeightProperty().bind(stage.heightProperty().divide(6));
		edit.prefHeightProperty().bind(stage.heightProperty().divide(6));
		view.prefHeightProperty().bind(stage.heightProperty().divide(6));
		delete.prefHeightProperty().bind(stage.heightProperty().divide(6));
		//set the pref width for choose title relative to the right side pane
		choose.prefWidthProperty().bind(allRightSide.prefWidthProperty().divide(2));
		
		//adding some nodes into the right side pane
		allRightSide.setTop(choose);
		allRightSide.setCenter(buttons);
		allRightSide.setBottom(copyright);
		allRightSide.setPadding(new Insets(15));
		BorderPane.setAlignment(choose, Pos.CENTER);
		//set the welcome text message to the center
		welcomePane.setCenter(welcome);
		//add all the left side and right side the content pane
		allContent.getChildren().addAll(welcomePane,allRightSide);
		allContent.setAlignment(Pos.CENTER);
		
		if(Commons.customBar)pane.setTop(topBar);// if customBar is enabled, add the top bar to the root pane
		pane.setCenter(allContent); //add all the content without topBar to the center of the root pane
	}

	public void loadQuestions()   {
		
		try (FileInputStream fis = new FileInputStream(qsFile);
			     ObjectInputStream ois = new ObjectInputStream(fis)) {
				
			Question q;
			    while((q = (Question) ois.readObject()) != null) {
			    	qsList.add(q);
			    }

			} catch (IOException | ClassNotFoundException ex) {
			    return;
			}
	}
	
	@Override
	public void stop() throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(qsFile)))
		{
			for(int i = 0; i < qsList.size(); i++) {
				out.writeObject(qsList.get(i));
			}
			out.close();
		}
	}	
	public static void main(String[] args) {
		launch(args);
	}

}
