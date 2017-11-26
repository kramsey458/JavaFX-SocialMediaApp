package sample.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Controller;
import sample.DataModel.UserModel;
import sample.DataModel.WallPost;
import sample.MainApp;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Observer;

public class MainController implements Controller, sample.DataModel.Observer {
    @FXML
    private Label title;

    @FXML
    private TableView<UserModel> friendTable;

    @FXML
    private Button simulateButton;

    @FXML
    private TableView<WallPost> newsfeedTable;

    @FXML
    private TableColumn<WallPost, String> newsfeedNameColumn;

    @FXML
    private TableColumn<WallPost, LocalDate> newsfeedDateColumn;

    @FXML
    private TableColumn<WallPost, String> newsfeedColumn;

    @FXML
    private TableColumn<UserModel, String> firstNameColumn;

    @FXML
    private TableColumn<WallPost, String> postColumn;

    @FXML
    private TableColumn<WallPost, LocalDate> postDateColumn;
    @FXML
    private TableView<WallPost> myPostsTable;

    @FXML
    private Button uploadPhotoButton;

    @FXML
    private ImageView profilePhoto;


    private MainApp mainApp;
    private String imageFile;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        friendTable.setItems(mainApp.getUserData().getCurrentUser().getFriendList());
        newsfeedTable.setItems(mainApp.getUserData().getCurrentUser().initializeNewsfeed());
        myPostsTable.setItems(mainApp.getUserData().getCurrentUser().getWallPosts());
        for (UserModel user : mainApp.getUserData().getCurrentUser().getFriendList())
        {
            user.setObserver(this);
        }
        displayUsername();
    }

    public void initialize(){
        displayFriendTable();
        displayWallPosts();
        displayNewsFeed();

    }

    public void update(){
        newsfeedTable.getItems().clear();
        newsfeedTable.setItems(mainApp.getUserData().getCurrentUser().initializeNewsfeed());
        newsfeedColumn.setCellValueFactory(cellData -> cellData.getValue().postContentProperty());
    }

    public void simulateWallPost() throws Exception{
        segueNewFrame("View/SimulatePostForm.fxml");
    }

    private void displayFriendTable(){
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
    }

    private void displayNewsFeed(){
        newsfeedColumn.setCellValueFactory(cellData -> cellData.getValue().postContentProperty());
        newsfeedDateColumn.setCellValueFactory(cellData -> cellData.getValue().postDateProperty());
        newsfeedNameColumn.setCellValueFactory(cellData -> cellData.getValue().ownerNameProperty());
    }

    private void displayWallPosts(){
        postColumn.setCellValueFactory(cellData -> cellData.getValue().postContentProperty());
        postDateColumn.setCellValueFactory(cellData -> cellData.getValue().postDateProperty());
    }

    private void displayUsername(){
        title.setText(mainApp.getUserData().getCurrentUser().getFullName());
    }

    public void uploadPhoto() throws MalformedURLException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();

            Image image = new Image(imageFile);
            profilePhoto.setImage(image);
        } else {

        }

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
