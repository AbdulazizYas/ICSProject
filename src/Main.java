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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	
	public static Stage stage;
	public static Scene main;
	private CreateScene createScene;
	private EditScene editScene;
	private Button create;
	private Button edit;
	private Button view;
	private Button delete;
	private Label welcome;
	private Label choose;
	private Label copyright;
	private ArrayList<Question> qsList;
	private File qsFile = new File("QuestionBank.dat");
	private BorderPane pane;
	private HBox content;
	private TopBarPane topBar;
	private GridPane buttons;
	private BorderPane welcomePane;
	private BorderPane allRightSide;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Main.stage = stage;
		
		welcomePane = new BorderPane();
		pane = new BorderPane();
		buttons = new GridPane();
		allRightSide = new BorderPane();
		topBar = new TopBarPane(stage,"Home");
		content = new HBox();

		create = new Button("Create");
		edit = new Button("Edit");
		view = new Button("View");
		delete = new Button("Delete");
		
		qsList = new ArrayList<>();
		
		loadQuestions();
		
		welcome = new Label("Welcome \n to Questions Bank App!");
		choose = new Label("Choose your action");
		copyright = new Label("Questioner © Developed by Abdulaziz & Yousef");
		
		main = new Scene(pane,950,600);
		
		stage.setScene(main);
		
		
		stage.setMinHeight(500);
		stage.setMinWidth(700);
		stage.setTitle("Questioner");
		
		if(Commons.customBar) {
			stage.initStyle(StageStyle.UNDECORATED);
		}
		
		
		
		// -------  handle events ---------//
		
		createScene = new CreateScene(qsList);
		editScene = new EditScene(qsList);
		
		create.setOnAction(e -> stage.setScene(createScene));
		
		edit.setOnAction(e -> stage.setScene(editScene));
		
		/*-----------  Designing  ---------------*/
		designLayout();
		
	
		/*===========  Layouting =============*/
		buildLayout();


		
		
		stage.show();		

		
		
	}
	
	
	public void designLayout() {
		
		welcome.setTextAlignment(TextAlignment.CENTER);
		welcome.setTextFill(Color.valueOf("#fefefe"));
		welcome.setFont(Font.font(Commons.font,FontWeight.BOLD, 35));
		welcome.setWrapText(true);
		
		choose.setAlignment(Pos.CENTER);
		choose.setTextFill(Color.valueOf("#FFF"));
		choose.setPadding(new Insets(10,5,10,5));
		choose.setStyle(Commons.title + Commons.shadow);
		
		copyright.setAlignment(Pos.CENTER);
		
		welcomePane.setStyle(Commons.primaryGradient);
		
		allRightSide.setStyle("-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.25) , 5, 0.0 , 2 , 1 );"
				                + Commons.bgWhite);
		
		create.setFont(Font.font(34));
		view.setFont(Font.font(34));
		delete.setFont(Font.font(34));
		edit.setFont(Font.font(34));
		
		create.setStyle(Commons.btn + Commons.bgAccent);
		view.setStyle(Commons.btn + Commons.bgAccent);
		delete.setStyle(Commons.btn + Commons.bgAccent);
		edit.setStyle(Commons.btn + Commons.bgAccent);
		
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
		
		content.getChildren().addAll(welcomePane,allRightSide);
		content.setAlignment(Pos.CENTER);
		
		if(Commons.customBar)pane.setTop(topBar);
		pane.setCenter(content);
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



