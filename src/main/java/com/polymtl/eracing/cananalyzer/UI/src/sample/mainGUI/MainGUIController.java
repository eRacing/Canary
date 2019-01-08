package sample.mainGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUIController {

    @FXML
    private MenuItem newButton;

    @FXML
    private TreeView<?> dataNavigator;

    @FXML
    private ListView<?> dataListView;

    @FXML
    private MenuBar topMenu;

    @FXML
    private Pane chartView;

    @FXML
    private Button newVisualButton;


    @FXML
    void startNewVisualisation(ActionEvent event) throws IOException {

        //testing
        Parent root = FXMLLoader.load(getClass().getResource("/sample/listView/listView.fxml"));

        Stage stage = new Stage();

        stage.setTitle("Messages Visualisation - Canary");

        stage.setScene(new Scene(root, chartView.getWidth(), chartView.getHeight()));

        stage.show();

    }

}
