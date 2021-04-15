import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class DeleteScene extends Scene{
	
	private DeleteScene(Pane pane) {
		super(pane);
	}
	
	public DeleteScene(ArrayList<Question> qstList, Button back) {
		this(new DeletePane(qstList, back));
	}
}



class DeletePane extends BasePane{
	
	private SelectionPane selectPane;
	
	private Button back;
	private Button delete;
	
	private Label question;
	private Label correctAns;
	private Label ansOne;
	private Label ansTwo;
	private Label ansThree;
	
	public DeletePane(ArrayList<Question> qstList, Button back) {
		
		this.selectPane = new SelectionPane(qstList);
		
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
	protected void buildLayout() {
		// TODO Auto-generated method stub
		
	}
	
}
