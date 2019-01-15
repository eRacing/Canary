package sample.mainGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGUIController implements Initializable {

    @FXML private Menu toolsMenu;

    @FXML private TextArea statusBar;

    @FXML private ContextMenu dataBaseContextMenu;

    @FXML private AnchorPane graphCanva;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


    //View Menu
    @FXML
    void DataBaseChecked(MouseEvent event) {

    }

    //View Menu
    @FXML
    void canMessageChecked(MouseEvent event) {



    }

    @FXML
    void disconnectButtonPressed(ActionEvent event) {

    }

    @FXML
    void graphButtonPressed(ActionEvent event) {

    }

    @FXML
    void associateButtonClicked(ActionEvent event) {

    }


}

