package FacebookGUI;

import FacebookGUI.JSONParsing.JSONFriendsParser;
import FacebookGUI.JSONParsing.JSONNewsfeedParser;
import FacebookGUI.JSONParsing.JSONUserParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import FacebookGUI.Controllers.Controller;
import FacebookGUI.DataModel.UserData;
import FacebookGUI.DataModel.UserModel;

public class MainApp extends Application {

    private UserData userData = new UserData();
    private UserModel currentUser;

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
        setCurrentUser(null);
        //JSONFriendsParser friendWriter = new JSONFriendsParser(userData);
        //friendWriter.initializeFriends();
        //friendWriter.readFriends();
       // JSONUserParser userParser = new JSONUserParser(userData);
        //userParser.initializeUsers();
       // userParser.readUsers();
        JSONNewsfeedParser newsfeedParser = new JSONNewsfeedParser(userData);
        //newsfeedParser.initializeNewsfeed();
        newsfeedParser.readNewsfeed();
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

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }
}
