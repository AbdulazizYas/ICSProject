import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class CreateScene extends Scene{

	private CreateScene(Pane pane) {
		super(pane);
	}
	
	public CreateScene(ArrayList<Question> qstList, Button back) {
		this(new CreatePane(qstList,back));
	}

}

class CreatePane extends BasePane{

	private Button back;
	private Button create;
	private TextField question;
	private TextField correctAns;
	private TextField ansOne;
	private TextField ansTwo;
	private TextField ansThree;
	
	public CreatePane(ArrayList<Question> qstList, Button back) {
		this.back = back;
		this.create = new Button("Create");
		this.question = new TextField();
		this.correctAns = new TextField();
		this.ansOne = new TextField();
		this.ansTwo = new TextField();
		this.ansThree = new TextField();
		
		this.create.setOnAction(e -> {
			
			Question q = new Question(
					this.question.getText(),
					this.correctAns.getText(),
					this.ansOne.getText(),
					this.ansTwo.getText(),
					this.ansThree.getText()
					);
			qstList.add(q);
			
		});
		buildLayout();
	}

	@Override
	protected void buildLayout() {
		VBox vb = new VBox(15);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(20));
		vb.getChildren().addAll(this.question,this.correctAns,this.ansOne,this.ansTwo,this.ansThree);
		
		BorderPane bp = new BorderPane();
		bp.setRight(this.create);
		bp.setLeft(this.back);

		this.setPadding(new Insets(25));
		this.setCenter(vb);
		this.setBottom(bp);
		
	}
	
}
