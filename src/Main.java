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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Scene main;
	private Button back;
	private Button create;
	private Button edit;
	private Button view;
	private Button delete;
	private Label welcome;
	private ArrayList<Question> qsList;
	private BorderPane pane;
	private HBox buttons;
	@Override
	public void start(Stage stage) throws Exception {
		
		qsList = new ArrayList<>();
		back = new Button("Back");
		create = new Button("Create");
		edit = new Button("Edit");
		view = new Button("View");
		delete = new Button("Delete");
		welcome = new Label("Welcome to Questions Bank App!");
		pane = new BorderPane();
		buttons = new HBox(15);
		
		back.setOnAction(e -> stage.setScene(this.main));
		
		
		
		create.setOnAction(e -> stage.setScene(new CreateScene(qsList,back)));
		edit.setOnAction(e -> stage.setScene(new EditScene(qsList,back)));
		
		buttons.getChildren().addAll(create,edit);
		buttons.setAlignment(Pos.CENTER);
		
		pane.setCenter(welcome);
		pane.setBottom(buttons);
		pane.setPadding(new Insets(50));
		
		main = new Scene(pane,700,500);
		stage.setScene(main);
		stage.setMinHeight(500);
		stage.setMinWidth(700);
		stage.show();
		
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



