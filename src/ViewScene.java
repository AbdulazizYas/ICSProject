import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class ViewScene extends BaseScene{
	
	
	private SelectionPane selectPane;
	private DisplayPane display;
	
	public ViewScene(ArrayList<Question> qstList) {
		
		this.display = new DisplayPane(qstList);
		
		// Changing the title and putting a combo box for the ViewScene
		this.selectPane = new SelectionPane("View a question");
		
		// Set the items of combo box with the question list when the ViewScene is created
		selectPane.getCombo().setItems(SelectionPane.getFormattedList(qstList));
		
		// Handle the selection of combo box in view scene with handleDisplay located in DisplayPane
		selectPane.getCombo().setOnAction(e -> this.display.handleDisplay(Main.viewScene.getSelectPane().getQuestion(qstList)));
		
		
		build();
	}

	@Override
	protected void build() {
		
		// a BorderPane for the buttons
		BorderPane buttons = new BorderPane();
		buttons.setLeft(this.back);
		buttons.setPadding(new Insets(25));
		buttons.setStyle(Commons.bgSecondary);
		
		this.display.setPadding(new Insets(25));
		
		// root refers to the BorderPane inherited from the BaseScene Class
		this.root.setTop(selectPane); // ComboBox
		this.root.setCenter(display); // Displaying question + answers
		this.root.setBottom(buttons); // Buttons

	}
	
	public SelectionPane getSelectPane() {
		return this.selectPane;
	}
	
	/*public DisplayPane getDisplayPane() {
		return this.display;
	}*/
}

