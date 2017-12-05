package FacebookGUI.Controllers;


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

import java.util.Random;


public class LogoutPromptController implements Controller {


    @FXML
    private Button okButton;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void initialize(){
    }

    public void ok() throws Exception{
        Stage stage = (Stage) okButton.getScene().getWindow();
        mainApp.saveUserData();
        stage.close();
        //segueNewFrame("View/LoginForm.fxml");
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
