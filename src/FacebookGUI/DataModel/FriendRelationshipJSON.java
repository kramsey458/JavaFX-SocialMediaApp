package FacebookGUI.DataModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
public class FriendRelationshipJSON {

    public String userName;
    public ArrayList<String> userNameFriends;

    public FriendRelationshipJSON() {
        userNameFriends = new ArrayList<String>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getUserNameFriends() {
        return userNameFriends;
    }

    public void setUserNameFriends(ArrayList<String> userNameFriends) {
        this.userNameFriends = userNameFriends;
    }

    public void addFriend(UserModel friend){
        userNameFriends.add(friend.getUsername());
    }

}
