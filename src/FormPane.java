import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

// this pane is used in CreateScene and EditScene since both are using TextFields
public class FormPane extends BorderPane implements BasePane{
	
	public  Button back;
	private Button post; // post can be create or update, each scene will define what is it
	private TextField questionTextField;
	private TextField correctAnsField;
	private TextField ansOneField;
	private TextField ansTwoField;
	private TextField ansThreeField;

	public FormPane( Button post,Button back) {
		this.back = back; //the scene has back button from BaseScene, so all scenes uses this pane will pass back button
		this.post = post;
		this.questionTextField = new TextField();
		this.correctAnsField = new TextField();
		this.ansOneField = new TextField();
		this.ansTwoField = new TextField();
		this.ansThreeField = new TextField();
		
		//build the pane of the Form
		buildPane();
	}
	
	@Override
	public void buildPane() {
		// set the prompt(placeholder for each field)
		this.questionTextField.setPromptText("Type here your question ...");
		this.correctAnsField.setPromptText("Type here the correct answer ...");
		this.ansOneField.setPromptText("Type here answer 1 ..");
		this.ansTwoField.setPromptText("Type here answer 2 ..");
		this.ansThreeField.setPromptText("Type here answer 3 ..");
		
		//style the fields and make the correct answer field with green shadow
		this.questionTextField.setStyle(Commons.textField + Commons.shadow);
		this.correctAnsField.setStyle(Commons.textField +"-fx-effect: dropshadow( three-pass-box , rgba(98, 206, 103, 0.5) , 2, 2.0 , 0 , 1 );");
		this.ansOneField.setStyle(Commons.textField + Commons.shadow);
		this.ansTwoField.setStyle(Commons.textField + Commons.shadow);
		this.ansThreeField.setStyle(Commons.textField + Commons.shadow);
		
		//set the max width of each field relative to the computer screen width 
		double fieldWidth = Screen.getPrimary().getBounds().getWidth()/1.25;
		this.questionTextField.setMaxWidth(fieldWidth);
		this.correctAnsField.setMaxWidth(fieldWidth);
		this.ansOneField.setMaxWidth(fieldWidth);
		this.ansTwoField.setMaxWidth(fieldWidth);
		this.ansThreeField.setMaxWidth(fieldWidth);
		
		this.questionTextField.setPrefHeight(80);
		this.questionTextField.setAlignment(Pos.CENTER);
		
		//specific pane for fields
		VBox fields = new VBox(25);
		fields.setAlignment(Pos.CENTER);
		fields.getChildren().addAll(this.questionTextField,this.correctAnsField,this.ansOneField,this.ansTwoField,this.ansThreeField);
		fields.setPadding(new Insets(16,0,16,0));
		
		//style the post button whether it was create or update
		this.post.setStyle(Commons.btn + Commons.bgPrimary + ";-fx-font-size:16; -fx-text-fill:#FFF");
		this.post.setPadding(new Insets(10));
		this.post.prefWidthProperty().bind(Main.stage.widthProperty().divide(10));
		
		//specific pane for both buttons post and back
		BorderPane buttonsPane = new BorderPane();
		buttonsPane.setRight(this.post);
		buttonsPane.setLeft(this.back);
		buttonsPane.setPadding(new Insets(16,8,16,8));
		
		this.setCenter(fields);
		this.setBottom(buttonsPane);
		
	}
	//------- getters of each TextField ----- ///
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
