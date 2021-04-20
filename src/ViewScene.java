import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class ViewScene extends BaseScene{
	
	private SelectionPane selectPane;
	
	private Button back;
	
	private Label question;
	private Label correctAns;
	private Label ansOne;
	private Label ansTwo;
	private Label ansThree;
	
	public ViewScene(ArrayList<Question> qstList, Button back) {
		this.back = back;
		
		this.question = new Label();
		this.correctAns = new Label();
		this.ansOne = new Label();
		this.ansTwo = new Label();
		this.ansThree = new Label();
	}

	@Override
	protected void buildLayout() {
		// TODO Auto-generated method stub
		
	}
}

