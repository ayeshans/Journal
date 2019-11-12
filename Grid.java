package journalapplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

public class Grid extends Application {
	
	TextArea tf;
	Button saveButton;
	Button clearButton;
	Stage stage;
	Label label;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		init(stage);
	}
	
	private void init(Stage stage) {
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(10));
		
		// Welcome Label
		Label l = new Label("Welcome! Click a button to get started.");
		pane.add(l, 1, 0);
		
		// Message Label
		Label label = new Label("(As you click on buttons, helpful messages will appear here.)");
		pane.add(label, 1,3);
		this.label=label;
		
		// Making buttons
		makeButton(pane, "New",2,"new_entry.png");
		makeButton(pane, "Open",3,"open_old_entry.png");
		makeButton(pane, "Save As", 2, "save_as.png");
		makeButton(pane, "Clear", 3, "clear.png");
		makeButton(pane, "Help", 5, "help.png");
		
		// Text Area
		TextArea tf = new TextArea();
		this.tf=tf;
		tf.setPromptText("Start by typing here...");
		tf.setDisable(true);
		pane.add(tf, 1, 2);
		
		stage.setTitle("Journal v.1.0");
		stage.setScene(scene);
		stage.show();
	}

	private void makeButton(GridPane pane, String buttonLabel, int columnNumber, String fileName) {
		try {
			FileInputStream input = new FileInputStream("icons/" + fileName);
			Image im = new Image(input);
			ImageView imview = new ImageView(im);
			Node n = ((Node) imview);
			n.setId(buttonLabel);
			if(buttonLabel == "Save As") {
				this.saveButton = new Button("", n);
				pane.add(saveButton, columnNumber, 2);
				saveButton.setOnAction(new Handler(this));
				saveButton.setDisable(true);
			} else if (buttonLabel == "Clear") {
				this.clearButton = new Button("", n);
				pane.add(clearButton, 3, 2);
				clearButton.setOnAction(new Handler(this));
				clearButton.setDisable(true);
			} else if (buttonLabel == "Help"){
				Button button = new Button("", n);
				pane.add(button, 3, 3);
				button.setOnAction(new Handler(this));
			} else {
				Button button = new Button("", n);
				pane.add(button, columnNumber, 0);
				button.setOnAction(new Handler(this));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: file not found");
		}
		
	}


}
