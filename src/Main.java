import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		
		Scene s = new Scene(new Pane());
		stage.setScene(s);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
