import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

// this pane is used in CreateScene and EditScene since both are using TextFields
public class VDFormPane extends BorderPane implements BasePane{
	
	public  Button back;
	private ArrayList<Question> qsList;
	private Question question;
	private Label questionText;
	private Label correctAns;
	private Label ansOne;
	private Label ansTwo;
	private Label ansThree;

	
	public VDFormPane(ArrayList<Question> qsList,Button back) {
		this.back = back; //the scene has back button from BaseScene, so all scenes uses this pane will pass back button
		
		this.qsList = qsList;
		
		
		this.questionText = new Label("Q. Select a question");
		this.correctAns = new Label("1.");
		this.ansOne = new Label("2.");
		this.ansTwo = new Label("3.");
		this.ansThree = new Label("4.");
		//build the pane of the Form
		buildPane();
	}
	
	@Override
	public void buildPane() {
		
		Font fontSize = new Font(Commons.font, 19);
		
		this.questionText.setFont(Font.font(Commons.font, FontWeight.BOLD, 21));
		
		this.correctAns.setFont(Font.font(Commons.font,FontWeight.BOLD, 20));
		this.correctAns.setTextFill(Color.valueOf("#62ce67"));
		
		this.ansOne.setFont(fontSize);
		this.ansTwo.setFont(fontSize);
		this.ansThree.setFont(fontSize);
		
		
		VBox vb2 = new VBox(25);
		vb2.setPadding(new Insets(0,0,0,25));
		vb2.getChildren().addAll(correctAns, ansOne, ansTwo, ansThree);
		
		VBox vb1 = new VBox(55);
		vb1.setPadding(new Insets(45,0,0,25));
		vb1.getChildren().addAll(questionText,vb2);

		this.setCenter(vb1);
		this.setBottom(this.back);
		
		
		
	}
	
	public void handleView() {
		
		this.question = Main.viewScene.selectPane.getQuestion(this.qsList);
		
		this.questionText.setText("Q. "+ question.getQuestionText());
		this.correctAns.setText("1. " + question.getCorrectAns());
		this.ansOne.setText("2. " + question.getAnsOne());
		this.ansTwo.setText("3. " + question.getAnsTwo());
		this.ansThree.setText("4. " + question.getAnsThree());
	}
    
}