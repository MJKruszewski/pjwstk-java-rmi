package zad1.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import zad1.web.interfaces.PhoneDirectoryInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class ClientController {

    public TextField searchPersonData;
    public TextField insertPersonData;
    public TextField insertPhoneData;
    public TextField searchResult;
    public Label errorInfo;

    private PhoneDirectoryInterface directoryInterface;

    public ClientController() {
        tryReconnect();
    }

    @FXML
    public void addOrReplace(ActionEvent event) {
        errorInfo.setText(null);

        try {
            String phoneNumber = directoryInterface.getPhoneNumber(insertPersonData.getText());

            if (insertPersonData.getText() == null || insertPersonData.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bad Input");
                alert.setHeaderText("Information");
                alert.setContentText("Provided personal data is incorrect");
                alert.showAndWait();

                return;
            }

            if (insertPhoneData.getText() == null || insertPhoneData.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bad Input");
                alert.setHeaderText("Information");
                alert.setContentText("Provided phone is incorrect");
                alert.showAndWait();

                return;
            }

            if (null == phoneNumber) {
                System.out.println("Adding new phone number");
                directoryInterface.addPhoneNumber(insertPersonData.getText(), insertPhoneData.getText());
            } else {
                System.out.println("Replacing phone number");
                directoryInterface.replacePhoneNumber(insertPersonData.getText(), insertPhoneData.getText());
            }
        } catch (RemoteException | NullPointerException e) {
            tryReconnect();
        }
    }

    @FXML
    public void search(ActionEvent event) {
        errorInfo.setText(null);

        if (searchPersonData.getText() == null || searchPersonData.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Bad Input");
            alert.setHeaderText("Information");
            alert.setContentText("Provided search is incorrect");
            alert.showAndWait();

            return;
        }

        try {
            System.out.println("Searching phone number");
            searchResult.setText(directoryInterface.getPhoneNumber(searchPersonData.getText()));
        } catch (RemoteException | NullPointerException e) {
            tryReconnect();
        }
    }

    private void tryReconnect() {
        if (errorInfo != null) {
            errorInfo.setText("There is problem with remote server, please try again");
        }

        try {
            directoryInterface = (new PhoneDirectoryClient()).preparePhoneDirectoryService();
            System.out.println("Connected to registry");
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Cannot connect to registry");
        }
    }
}
