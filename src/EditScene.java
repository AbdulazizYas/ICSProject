import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EditScene extends BaseScene {

	private SelectionPane selectPane;
	private FormPane form;
	private Button update;
	private Question question;
	private ArrayList<Question> qsList;

	public EditScene(ArrayList<Question> qsList) {

		this.selectPane = new SelectionPane("Modify a question"); //pass the title of this scene and it will be passed to TopBarPane
		this.update = new Button("Update");
		this.form = new FormPane(this.update, this.back);// this back is inherited from BaseScene
		this.qsList = qsList;
		
		//when creating this scene set the items of the combo box
		selectPane.getCombo().setItems(SelectionPane.getFormattedList(qsList));
		
		//set handling the selection
		selectPane.getCombo().setOnAction(e -> handleSelection());
		
		this.update.setOnAction(e -> handleUpdating());
		
		//build and design the layout
		build();
	}

	@Override
	protected void build() {
	
		this.form.setPadding(new Insets(25));
		this.form.setStyle(Commons.bgWhite + Commons.innerShadow);
		
		this.root.setTop(this.selectPane);
		this.root.setCenter(this.form);

	}
	
	public SelectionPane getSelectPane() {
		return this.selectPane;
	}

	// fill the fields with values of the selected question when combo is changed
	private void handleSelection() {
		
		//initialize the question by the selected item in combo box
		question = selectPane.getQuestion(this.qsList);
		//method for filling the fields with the selected question
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
			return; // stop executing method
		}
		

		// check if all fields are not empty
		if (this.form.isFieldsEmpty()) {
			Commons.openAlert("Empty fields", "You must fill all the fields to update your question",
					Alert.AlertType.WARNING);
			fillFields(); //fill the fields again with the correct data for the selected item
			return;// stop executing method
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
		String chosenQuestion = (index + 1) + " - " + this.question.getQuestionText();
		
		// change the text in combo box
		selectPane.getCombo().getItems().set(index, chosenQuestion);
		
		//open alert that tells the user that question is updated
		Commons.openAlert("Success", "Your question has been modified successfully!", Alert.AlertType.INFORMATION);
	}

}
