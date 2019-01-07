package sample.mainGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUIController implements Initializable {

    @FXML
    private MenuBar topMenu;
    @FXML
    private MenuItem newMenuItem;
    @FXML
    private TreeView<String> dataNavigator;
    @FXML
    private ListView<String> dataListView;
    @FXML
    private Pane chartView;
    @FXML
    private Button newVisualButton;
    @FXML
    private Button existingButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    void openExistingFile(ActionEvent event) {

        //testing
        System.out.println("OpenExistingFile...");

    }

    @FXML
    void startNewVisualisation(ActionEvent event) throws IOException {
        //testing
        System.out.println("Starting new visualisation...");

        Pane newRoot = FXMLLoader.load(getClass().getResource("/sample/repertoriesTree/repertoriesTree.fxml"));

        chartView.getChildren().setAll(newRoot);

        newRoot.prefWidthProperty().bind(chartView.widthProperty());
        newRoot.prefHeightProperty().bind(chartView.heightProperty());


    }

}
