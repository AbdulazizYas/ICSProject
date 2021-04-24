import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


// this pane is used in ViewScene and DeleteScene since both are using the same labels
public class DisplayPane extends BorderPane implements BasePane{
	
	// Passing the question list to the view/delete scenes
	private ArrayList<Question> qsList;
	private Question question;
	
	// Questions + answers that are going to be shown in the scene
	private Label questionText;
	private Label correctAns;
	private Label ansOne;
	private Label ansTwo;
	private Label ansThree;

	public DisplayPane(ArrayList<Question> qsList) {
		
		this.qsList = qsList;	
		
		// So, the labels has initialized text to view
		this.questionText = new Label("");
		this.questionText.setWrapText(true);
		this.questionText.setMaxWidth(750);
		// Set the default value of labels
		this.correctAns = new Label("");
		this.ansOne     = new Label("");
		this.ansTwo     = new Label("");
		this.ansThree   = new Label(""); 
		setDefaultLabels();
		
		//build the pane of the Form
		buildPane();
	}
	
	@Override
	public void buildPane() {
		
		// Changing the font and style for the labels (question + answers)
		Font fontSize = new Font(Commons.font, 19);
		
		
		this.questionText.setFont   (Font.font(Commons.font, FontWeight.BOLD, 21));
		this.questionText.setStyle  ("-fx-text-fill: "+ (Commons.darkMode? "#fdfdfd" : "#202020") +";");
		
		this.correctAns.setFont     (Font.font(Commons.font,FontWeight.BOLD, 20));
		this.correctAns.setTextFill (Color.valueOf("#62ce67"));
		
		this.ansOne.setFont         (fontSize);
		this.ansOne.setStyle        ("-fx-text-fill: "+ (Commons.darkMode? "#fdfdfd" : "#202020") +";");
		
		this.ansTwo.setFont         (fontSize);
		this.ansTwo.setStyle        ("-fx-text-fill: "+ (Commons.darkMode? "#fdfdfd" : "#202020") +";");
		
		this.ansThree.setFont       (fontSize);
		this.ansThree.setStyle      ("-fx-text-fill: "+ (Commons.darkMode? "#fdfdfd" : "#202020") +";");
		
		
		
		// Answers VBox the
		VBox answersVB = new VBox(25);
		answersVB.setPadding(new Insets(0,0,0,25));
		answersVB.getChildren().addAll(correctAns, ansOne, ansTwo, ansThree);
		
		// Questions + the answers VBox
		VBox layoutVB = new VBox(45);
		layoutVB.setPadding(new Insets(25,0,0,25));
		layoutVB.getChildren().addAll(questionText,answersVB);
		
		// this is referring to a (BorderPane)
		this.setCenter(layoutVB);
		this.setStyle(Commons.bgSecondary+";" + Commons.innerShadow);
		
		
	}
	
	public void setDefaultLabels() {
		// This will reset the labels everytime a question gets deleted
		this.questionText.setText("Q. Select a question from the box above");
		
		this.correctAns.setText  ("1. Correct answer");
		this.ansOne.setText      ("2. Answer two");
		this.ansTwo.setText      ("3. Answer three");
		this.ansThree.setText    ("4. Answer four");
	}
	
	// Here the handler works as a setter for the display class ... thats why its public
	public void handleDisplay(Question question) {
		
		// Getting the question object from the combo box
		this.question = question;
		if (question == null) {
			setDefaultLabels();
		}
		else {
			// Changing the text of the labels
			this.questionText.setText("Q. "+ question.getQuestionText());
			this.correctAns.setText  ("1. " + question.getCorrectAns());
			this.ansOne.setText      ("2. " + question.getAnsOne());
			this.ansTwo.setText      ("3. " + question.getAnsTwo());
			this.ansThree.setText    ("4. " + question.getAnsThree());
		}
	}
    
}