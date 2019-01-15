package sample.mainGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUIController implements Initializable {

    @FXML private Menu toolsMenu;

    @FXML private TextArea statusBar;

    @FXML private ContextMenu dataBaseContextMenu;

    @FXML private AnchorPane graphCanva;

    @FXML private ComboBox driverButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initDriverButton();

        try {
            initCanTable();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initCanTable() throws IOException {

        AnchorPane tableWindow =FXMLLoader.load(getClass().getResource("/sample/canMessageTable/canMessageTable.fxml"));
        graphCanva.getChildren().add(tableWindow);
        tableWindow.prefWidthProperty().bind(graphCanva.widthProperty());
        tableWindow.prefHeightProperty().bind(graphCanva.heightProperty());
    }

    private void initDriverButton() {

        driverButton.getItems().addAll("PEAK USB", "WIFI SOMEDAY", "3G SOMEDAY", "UNDERGROUD TUNNEL");

    }


    //View Menu
    @FXML
    void DataBaseChecked(MouseEvent event) {

        //TODO
        /* - hide the treeView when unChecked, show otherwise*/

    }

    //View Menu
    @FXML
    void canMessageChecked(MouseEvent event) {

        //TODO
        /* - hide the tableView when unChecked, show otherwise*/

    }

    @FXML
    void disconnectButtonPressed(ActionEvent event) {

    }

    @FXML
    void graphButtonPressed(ActionEvent event) {

    }

    @FXML
    void associateButtonClicked(ActionEvent event) {

        //TODO
        /* - link the dataBase to the treeView
        *  - update the tree view to show its structure*/

        //adds a navigator within the computer
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }




}

