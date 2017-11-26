package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DataModel.UserData;
import sample.DataModel.UserModel;

public class MainApp extends Application {

    private UserData userData = new UserData();

    public MainApp(){
        UserModel kyler = new UserModel("Kyler", "Ramsey", "user", "pass");
        UserModel kristin = new UserModel("Kristin", "Ramsey", "user1", "pass1");
        UserModel ken = new UserModel("Ken", "Ramsey", "user2", "pass2");
        kyler.addFriend(kristin);
        kyler.addFriend(ken);
        kyler.addWallPost("this is a test post");
        ken.addWallPost("this is a second post!");
        kristin.addWallPost("this is a newsfeed post!");
        kyler.addWallPost("late post");
        userData.addUser(kyler);
        userData.addUser(kristin);
        userData.addUser(ken);
        userData.setCurrentUser(null);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("View/LoginForm.fxml"));

        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();

        setDestinationControllerDataSource(loader.getController());
    }

    private void setDestinationControllerDataSource(Controller controller){
        controller.setMainApp(this);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public UserData getUserData() {
        return userData;
    }
}
