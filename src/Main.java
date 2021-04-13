import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		
//		Question q = new Question("bla","a","b","c","d");
//		System.out.print(q.toString());
//		ArrayList<Question> qs = new ArrayList<>();
//		qs.add(q);
		
	
		Scene s = new Scene(new Pane());
		stage.setScene(s);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}



