package journalapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javafx.stage.FileChooser;

public class JournalExecutionStrategy implements ExecutionStrategy {
	
	Handler handler;
	
	public void execute(String command, Handler handler) {
		this.handler = handler;
		
		if(command=="New") {
			this.handler.grid.tf.setDisable(false);
			this.handler.grid.saveButton.setDisable(false);
			this.handler.grid.clearButton.setDisable(false);
		} else if(command == "Save As") {
				FileChooser fc = new FileChooser();
				File file = fc.showSaveDialog(this.handler.grid.stage);
				if (file != null) {
					this.handler.grid.label.setText("Saving: " + file.getName() + "." + "\n");
					try {
						this.handler.save(new PrintWriter(file));
						this.handler.grid.label.setText("Saved!");
					}
					catch (Exception e) {
						this.handler.grid.label.setText("Something went wrong. Please try again. \n");
					}
				} else {
					this.handler.grid.label.setText("Save command cancelled by user.");
				}
		} else if(command == "Clear") {
			this.handler.grid.tf.clear();
		} else if(command == "Open") {
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(this.handler.grid.stage);

			if (file != null) {
				this.handler.grid.label.setText("Opening: " + file.getName() + "." + "\n");
				BufferedReader bufferedReader;
				try {
					bufferedReader = new BufferedReader(new FileReader(file));
					this.handler.open(bufferedReader);
				} catch (Exception e1) {
				} 
			} else {
				this.handler.grid.label.setText("Open command cancelled by user." + "\n");
			}
		}
	}
}
