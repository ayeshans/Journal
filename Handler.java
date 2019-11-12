package journalapplication;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Handler implements EventHandler<ActionEvent> {
	
	Grid grid;
	ExecutionStrategy sf;
	
	public Handler(Grid grid) {
		this.grid = grid;
		this.sf = new JournalExecutionStrategy();
	}
	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getGraphic().getId();
		((Button)event.getSource()).setEffect(new DropShadow());
		this.grid.label.setText(command);
		this.sf.execute(command, this);
	}

	public void save(PrintWriter printWriter) {
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
		String text = this.grid.tf.getText();
		
		printWriter.println(date.toString()+":"+"\n");
		printWriter.println(text);
		printWriter.close();
	}
	
	public void open(BufferedReader reader) throws IOException {
		String txt = null;
		String l;
		while ((l = reader.readLine()) != null) {
			if(txt==null) {
				txt=l+"\n";
			} else {
				txt=txt+l;
			}
		}
		this.grid.label.setText(txt);
	}
		

}
