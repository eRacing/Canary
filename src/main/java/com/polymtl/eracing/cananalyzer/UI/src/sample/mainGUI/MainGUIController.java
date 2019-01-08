package sample.mainGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
        System.out.println("Starting new visualisation...");

        Pane newRoot = FXMLLoader.load(getClass().getResource("/sample/repertoriesTree/repertoriesTree.fxml"));



        newRoot.prefWidthProperty().bind(chartView.widthProperty());
        newRoot.prefHeightProperty().bind(chartView.heightProperty());



    }

}
