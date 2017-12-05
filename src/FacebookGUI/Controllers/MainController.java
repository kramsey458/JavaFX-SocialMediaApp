package FacebookGUI.Controllers;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import FacebookGUI.DataModel.UserModel;
import FacebookGUI.DataModel.WallPost;
import FacebookGUI.MainApp;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;

public class MainController implements Controller, FacebookGUI.DataModel.Observer {
    @FXML
    private Label title;

    @FXML
    private TableView<UserModel> friendTable;

    @FXML
    private Button simulateButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<WallPost> newsfeedTable;

    @FXML
    private TableColumn<WallPost, String> newsfeedNameColumn;

    @FXML
    private TableView<UserModel> searchTable;

    @FXML
    private TableColumn<UserModel, String> searchColumn;

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
    private Button addPostButton;

    @FXML
    private ImageView profilePhoto;

    @FXML
    private TextField filterField;

    private ObservableList<UserModel> masterData = FXCollections.observableArrayList();



    private MainApp mainApp;
    private String imageFile;


    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        masterData = mainApp.getUserData().getUserDataList();
        friendTable.setItems(mainApp.getCurrentUser().getFriendList());
        searchTable.setItems(mainApp.getUserData().getUserDataList());
        newsfeedTable.setItems(mainApp.getCurrentUser().initializeNewsfeed());
        myPostsTable.setItems(mainApp.getCurrentUser().getWallPosts());
        for (UserModel user : mainApp.getCurrentUser().getFriendList())
        {
            user.setObserver(this);
        }
        displayUsername();
    }

    public void initialize(){
        displayFriendTable();
        displayWallPosts();
        displayNewsFeed();
        displaySearchTable();

    }

    @FXML
    public void clickItem(MouseEvent event) throws Exception {
        if (event.getClickCount() == 2) //Checking double click
        {
            UserModel targetUser = searchTable.getSelectionModel().getSelectedItem();
            displayProfile("View/ProfileDisplayForm.fxml", targetUser);
        }
    }

    @FXML
    public void clickItemFriends(MouseEvent event) throws Exception {
        if (event.getClickCount() == 2) //Checking double click
        {
            UserModel targetUser = friendTable.getSelectionModel().getSelectedItem();
            displayProfile("View/ProfileDisplayForm.fxml", targetUser);
        }
    }

    public void settingsClicked() throws Exception {
        segueNewFrame("View/SettingsForm.fxml");
    }


    public void logout(ActionEvent event) throws Exception{
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        segueNewFrame("View/LogoutPromptForm.fxml");
    }
    public void update(){
        newsfeedTable.getItems().clear();
        newsfeedTable.setItems(mainApp.getCurrentUser().initializeNewsfeed());
        newsfeedColumn.setCellValueFactory(cellData -> cellData.getValue().postContentProperty());
    }

    public void simulateWallPost() throws Exception{
        segueNewFrame("View/SimulatePostForm.fxml");
    }

    public void addWallPost() throws Exception{
        segueNewFrame("View/NewPostForm.fxml");
    }

    private void displayFriendTable(){
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
    }

    public void displaySearchTable(){

        searchColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        ObservableList data =  searchTable.getItems();
        filterField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                searchTable.setItems(masterData);
            }
            if (newValue == null){
                searchTable.setItems(masterData);
            }
            String value = newValue.toLowerCase();
            ObservableList<UserModel> subentries = FXCollections.observableArrayList();

            long count = searchTable.getColumns().stream().count();
            for (int i = 0; i < searchTable.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + searchTable.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(searchTable.getItems().get(i));
                        break;
                    }
                }
            }
            searchTable.setItems(subentries);
        });

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
        title.setText(mainApp.getCurrentUser().getFullName());
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

    public void displayProfile(String form, UserModel user) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(form));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
        setTargetUser(loader.getController(), user);
    }

    private void setDestinationControllerDataSource(Controller controller){
        controller.setMainApp(mainApp);
    }

    private void setTargetUser(ProfileDisplayController controller, UserModel user){
        controller.setMainApp(mainApp);
        controller.setTargetUser(user);
        controller.setObserver(this);
    }

}
