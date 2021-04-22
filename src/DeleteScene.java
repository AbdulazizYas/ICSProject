import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class DeleteScene extends BaseScene{
	
	private SelectionPane selectPane;
	
	private Button back;
	private Button delete;
	
	private Label question;
	private Label correctAns;
	private Label ansOne;
	private Label ansTwo;
	private Label ansThree;
	
	public DeleteScene(ArrayList<Question> qstList, Button back) {
	
		this.selectPane = new SelectionPane("Delete a question");

		this.back = back;
		this.delete = new Button("Delete");
		
		this.question = new Label();
		this.correctAns = new Label();
		this.ansOne = new Label();
		this.ansTwo = new Label();
		this.ansThree = new Label();
		
		this.delete.setOnAction(null);
	}

	@Override
	protected void build() {
		// TODO Auto-generated method stub
		
	}
}


