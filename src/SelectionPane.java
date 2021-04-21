import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class SelectionPane extends BorderPane {

	public static ComboBox<String> qsCoList = new ComboBox<>();

	public SelectionPane() {

		qsCoList.setPromptText("-- Select a Question --");;

		qsCoList.setPrefWidth(Double.MAX_VALUE);
		qsCoList.setStyle("-fx-alignment: center; -fx-text-alignment: center");

		this.setCenter(qsCoList);

	}

	public static ObservableList<String> getFormattedList(ArrayList<Question> qsList) {

		String[] qsArr = new String[qsList.size()];

		// filling the array of question text for each question object
		for (int i = 0; i < qsArr.length; i++) {
			qsArr[i] = qsList.get(i).getID() + " - " + qsList.get(i).getQuestionText();
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
