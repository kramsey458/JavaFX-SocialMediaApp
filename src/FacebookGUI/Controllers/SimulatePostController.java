package FacebookGUI.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import FacebookGUI.DataModel.UserModel;
import FacebookGUI.MainApp;

import java.util.Random;


public class SimulatePostController implements Controller {
    @FXML
    private Label name;

    @FXML
    private TextField newPost;

    @FXML
    private Button submitButton;

    private UserModel selectedUser = null;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        Random rand = new Random();
        selectedUser = mainApp.getUserData().getCurrentUser().getFriendList().get(0);
        if (selectedUser != null)
            name.setText(selectedUser.getFullName());
    }

    public void initialize(){
    }

    public void submitPost(){
        selectedUser.addWallPost(newPost.getText());
        Stage stage = (Stage) newPost.getScene().getWindow();
        stage.close();
    }



}
