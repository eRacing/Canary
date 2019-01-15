package sample.canMessageTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Message.Message;
import java.net.URL;
import java.util.ResourceBundle;

public class CanMessageTableController implements Initializable{

    private ObservableList<Message> messages;

    @FXML
    TableView tableView;

    @FXML
    private TableColumn<TableView, Double> timeColumn;

    @FXML
    private TableColumn<TableView, String> txColumn;

    @FXML
    private TableColumn<TableView, Integer> channelColumn;

    @FXML
    private TableColumn<TableView, String> messageColumn;

    @FXML
    private TableColumn<TableView, Integer> DLCColumn;

    @FXML
    private TableColumn<TableView, String> byteColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //creating test message list
        messages = FXCollections.observableArrayList();

        //associates the columns to the attributes of Message, has to have the same name of attribute
        initColumns();



    }

    private void initColumns(){
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
        txColumn.setCellValueFactory(new PropertyValueFactory<>("TxRx"));
        channelColumn.setCellValueFactory(new PropertyValueFactory<>("channel"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        DLCColumn.setCellValueFactory(new PropertyValueFactory<>("DLC"));
        byteColumn.setCellValueFactory(new PropertyValueFactory<>("bytes"));

        //connect the list to the view
        tableView.setItems(messages);
    }

    public void addMessage(Message message){

        //adding the list to tableView, automatically shows on the view
        tableView.getItems().add(message);

    }

    public void removeMessage(Message message){
        messages.remove(message);
    }
    }
