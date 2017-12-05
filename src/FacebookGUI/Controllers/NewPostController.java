package FacebookGUI.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import FacebookGUI.DataModel.UserModel;
import FacebookGUI.MainApp;

import java.util.Random;


public class NewPostController implements Controller {

    @FXML
    private TextArea newPost;

    @FXML
    private Button submitButton;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void initialize(){
    }

    public void submitPost(){
        mainApp.getCurrentUser().addWallPost(newPost.getText());
        Stage stage = (Stage) newPost.getScene().getWindow();
        mainApp.saveUserData();
        stage.close();
    }



}
