package sample.listView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Message.Message;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListViewController implements Initializable{

    private ArrayList<Message> messages;

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
        messages = new ArrayList<Message>();
        for(int i = 0; i < 10; i++){
            messages.add(new Message(1, 0, ("message"+ i),"00 00 00 00", i, "tx"));

            //adding the list to tableView

            tableView.edit(0, timeColumn, Double.valueOf(messages.get(i).getTimeStamp()));
            tableView.edit(1, txColumn, messages.get(i).getTimeStamp());
            tableView.edit(2, channelColumn, messages.get(i).getTimeStamp());
            tableView.edit(3, messageColumn, messages.get(i).getTimeStamp());
            tableView.edit(4, DLCColumn, messages.get(i).getTimeStamp());
            tableView.edit(5, byteColumn, messages.get(i).getTimeStamp());

        }

        tableView.getColumns().addAll(timeColumn,txColumn,channelColumn, messageColumn, DLCColumn, byteColumn);



    }

    public void addMessage(Message message){

        messages.add(message);

    }

    public void removeMessage(Message message){
        messages.remove(message);
    }
}
