import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class CreateScene extends BaseScene {

	private Button create;
	private FormPane form;
	private TopBarPane topBar;
	private ArrayList<Question> qsList;

	public CreateScene(ArrayList<Question> qsList) {

		this.qsList = qsList;
		this.create = new Button("Create");
		this.form = new FormPane(this.create, this.back);
		this.topBar = new TopBarPane(Main.stage,"Create a question");

		this.create.setOnAction(e -> handleCreating());
		
		build();
	}

	// @Override
	protected void build() {
		Label title = new Label("Create your question");
		title.prefWidthProperty().bind(root.widthProperty().divide(3));
		title.setStyle(Commons.title+Commons.shadow + "-fx-font-size: 16");
		title.setAlignment(Pos.CENTER);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setPadding(new Insets(10,5,10,5));
		BorderPane.setAlignment(title, Pos.CENTER);
		
		BorderPane content = new BorderPane();
		
		if(Commons.customBar) {
			content.setStyle(Commons.bgWhite + Commons.innerShadow);
			content.setPadding(new Insets(25));
			content.setTop(title);
			content.setCenter(this.form);
			root.setTop(this.topBar);
			root.setCenter(content);
		}else {
			root.setStyle(Commons.bgWhite);
			root.setPadding(new Insets(25));
			root.setTop(title);
			root.setCenter(this.form);
		}
		

	}

	private void handleCreating() {
		
		if (this.form.isFieldsEmpty()) {
			Commons.openAlert("Empty fields", "You must fill all the fields to create your question",
					Alert.AlertType.WARNING);
			return;
		}

		Question q = new Question( this.form.getQuestionTextField().getText(), this.form.getCorrectAnsField().getText(),
				this.form.getAnsOneField().getText(), this.form.getAnsTwoField().getText(),
				this.form.getAnsThreeField().getText());
		
		this.qsList.add(q);

		SelectionPane.qsCoList.setItems(SelectionPane.getFormattedList(this.qsList));

		Commons.openAlert("Success", "Your question has been created successfully!", Alert.AlertType.INFORMATION);

		this.form.getQuestionTextField().setText("");
		this.form.getCorrectAnsField().setText("");
		this.form.getAnsOneField().setText("");
		this.form.getAnsTwoField().setText("");
		this.form.getAnsThreeField().setText("");
	}

}
