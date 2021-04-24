import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

// this class is used to be inherited by the scenes
//it gives the root pane, which is BorderPane, and back button to its subclasses
public abstract class BaseScene extends Scene {
	
	protected final Button back = new Button("Back");
	protected final BorderPane root = (BorderPane) getRoot(); // the root is the one created inside the super
	
	public BaseScene() {
		super(new BorderPane(),Commons.sceneWidth,Commons.sceneHeight); //set the width and height for all its subclasses
		
		//define only once what back button should do so all its subclasses inherit it
		back.setOnAction(e -> Main.stage.setScene(Main.main));
		
		//style the back button once and set the pref width relative to the stage
		back.setStyle(Commons.btn + Commons.bgPrimary + ";-fx-font-size:16; -fx-text-fill:#FFF");
		back.setPadding(new Insets(10));
		back.prefWidthProperty().bind(Main.stage.widthProperty().divide(10));
	}
	
	//define method that must be used by subclasses 
	protected abstract void build();

}
//interface for TopBarPane and FormPane
interface BasePane {
	void buildPane();
}