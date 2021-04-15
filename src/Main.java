import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	public Scene s;
	@Override
	public void start(Stage stage) throws Exception {
		
		ArrayList<Question> qs = new ArrayList<>();
		
		Button back = new Button("Back");
		back.setOnAction(e -> {
			stage.setScene(s);
			for(Question qq : qs) {
				System.out.print(qq.getQuestionText() + " ");
			}
			System.out.println();
		});
		
		BorderPane bb = new BorderPane();
		Button create = new Button("Create");
		create.setOnAction(e -> {
			stage.setScene(new CreateScene(qs,back));
		});
		
		bb.setCenter(create);
		 s = new Scene(bb,400,400);
		stage.setScene(s);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}



