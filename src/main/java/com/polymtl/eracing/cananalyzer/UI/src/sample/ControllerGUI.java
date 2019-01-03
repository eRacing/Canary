package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGUI implements Initializable {

    @FXML
    private TreeView<String> repertoriesTree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //initializing TreeView
        TreeItem<String> root = new TreeItem<String>("DB");
        TreeItem<String> msg = new TreeItem<String>("msg");
        TreeItem<String> signal = new TreeItem<String>("signal");

        root.getChildren().add(msg);
        msg.getChildren().add(signal);

        repertoriesTree.setRoot(root);

    }
}
