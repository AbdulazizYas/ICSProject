import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class CreateScene extends Scene{

	private CreateScene(Pane pane) {
		super(pane,700,500);
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
			
			if(this.form.isFieldsEmpty()) {
				
				return;
			}
			
			Question q = new Question(
					this.form.getQuestionTextField().getText(),
					this.form.getCorrectAnsField().getText(),
					this.form.getAnsOneField().getText(),
					this.form.getAnsTwoField().getText(),
					this.form.getAnsThreeField().getText()
					);
			qstList.add(q);
			
			this.form.getQuestionTextField().setText("");
			this.form.getCorrectAnsField().setText("");
			this.form.getAnsOneField().setText("");
			this.form.getAnsTwoField().setText("");
			this.form.getAnsThreeField().setText("");
			
			
		});
		
		buildLayout();
	}

	@Override
	protected void buildLayout() {
		Label title = new Label("Create your question");

		this.setPadding(new Insets(25));
		this.setTop(title);
		this.setCenter(this.form);
		
	}
	
	
	
}
