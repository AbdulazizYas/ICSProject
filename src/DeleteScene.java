import java.util.ArrayList;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;


public class DeleteScene extends BaseScene{	
	
	private SelectionPane selectPane;
	private DisplayPane display;
	
	private ArrayList<Question> qstList;
	
	private Button delete;
	
	public DeleteScene(ArrayList<Question> qstList) {
		
		this.qstList = qstList;
		
		this.display = new DisplayPane();
		
		// Changing the title and putting a combo box for the DeleteScene
		this.selectPane = new SelectionPane("Delete a question");

		// Set the items of combo box with the question list when the DeleteScene is created
		this.selectPane.getCombo().setItems(SelectionPane.getFormattedList(qstList));
		
		// Handle the selection of combo box in view scene with handleDisplay located in DisplayPane
		this.selectPane.getCombo().setOnAction(e -> this.display.handleDisplay(this.selectPane.getQuestion(this.qstList)));
		
		this.delete = new Button("Delete");
		this.delete.setOnAction(e -> handleDelete());
		
		build();
	}

	@Override
	protected void build() {
		
		// Styling the delete button
		this.delete.setStyle(Commons.btn + Commons.bgPrimary + ";-fx-font-size:16; -fx-text-fill:#FFF");
		this.delete.setPadding(new Insets(10));
		this.delete.prefWidthProperty().bind(Main.stage.widthProperty().divide(10));
		
		// A BorderPane for the buttons
		BorderPane buttons = new BorderPane();
		buttons.setLeft(this.back);
		buttons.setRight(this.delete);
		buttons.setPadding(new Insets(25));
		buttons.setStyle(Commons.bgSecondary);
		
		this.display.setPadding(new Insets(25));
		
		// root refers to the BorderPane inherited from the BaseScene Class
		this.root.setTop(selectPane); // ComboBox
		this.root.setCenter(display); // Displaying question + answers
		this.root.setBottom(buttons); // Buttons

	}
	
	private void handleDelete() {
		// Getting the question from the ComboBox
		Question q = this.selectPane.getQuestion(this.qstList);
		
		if (q != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			
			alert.setTitle("Delete Confirmation");
			alert.setHeaderText("Are you sure you want to delete this question ?");
			alert.setContentText("This action can't be undone later");
			
			// This will take input if the user wants to delete the question or not
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				
				// Getting the question object and removing it from the question list
				Boolean deleted = qstList.remove(q);
				if (deleted) {
					display.setDefaultLabels(); // Changing the labels
					Commons.openAlert("Success", "Your question has been deleted successfully!", Alert.AlertType.INFORMATION);
					Commons.updateAllCombos(qstList);
				}
			}
		}
		else {
			// An alert if the user didn't choose a question to delete
			Commons.openAlert("Failed to delete", "Please choose or create a question to delete!", Alert.AlertType.INFORMATION);
		}
	
	}
	
	public SelectionPane getSelectPane() {
		return this.selectPane;
	}
}

