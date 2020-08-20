package edu.bsu.cs222;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class View extends Application {
    Main main = new Main();
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox parent = new VBox();
        parent.getChildren().add(new Label("Which Page would you like to search for?"));
        final TextField searchRequest = new TextField();
        parent.getChildren().add(searchRequest);

        parent.getChildren().add(new Label("How many revisions would you like to see?"));
        final ComboBox<Integer> quantity = new ComboBox<>();
        ArrayList<Integer> options = new ArrayList<>();
        for (int index = 1; index < 31; index++) {
            options.add(index);
        }
        quantity.getItems().addAll(options);
        quantity.setVisibleRowCount(5);
        parent.getChildren().add(quantity);
        Button button = new Button("Retrieve Revisions");
        parent.getChildren().add(button);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        parent.getChildren().add(textArea);
        button.setOnAction(event -> main.pageRequest(searchRequest.getText(), quantity.getValue(),textArea));
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }
}
