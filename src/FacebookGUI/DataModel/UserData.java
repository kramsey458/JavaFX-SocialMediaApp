package FacebookGUI.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class UserData {
    private ObservableList<UserModel> userDataList = FXCollections.observableArrayList();
    private static UserData single_instance = null;
    private UserData(){

    }

    public static UserData getInstance(){
        if (single_instance == null){
            single_instance = new UserData();
        }
        return single_instance;
    }
    public UserModel searchUser(String username){
        for (UserModel userModel : userDataList){
            if (userModel.validateUsername(username)){
                return userModel;
            }
        }
        return null;
    }

    public void addUser(UserModel user){
        userDataList.add(user);
    }

    public ObservableList<UserModel> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(ObservableList<UserModel> userDataList) {
        this.userDataList = userDataList;
    }

}
