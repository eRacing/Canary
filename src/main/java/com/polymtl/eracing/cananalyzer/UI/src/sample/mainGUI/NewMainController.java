package sample.mainGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class NewMainController {

    @FXML private Menu viewMenu;

    @FXML private Menu toolsMenu;

    @FXML private TextArea statusBar;

    @FXML private ContextMenu dataBaseContextMenu;

    @FXML private TableView<?> tableView;

    @FXML private TableColumn<?, ?> timeColumn;

    @FXML private TableColumn<?, ?> txColumn;

    @FXML private TableColumn<?, ?> channelColumn;

    @FXML private TableColumn<?, ?> messageColumn;

    @FXML private TableColumn<?, ?> DLCColumn;

    @FXML private TableColumn<?, ?> byteColumn;



    @FXML
    void DataBaseChecked(MouseEvent event) {

    }

    @FXML
    void associateButtonClicked(ActionEvent event) {

    }

    @FXML
    void associateButtonEntered(MouseEvent event) {

    }

    @FXML
    void associateButtonExited(MouseEvent event) {

    }

    @FXML
    void canMessageChecked(MouseEvent event) {

    }

    @FXML
    void disconnectButtonEntered(MouseEvent event) {

    }

    @FXML
    void disconnectButtonExited(MouseEvent event) {

    }

    @FXML
    void disconnectButtonPressed(MouseEvent event) {

    }

    @FXML
    void graphButtonEntered(MouseEvent event) {

    }

    @FXML
    void graphButtonExited(MouseEvent event) {

    }

    @FXML
    void graphButtonPressed(MouseEvent event) {

    }

}

