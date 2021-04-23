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
		
		
		this.selectPane = new SelectionPane("View a question");
		
		this.display = new DisplayPane(qstList);
		
		//set the items of combo box with the question list when the ViewScene is created
		selectPane.getCombo().setItems(SelectionPane.getFormattedList(qstList));
		
		//handle the selection of combo box in view scene with handleView located in VDFormPane
		selectPane.getCombo().setOnAction(e -> getVDForm().handleView());
		
		build();
	}

	@Override
	protected void build() {
		
		BorderPane buttons = new BorderPane();
		buttons.setLeft(this.back);
		buttons.setPadding(new Insets(25));
		buttons.setStyle(Commons.bgSecondary);
		
		this.display.setPadding(new Insets(25));
		
		this.root.setTop(selectPane);
		this.root.setCenter(display);
		this.root.setBottom(buttons);

	}
	
	public SelectionPane getSelectPane() {
		return this.selectPane;
	}
	
	public DisplayPane getVDForm() {
		return this.display;
	}
}

