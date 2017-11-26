package FacebookGUI.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserData {
    private ObservableList<UserModel> userData = FXCollections.observableArrayList();
    private UserModel currentUser;

    public UserModel searchUser(String username){
        for (UserModel userModel : userData){
            if (userModel.validateUsername(username)){
                return userModel;
            }
        }
        return null;
    }

    public void addUser(UserModel user){
        userData.add(user);
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public ObservableList<UserModel> getUserData() {
        return userData;
    }

}
