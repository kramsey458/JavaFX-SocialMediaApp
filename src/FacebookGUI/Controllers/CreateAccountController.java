package FacebookGUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import FacebookGUI.DataModel.UserModel;
import FacebookGUI.MainApp;


public class CreateAccountController implements Controller {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private Button submitButton;

    private MainApp mainApp;

    public void CreateAccount(ActionEvent event) throws Exception{
        String firstname = txtFirstName.getText();
        String lastname = txtLastName.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        mainApp.getUserData().addUser(new UserModel(firstname, lastname, username, password));
        Stage stage = (Stage) txtFirstName.getScene().getWindow();
        stage.close();
    }

    public void segueNewFrame(String form) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(form));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
        setDestinationControllerDataSource(loader.getController());
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    private UserModel getCurrentUser(String username){
        return mainApp.getUserData().searchUser(username);
    }

    private void setDestinationControllerDataSource(Controller controller){
        controller.setMainApp(mainApp);
    }
}
