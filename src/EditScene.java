import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class EditScene extends BaseScene{

	private SelectionPane selectPane;
	private FormPane form;
	private Button update;
	private Question question;
	
	public EditScene(ArrayList<Question> qstList, Button back) {
		this.selectPane = new SelectionPane(qstList);
		this.update = new Button("Update");
		this.form = new FormPane(back,this.update);
		
		
		//fill the fields with values of the selected question when combo is changed
		this.selectPane.getCombo().setOnAction(e -> {
			
			this.question = selectPane.getQuestion();
			
				this.form.getQuestionTextField().setText(this.question.getQuestionText());
				this.form.getCorrectAnsField().setText(this.question.getCorrectAns());
				this.form.getAnsOneField().setText(this.question.getAnsOne());
				this.form.getAnsTwoField().setText(this.question.getAnsTwo());
				this.form.getAnsThreeField().setText(this.question.getAnsThree());

		});
		
		this.update.setOnAction(e -> {
			
			
			// if question object is null, then the question has not been selected
			if(this.question == null) return;
			
			//check if one field at least not empty
			if(this.form.isFieldsEmpty()) {
				
				return;
			}
			
			//get the index of question object 
			int index = qstList.indexOf(this.question);
			
			//set the question text of question object
			qstList.get(index)
			.setQuestionText(form.getQuestionTextField().getText());
			
			//set the correct answer of question object
			qstList.get(index)
			.setCorrectAns(form.getCorrectAnsField().getText());
			
			//set the answer one of question object
			qstList.get(index)
			.setAnsOne(form.getAnsOneField().getText());
			
			//set the answer two of question object
			qstList.get(index)
			.setAnsTwo(form.getAnsTwoField().getText());
			
			//set the answer three of question object
			qstList.get(index)
			.setAnsThree(form.getAnsThreeField().getText());
			
			// change the text in combo box
			this.selectPane.getCombo().getItems().set(
					index,
					this.question.getID() +" - "+ this.question.getQuestionText());
		});
		
		buildLayout();
	}

	@Override
	protected void buildLayout() {
		this.root.setPadding(new Insets(25));
		this.root.setTop(this.selectPane);
		this.root.setCenter(this.form);
		
	}

}
