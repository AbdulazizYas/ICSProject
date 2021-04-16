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

	private Button create;
	private FormPane form;
	
	public CreatePane(ArrayList<Question> qstList, Button back) {
		
		this.create = new Button("Create");
		this.form = new FormPane(back,this.create);
		
		this.create.setOnAction(e -> {
			
			Question q = new Question(
					this.form.getQuestionTextField().getText(),
					this.form.getCorrectAnsField().getText(),
					this.form.getAnsOneField().getText(),
					this.form.getAnsTwoField().getText(),
					this.form.getAnsThreeField().getText()
					);
			qstList.add(q);
			
		});
		
		buildLayout();
	}

	@Override
	protected void buildLayout() {
		this.setPadding(new Insets(25));
		this.setCenter(this.form);
		
	}
	
}
