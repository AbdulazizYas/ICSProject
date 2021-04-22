import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public abstract class BaseScene extends Scene {
	
	protected final Button back = new Button("Back");
	protected final BorderPane root = (BorderPane) getRoot();
	
	public BaseScene() {
		super(new BorderPane(),950,600);
		
		back.setOnAction(e -> Main.stage.setScene(Main.main));
		
		back.setStyle(Commons.btn + Commons.bgPrimary);
		back.setPadding(new Insets(10));
		back.prefWidthProperty().bind(Main.stage.widthProperty().divide(10));
		back.setTextFill(Color.valueOf("#FFF"));
		back.setFont(Font.font(16));
	}

	protected abstract void build();

}

interface BasePane {
	void buildPane();
}


