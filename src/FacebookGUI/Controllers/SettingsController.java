package FacebookGUI.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import FacebookGUI.DataModel.UserModel;
import FacebookGUI.MainApp;

import java.util.ArrayList;
import java.util.Random;


public class SettingsController implements Controller {


    @FXML
    private Button submitButton;

    @FXML
    private Label name;

    @FXML
    private Button deactivate;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void initialize(){
    }

    public void submit(ActionEvent event) throws Exception{
        ArrayList<String> usernameList = new ArrayList<String>();
        for (UserModel user : mainApp.getUserData().getUserDataList()){
            usernameList.add(user.getUsername());
        }
        if (usernameList.contains(username.getText()) == false) {
            mainApp.getCurrentUser().setUsername(username.getText());
            mainApp.getCurrentUser().setPassword(password.getText());
            Stage stage = (Stage) username.getScene().getWindow();
            mainApp.saveUserData();
            stage.close();
        }
        else {
            name.setText("Invalid User");
        }

    }

    public void deactivate(ActionEvent event) throws Exception{
        mainApp.getUserData().getUserDataList().remove(mainApp.getCurrentUser());
        Stage stage = (Stage) deactivate.getScene().getWindow();
        mainApp.saveUserData();
        stage.close();
    }

    public void segueNewFrame(String form) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(form));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(loader.load());
        setDestinationControllerDataSource(loader.getController());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setDestinationControllerDataSource(Controller controller){
        controller.setMainApp(mainApp);
    }



}
