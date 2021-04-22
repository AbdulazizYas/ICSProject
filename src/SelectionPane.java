import java.util.ArrayList;

import javafx.util.Callback;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SelectionPane extends BorderPane {

	public static ComboBox<String> qsCoList = new ComboBox<>();
	private TopBarPane topBar;
	
	public SelectionPane(String title) {
	    topBar = new TopBarPane(Main.stage,title);
	    
		qsCoList.setPromptText("-- Select a Question --");;
		qsCoList.setPrefWidth(Double.MAX_VALUE);
		
		qsCoList.setStyle("-fx-background-color: #FFF;-fx-border-radius:0;-fx-border-width:3;"
				+ "-fx-border-color: #FFF;" + "-fx-cursor: hand;");
		
		BorderPane content = new BorderPane();
		content.setCenter(qsCoList);
		content.setPadding(new Insets(25,25,30,25));
		content.setStyle(Commons.primaryGradient);
		if(Commons.customBar)
		this.setTop(topBar);
		this.setCenter(content);

	}

	public static ObservableList<String> getFormattedList(ArrayList<Question> qsList) {

		String[] qsArr = new String[qsList.size()];

		// filling the array of question text for each question object
		for (int i = 0; i < qsArr.length; i++) {
			qsArr[i] = (i +1) + " - " + qsList.get(i).getQuestionText();
		}
		return FXCollections.observableArrayList(qsArr);
	}

	public Question getQuestion(ArrayList<Question> qsList) {

		String questionInCB = qsCoList.getValue();

		if (questionInCB == "-- Select a Question --")
			return null;

		if (questionInCB != null) {
			// get the index from the numbers in each item in combo box
			int index = Integer.parseInt(questionInCB.split(" ")[0]) - 1;
			return qsList.get(index);
		}

		// if questionInCB == null, then return the first question || *NOTE* THIS FIX A BUG THAT RETURN NULL FOR FIRST QUESTION 
		return qsList.get(0);
	}

}
