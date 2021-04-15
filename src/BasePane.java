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
		
		
		HBox hb = new HBox(15);
		hb.setPadding(new Insets(35,20,35,20));
		hb.getChildren().addAll(this.selectLbl, this.qsCoList);
		hb.setAlignment(Pos.CENTER);
		hb.setPrefWidth(400);
		
		
		this.qsCoList.setPrefWidth(hb.getPrefWidth() - 100);
		
		this.setCenter(hb);
		
	}
	
	public Question getQuestion() {
		String questionInCB = this.qsCoList.getValue();
		
		String[] tempArr = questionInCB.split(" ");
		return this.qsList.get(Integer.parseInt(tempArr[0])-1);
	}
	
}
