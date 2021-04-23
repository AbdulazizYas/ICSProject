import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class ViewScene extends BaseScene{
	
	public SelectionPane selectPane;
	private VDFormPane form;
	
	

	
	public ViewScene(ArrayList<Question> qstList) {
		
		
		this.selectPane = new SelectionPane("View a question");
		
		this.form = new VDFormPane(qstList, this.back);
		
		selectPane.getCombo().setItems(SelectionPane.getFormattedList(qstList));
		selectPane.getCombo().setOnAction(e -> getVDForm().handleView());
		
		build();
	}

	@Override
	protected void build() {

		this.root.setTop(selectPane);
		this.root.setCenter(form);

	}
	
	public SelectionPane getSelectPane() {
		return this.selectPane;
	}
	
	public VDFormPane getVDForm() {
		return this.form;
	}
}

