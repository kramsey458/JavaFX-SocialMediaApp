package FacebookGUI.Controllers;


import FacebookGUI.DataModel.Observable;
import FacebookGUI.DataModel.Observer;
import FacebookGUI.DataModel.WallPost;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import FacebookGUI.DataModel.UserModel;
import FacebookGUI.MainApp;

import java.time.LocalDate;
import java.util.Random;


public class ProfileDisplayController implements Controller, Observable {
    @FXML
    private Label name;

    @FXML
    private Label friendStatus;

    @FXML
    private TableView<WallPost> postTable;

    @FXML
    private TableColumn<WallPost, String> postColumn;

    @FXML
    private ImageView profilePhoto;

    @FXML
    private Button followButton;
    boolean friend;

    private MainApp mainApp;
    private UserModel targetUser;
    private Observer observer = null;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setObserver(Observer o){
        observer = o;
    }

    public void notifyObserver(){
        observer.update();
    }

    public void setTargetUser(UserModel user) {
        this.targetUser = user;
        name.setText(targetUser.getFullName());

        setFollowButtonText();
        if (mainApp.getCurrentUser().getFriendList().contains(targetUser))
            displayWallPosts();
    }

    public void setFollowButtonText(){
        if (mainApp.getCurrentUser().getFriendList().contains(targetUser)) {
            friendStatus.setText("Friends");
            followButton.setText("Unfollow");
        }
        else if ( mainApp.getCurrentUser().getUsername().equals(targetUser.getUsername())){
            friendStatus.setText("Your profile");
            followButton.setText("N/A");
        }
        else {
            friendStatus.setText("Not friends");
            followButton.setText("Follow");
        }
        if (observer != null)
            observer.update();

    }

    public void initialize(){
    }

    private void displayWallPosts() {
        postTable.setItems(targetUser.getWallPosts());
        postColumn.setCellValueFactory(cellData -> cellData.getValue().postContentProperty());
    }

    public void followButton() throws Exception{
        UserModel current = mainApp.getCurrentUser();
        if ( mainApp.getCurrentUser().getUsername().equals(targetUser.getUsername())){
            displayWallPosts();
            return;
        }
        //Not friends case
        if (!current.getFriendList().contains(targetUser)){
            current.addFriend(targetUser);
            setFollowButtonText();
            displayWallPosts();
        }
        //Friends case, want to unfriend
        else {
            current.removeFriend(targetUser);
            setFollowButtonText();
        }
    }

    private void exit(){
        Stage stage = (Stage) name.getScene().getWindow();
        mainApp.saveUserData();
        stage.close();
    }

}
