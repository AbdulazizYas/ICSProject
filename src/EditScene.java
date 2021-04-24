import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

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
		
		if(question != null)
			fillFields(); //method for filling the fields with the selected question
	}
	
	private void fillFields() {
		this.form.getQuestionTextField().setText(this.question.getQuestionText());
		this.form.getCorrectAnsField().setText(this.question.getCorrectAns());
		this.form.getAnsOneField().setText(this.question.getAnsOne());
		this.form.getAnsTwoField().setText(this.question.getAnsTwo());
		this.form.getAnsThreeField().setText(this.question.getAnsThree());
	}
	
	private void handleUpdating() {
		
		// if question object is null, then the question has not been selected
		if (this.question == null) {
			Commons.openAlert("Question not selected", "You must choose a question to perform the updating",
					Alert.AlertType.WARNING);
			return; 
		}
		

		// check if all fields are not empty so it can update
		if (this.form.isFieldsEmpty()) {
			Commons.openAlert("Empty fields", "You must fill all the fields to update your question",
					Alert.AlertType.WARNING);
			
			fillFields(); //fill the fields again with the correct data for the selected item
			return;
		}

		// ----- Now set each field of question object with corresponding TextField's value ------///
		
		this.question.setQuestionText(form.getQuestionTextField().getText());
		this.question.setCorrectAns(form.getCorrectAnsField().getText());
		this.question.setAnsOne(form.getAnsOneField().getText());
		this.question.setAnsTwo(form.getAnsTwoField().getText());
		this.question.setAnsThree(form.getAnsThreeField().getText());

		int index = this.qsList.indexOf(this.question);
		
		//format the chosen question to be updated in combo box
		String chosenQuestion = (index + 1) + " - " + this.question.getQuestionText();
		
		// change the text in combo box
		selectPane.getCombo().getItems().set(index, chosenQuestion);
		
		Commons.openAlert("Success", "Your question has been modified successfully!", Alert.AlertType.INFORMATION);
	}

}
