import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public abstract class BasePane extends BorderPane {
	
	protected abstract void buildLayout();

}

class SelectionPane extends BasePane{
	
	private ComboBox<String> qsCoList;
	private Label selectLbl;
	private String[] qsArr;
	private ArrayList<Question> qsList;
	private ObservableList<String> qsObList;
	
	public SelectionPane(ArrayList<Question> qsList){
		
		this.qsCoList = new ComboBox<String>();
		
		this.selectLbl = new Label("Select");

		this.qsList = qsList;
		
		this.qsArr = new String[qsList.size()];
		
		// filling the array of question text for each question object
		for(int i = 0; i < qsArr.length; i++) {
			qsArr[i] = qsList.get(i).getID() +" - "+ qsList.get(i).getQuestionText();
		}
		
		this.qsObList = FXCollections.observableArrayList(qsArr);
		//Build the layout
		buildLayout();
		
	}

	@Override
	protected void buildLayout() {
		
		this.qsCoList.getItems().addAll(qsObList);
		this.qsCoList.setValue("-- Select a Question --");

		
		
		this.qsCoList.setPrefWidth(Double.MAX_VALUE);
		this.qsCoList.setStyle("-fx-alignment: center; -fx-text-alignment: center");

		this.setCenter(this.qsCoList);
		
	}
	public ComboBox<String> getCombo(){
		return this.qsCoList;
	}
	public Question getQuestion() {
		
		String questionInCB = this.qsCoList.getSelectionModel().getSelectedItem();
		
		if(questionInCB == "-- Select a Question --") return null;
		
		if(questionInCB != null) {
			//get the index from the numbers in each item in combo box
			int index = Integer.parseInt(questionInCB.split(" ")[0]) - 1;
			return this.qsList.get(index);
		}
			
		//if questionInCB == null, then return the first question
		return this.qsList.get(0);
	}
	
}
