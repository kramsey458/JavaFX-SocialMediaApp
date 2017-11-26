package sample.DataModel;

import java.time.LocalDate;
import java.util.Arrays;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel implements sample.DataModel.Observable{

    private StringProperty firstName;
    private StringProperty lastName;
    private ObservableList<WallPost> wallPosts = FXCollections.observableArrayList();
    private ObservableList<WallPost> newsfeed = FXCollections.observableArrayList();
    private ObservableList<UserModel> friendList = FXCollections.observableArrayList();
    private StringProperty username;
    private StringProperty password;
    private StringProperty fullName;
    private Observer observer = null;

    public UserModel(String firstName, String lastName, String username, String password) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        String fullname = firstName + " " + lastName;
        this.fullName = new SimpleStringProperty(fullname);
    }

    public ObservableList<WallPost> initializeNewsfeed(){
        newsfeed.removeAll();
        for (UserModel friend : friendList){
            newsfeed.addAll(friend.getWallPosts());
        }
        FXCollections.sort(newsfeed);
        return newsfeed;
    }


    public void setObserver(Observer o){
        observer = o;
    }

    public void notifyObserver(){
        observer.update();
    }
    public void addFriend(UserModel friend){
        friendList.add(friend);
    }

    public boolean isFriend(UserModel user){
        if (friendList.contains(user)){
            return true;
        }
        return false;
    }

    public boolean validateUsername(String name){
        StringProperty user = new SimpleStringProperty(name);
        return (user.toString().equals(username.toString()));
    }
    public boolean validatePassword(String pass){
        StringProperty password = new SimpleStringProperty(pass);
        return password.toString().equals(password.toString());
    }

    public void addWallPost(String post){
        WallPost newPost = new WallPost(LocalDate.now(), post, this.getFullName());
        wallPosts.add(newPost);
        if (observer != null)
            notifyObserver();
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ObservableList<WallPost> getWallPosts() {
        return wallPosts;
    }

    public ObservableList<UserModel> getFriendList() {
        return friendList;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
