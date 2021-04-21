import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class FormPane extends BorderPane implements BasePane{
	
	public  Button back;
	private Button post;
	private TextField questionTextField;
	private TextField correctAnsField;
	private TextField ansOneField;
	private TextField ansTwoField;
	private TextField ansThreeField;

	
	public FormPane( Button post,Button back) {
		this.back = back;
		this.post = post;
		this.questionTextField = new TextField();
		this.correctAnsField = new TextField();
		this.ansOneField = new TextField();
		this.ansTwoField = new TextField();
		this.ansThreeField = new TextField();
		
		this.back.setOnAction(e -> Main.stage.setScene(Main.main));
		
		//build the pane of the Form
		buildPane();
	}
	
	@Override
	public void buildPane() {
		
		this.questionTextField.setPromptText("Type here your question ...");
		this.correctAnsField.setPromptText("Type here the correct answer ...");
		this.ansOneField.setPromptText("Type here answer 1 ..");
		this.ansTwoField.setPromptText("Type here answer 2 ..");
		this.ansThreeField.setPromptText("Type here answer 3 ..");
		
		this.questionTextField.setPrefHeight(80);
		this.questionTextField.setAlignment(Pos.CENTER);
		
		VBox fields = new VBox(25);
		fields.setAlignment(Pos.CENTER);
		fields.getChildren().addAll(this.questionTextField,this.correctAnsField,this.ansOneField,this.ansTwoField,this.ansThreeField);
		
		BorderPane buttonsPane = new BorderPane();
		
		buttonsPane.setRight(this.post);
		buttonsPane.setLeft(this.back);
		
		this.setCenter(fields);
		this.setBottom(buttonsPane);
		
	}
	
	public TextField getQuestionTextField() {
		return questionTextField;
	}
	public TextField getCorrectAnsField() {
		return correctAnsField;
	}
	public TextField getAnsOneField() {
		return ansOneField;
	}
	public TextField getAnsTwoField() {
		return ansTwoField;
	}
	public TextField getAnsThreeField() {
		return ansThreeField;
	}
	
	//This method is used for handling creating or updating questions
	public boolean isFieldsEmpty() {
		//if at least one field is empty ...
		
		if( this.questionTextField.getText().length() == 0 ||
			this.correctAnsField.getText().length() == 0 ||
			this.ansOneField.getText().length() == 0 ||
			this.ansTwoField.getText().length() == 0 ||
			this.ansThreeField.getText().length() == 0) return true; // then return true
		
		//otherwise return false
		return false;
	}
}
