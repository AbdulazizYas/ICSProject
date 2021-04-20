import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class TopBarPane extends BasePane{
	
	private Stage stage;
	private double xOff = 0;
	private double yOff = 0;
	private Label title ;
	private Label X = new Label("X");
	private Label maximize = new Label("\u25A0");
	private Label minimize = new Label("\u2501");
	
	public TopBarPane(Stage stage,String title) {
		this.stage = stage;
		this.title = new Label("Questioner || "+title);
	
		buildLayout();
	}
	
	@Override
	protected void buildLayout() {
		HBox buttons = new HBox(10);
		X.setFont(Font.font("Calbiri", 16));
		maximize.setFont(Font.font("Calbiri", 16));
		minimize.setFont(Font.font("Calbiri", 16));
		
		X.setPadding(new Insets(5,20,5,20));
		maximize.setPadding(new Insets(5,20,5,20));
		minimize.setPadding(new Insets(5,20,5,20));
		
		
		X.setStyle(Constants.cursor);
		maximize.setStyle(Constants.cursor);
		minimize.setStyle(Constants.cursor);
		
		X.setOnMouseEntered(e -> X.setStyle(Constants.cursor+ "-fx-background-color:#DF362D"));
		X.setOnMouseExited(e -> X.setStyle(Constants.cursor+"-fx-background-color: transparent"));
		
		maximize.setOnMouseEntered(e -> maximize.setStyle(Constants.cursor+ Constants.bgPrimary+"55"));
		maximize.setOnMouseExited(e -> maximize.setStyle(Constants.cursor+"-fx-background-color: transparent"));
		
		minimize.setOnMouseEntered(e -> minimize.setStyle(Constants.cursor+ Constants.bgPrimary+"55"));
		minimize.setOnMouseExited(e -> minimize.setStyle(Constants.cursor+"-fx-background-color: transparent"));
		
		
		X.setOnMouseClicked(e -> Platform.exit());
		maximize.setOnMouseClicked(e -> {
			if(stage.isMaximized())stage.setMaximized(false);
			else stage.setMaximized(true);
		});
		minimize.setOnMouseClicked(e -> stage.setIconified(true));
		
		X.setTextFill(Color.valueOf("#FFF"));
		maximize.setTextFill(Color.valueOf("#FFF"));
		minimize.setTextFill(Color.valueOf("#FFF"));
		
		this.prefWidthProperty().bind(stage.widthProperty());
		this.setPrefHeight(32);
		this.setStyle(Constants.bgPrimary+"aa");
		this.setRight(buttons);
		this.setLeft(this.title);
		
		this.title.setFont(Font.font("Calbiri", FontWeight.BOLD, 12));
		this.title.setTextFill(Color.valueOf("#FFF"));
		this.title.setPadding(new Insets(0,0,0,10));
		BorderPane.setAlignment(this.title, Pos.CENTER);
		
		buttons.getChildren().addAll(minimize,maximize,X);
	
		this.setOnMousePressed(e -> {
			xOff = e.getSceneX();
			yOff = e.getSceneY();
		});
		this.setOnMouseDragged(e -> {
			if(stage.isMaximized()) stage.setMaximized(false);
			stage.setX(e.getScreenX() - xOff);
			stage.setY(e.getScreenY() - yOff);
		});
		
		//for add resizing feature
		
		
	}

}


/* ================= ResizerHelper to set resizing feature to UNDECORATED stage ==============*/

class ResizeHelper {

    public static void addResizeListener(Stage stage) {
        addResizeListener(stage, 1, 1, Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public static void addResizeListener(Stage stage, double minWidth, double minHeight, double maxWidth, double maxHeight) {
        ResizeListener resizeListener = new ResizeListener(stage);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_MOVED, resizeListener);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, resizeListener);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_DRAGGED, resizeListener);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_EXITED, resizeListener);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, resizeListener);

        resizeListener.setMinWidth(minWidth);
        resizeListener.setMinHeight(minHeight);
        resizeListener.setMaxWidth(maxWidth);
        resizeListener.setMaxHeight(maxHeight);

        ObservableList<Node> children = stage.getScene().getRoot().getChildrenUnmodifiable();
        for (Node child : children) {
            addListenerDeeply(child, resizeListener);
        }
    }

    private static void addListenerDeeply(Node node, EventHandler<MouseEvent> listener) {
        node.addEventHandler(MouseEvent.MOUSE_MOVED, listener);
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, listener);
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, listener);
        node.addEventHandler(MouseEvent.MOUSE_EXITED, listener);
        node.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, listener);
        if (node instanceof Parent) {
            Parent parent = (Parent) node;
            ObservableList<Node> children = parent.getChildrenUnmodifiable();
            for (Node child : children) {
                addListenerDeeply(child, listener);
            }
        }
    }

    static class ResizeListener implements EventHandler<MouseEvent> {
        private Stage stage;
        private Cursor cursorEvent = Cursor.DEFAULT;
        private boolean resizing = true;
        private int border = 4;
        private double startX = 0;
        private double startY = 0;
        private double screenOffsetX = 0;
        private double screenOffsetY = 0;

        // Max and min sizes for controlled stage
        private double minWidth;
        private double maxWidth;
        private double minHeight;
        private double maxHeight;

        public ResizeListener(Stage stage) {
            this.stage = stage;
        }

        public void setMinWidth(double minWidth) {
            this.minWidth = minWidth;
        }

        public void setMaxWidth(double maxWidth) {
            this.maxWidth = maxWidth;
        }

        public void setMinHeight(double minHeight) {
            this.minHeight = minHeight;
        }

        public void setMaxHeight(double maxHeight) {
            this.maxHeight = maxHeight;
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            EventType<? extends MouseEvent> mouseEventType = mouseEvent.getEventType();
            Scene scene = stage.getScene();

            double mouseEventX = mouseEvent.getSceneX(),
                    mouseEventY = mouseEvent.getSceneY(),
                    sceneWidth = scene.getWidth(),
                    sceneHeight = scene.getHeight();

            if (MouseEvent.MOUSE_MOVED.equals(mouseEventType)) {
                if (mouseEventX < border && mouseEventY < border) {
                    cursorEvent = Cursor.NW_RESIZE;
                } else if (mouseEventX < border && mouseEventY > sceneHeight - border) {
                    cursorEvent = Cursor.SW_RESIZE;
                } else if (mouseEventX > sceneWidth - border && mouseEventY < border) {
                    cursorEvent = Cursor.NE_RESIZE;
                } else if (mouseEventX > sceneWidth - border && mouseEventY > sceneHeight - border) {
                    cursorEvent = Cursor.SE_RESIZE;
                } else if (mouseEventX < border) {
                    cursorEvent = Cursor.W_RESIZE;
                } else if (mouseEventX > sceneWidth - border) {
                    cursorEvent = Cursor.E_RESIZE;
                } else if (mouseEventY < border) {
                    cursorEvent = Cursor.N_RESIZE;
                } else if (mouseEventY > sceneHeight - border) {
                    cursorEvent = Cursor.S_RESIZE;
                } else {
                    cursorEvent = Cursor.DEFAULT;
                }
                scene.setCursor(cursorEvent);
            } else if (MouseEvent.MOUSE_EXITED.equals(mouseEventType) || MouseEvent.MOUSE_EXITED_TARGET.equals(mouseEventType)) {
                scene.setCursor(Cursor.DEFAULT);
            } else if (MouseEvent.MOUSE_PRESSED.equals(mouseEventType)) {
                startX = stage.getWidth() - mouseEventX;
                startY = stage.getHeight() - mouseEventY;
            } else if (MouseEvent.MOUSE_DRAGGED.equals(mouseEventType)) {
                if (!Cursor.DEFAULT.equals(cursorEvent)) {
                    resizing = true;
                    if (!Cursor.W_RESIZE.equals(cursorEvent) && !Cursor.E_RESIZE.equals(cursorEvent)) {
                        double minHeight = stage.getMinHeight() > (border * 2) ? stage.getMinHeight() : (border * 2);
                        if (Cursor.NW_RESIZE.equals(cursorEvent) || Cursor.N_RESIZE.equals(cursorEvent)
                                || Cursor.NE_RESIZE.equals(cursorEvent)) {
                            if (stage.getHeight() > minHeight || mouseEventY < 0) {
                                setStageHeight(stage.getY() - mouseEvent.getScreenY() + stage.getHeight());
                                stage.setY(mouseEvent.getScreenY() );
                            }
                        } else {
                            if (stage.getHeight() > minHeight || mouseEventY + startY - stage.getHeight() > 0) {
                                setStageHeight(mouseEventY + startY);
                            }
                        }
                    }

                    if (!Cursor.N_RESIZE.equals(cursorEvent) && !Cursor.S_RESIZE.equals(cursorEvent)) {
                        double minWidth = stage.getMinWidth() > (border * 2) ? stage.getMinWidth() : (border * 2);
                        if (Cursor.NW_RESIZE.equals(cursorEvent) || Cursor.W_RESIZE.equals(cursorEvent)
                                || Cursor.SW_RESIZE.equals(cursorEvent)) {
                            if (stage.getWidth() > minWidth || mouseEventX < 0) {
                                setStageWidth(stage.getX() - mouseEvent.getScreenX() + stage.getWidth());
                                stage.setX(mouseEvent.getScreenX());
                            }
                        } else {
                            if (stage.getWidth() > minWidth || mouseEventX + startX - stage.getWidth() > 0) {
                                setStageWidth(mouseEventX + startX);
                            }
                        }
                    }
                    resizing = false;
                }
            }

            if (MouseEvent.MOUSE_PRESSED.equals(mouseEventType) && Cursor.DEFAULT.equals(cursorEvent)) {
                resizing = false;
                screenOffsetX = stage.getX() - mouseEvent.getScreenX();
                screenOffsetY = stage.getY() - mouseEvent.getScreenY();
            }

            if (MouseEvent.MOUSE_DRAGGED.equals(mouseEventType) && Cursor.DEFAULT.equals(cursorEvent) && resizing == false) {
                stage.setX(mouseEvent.getScreenX() + screenOffsetX);
                stage.setY(mouseEvent.getScreenY() + screenOffsetY);
            }

        }

        private void setStageWidth(double width) {
            width = Math.min(width, maxWidth);
            width = Math.max(width, minWidth);
            stage.setWidth(width);
        }

        private void setStageHeight(double height) {
            height = Math.min(height, maxHeight);
            height = Math.max(height, minHeight);
            stage.setHeight(height);
        }

    }
}