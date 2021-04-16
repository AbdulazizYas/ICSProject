import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class FormPane extends BasePane{
	
	private Button back;
	private Button post;
	private TextField questionTextField;
	private TextField correctAnsField;
	private TextField ansOneField;
	private TextField ansTwoField;
	private TextField ansThreeField;
	
	public FormPane(Button back, Button post) {
		this.back = back;
		this.post = post;
		this.questionTextField = new TextField();
		this.correctAnsField = new TextField();
		this.ansOneField = new TextField();
		this.ansTwoField = new TextField();
		this.ansThreeField = new TextField();
		
		//build the layout of the Form
		buildLayout();
	}
	@Override
	protected void buildLayout() {
		
		GridPane gridPane = new GridPane();
		
        gridPane.add(new Label("Question Text: "), 0, 0);
        gridPane.add(this.questionTextField, 1, 0);
        
        gridPane.add(new Label("Correct Answer: "), 0, 1);
        gridPane.add(this.correctAnsField, 1, 1);
        
        Label incorrects = new Label("Incorrect Answers");
        incorrects.setAlignment(Pos.CENTER);
        gridPane.add(incorrects, 0, 2);
        
        gridPane.add(new Label("First Answer: "), 0, 3);
        gridPane.add(this.ansOneField, 1, 3);
        
        gridPane.add(new Label("Second Answer: "), 0, 4);
        gridPane.add(this.ansTwoField, 1, 4);
        
        gridPane.add(new Label("Third Answer: "), 0, 5);
        gridPane.add(this.ansThreeField, 1, 5);
        
        gridPane.setVgap(15);
        gridPane.setHgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
		
		BorderPane buttonsPane = new BorderPane();
		
		buttonsPane.setRight(this.post);
		buttonsPane.setLeft(this.back);
		
		this.setCenter(gridPane);
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
}
