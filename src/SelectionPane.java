import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

//this pane for selecting questions in EditScene,ViewScene,DeleteScene
public class SelectionPane extends BorderPane {
	
	private ComboBox<String> qsCoList;
	private TopBarPane topBar; 
	
	//the title parameter is for passing it to TopBarPane to set the title of the current Scene
	public SelectionPane(String title) {
		
		qsCoList = new ComboBox<>();
	    topBar = new TopBarPane(Main.stage,title);
	    
	    //set the default text inside the combo box
		qsCoList.setPromptText("-- Select a Question --");
		
		qsCoList.setPrefWidth(Double.MAX_VALUE);
		qsCoList.setStyle("-fx-background-color: #FFF;-fx-border-radius:0;-fx-border-width:3;"
				+ "-fx-border-color: #FFF;" + "-fx-cursor: hand;");

		//pane for the content which is the combo box and style it
		BorderPane content = new BorderPane();
		content.setCenter(qsCoList);
		content.setPadding(new Insets(25,25,30,25));
		content.setStyle(Commons.primaryGradient);
		
		//if customBar is true add the top bar to the top of SeletionPane
		if(Commons.customBar)
			this.setTop(topBar);
		
		this.setCenter(content); //set the combo box's pane to the center
	}

	
	/* this method is for outside usage
	 * it receives the question list and format each question text as (id - questionText)  
	 */
	public static ObservableList<String> getFormattedList(ArrayList<Question> qsList) {
		//new array to be formatted and be added to Observable list
		String[] qsArr = new String[qsList.size()];

		// filling the array of question text for each question object
		for (int i = 0; i < qsArr.length; i++) {
			qsArr[i] = (i +1) + " - " + qsList.get(i).getQuestionText();
		}
		return FXCollections.observableArrayList(qsArr);
	}
	
	
	/* this method receives the question list 
	 * it uses the question list to get the question by the index that is got by the following process
	 */
	public Question getQuestion(ArrayList<Question> qsList) {
		//get the selected item
		String questionTextInCombo = qsCoList.getValue();
		
		if (questionTextInCombo != null) {
			// get the index from the id beside each item in combo box
			int index = Integer.parseInt(questionTextInCombo.split(" ")[0]) - 1;
			return qsList.get(index);
		}
		//if the selected item is this, so the question has not been selected and the caller will check if the returned value is null
		return null;
	}
	
	public ComboBox<String> getCombo(){
		return this.qsCoList;
	}
	
}
