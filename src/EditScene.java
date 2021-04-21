import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class EditScene extends BaseScene {

	private SelectionPane selectPane;
	private FormPane form;
	private Button update;
	private Question question;
	private ArrayList<Question> qsList;

	public EditScene(ArrayList<Question> qsList) {

		this.selectPane = new SelectionPane();
		this.update = new Button("Update");
		this.form = new FormPane(this.update, this.back);
		this.qsList = qsList;

		SelectionPane.qsCoList.setOnAction(e -> handleSelection());

		this.update.setOnAction(e -> handleUpdating());

		build();
	}

	@Override
	protected void build() {
		this.root.setPadding(new Insets(25));
		this.root.setTop(this.selectPane);
		this.root.setCenter(this.form);

	}

	// fill the fields with values of the selected question when combo is changed
	private void handleSelection() {
		this.question = selectPane.getQuestion(this.qsList);

		fillFields();
	}
	
	//for filling the fields for the chosen question
	private void fillFields() {
		this.form.getQuestionTextField().setText(this.question.getQuestionText());
		this.form.getCorrectAnsField().setText(this.question.getCorrectAns());
		this.form.getAnsOneField().setText(this.question.getAnsOne());
		this.form.getAnsTwoField().setText(this.question.getAnsTwo());
		this.form.getAnsThreeField().setText(this.question.getAnsThree());
	}
	
	//update the question selected with values of the fields
	private void handleUpdating() {
		
		// if question object is null, then the question has not been selected
		if (this.question == null) {
			Commons.openAlert("Question not selected", "You must choose a question to perform the updating",
					Alert.AlertType.WARNING);
			return;
		}
		

		// check if all fields are not empty
		if (this.form.isFieldsEmpty()) {
			Commons.openAlert("Empty fields", "You must fill all the fields to update your question",
					Alert.AlertType.WARNING);
			fillFields();
			return;
		}


		// set the question text of question object
		this.question.setQuestionText(form.getQuestionTextField().getText());

		// set the correct answer of question object
		this.question.setCorrectAns(form.getCorrectAnsField().getText());

		// set the answer one of question object
		this.question.setAnsOne(form.getAnsOneField().getText());

		// set the answer two of question object
		this.question.setAnsTwo(form.getAnsTwoField().getText());

		// set the answer three of question object
		this.question.setAnsThree(form.getAnsThreeField().getText());

		// get the index of question object
		int index = this.qsList.indexOf(this.question);
		
		//format the chosen question to be updated in combo box
		String chosenQuestion = this.question.getID() + " - " + this.question.getQuestionText();
		
		//fix a bug that if there is one question and 
		
		// change the text in combo box
		SelectionPane.qsCoList.getItems().set(index, chosenQuestion);

		Commons.openAlert("Success", "Your question has been modified successfully!", Alert.AlertType.INFORMATION);
	}

}
