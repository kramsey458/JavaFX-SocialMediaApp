package FacebookGUI.JSONParsing;
import FacebookGUI.DataModel.FriendListJSON;
import FacebookGUI.DataModel.FriendRelationshipJSON;
import FacebookGUI.DataModel.UserData;
import FacebookGUI.DataModel.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONFriendsParser {

    public UserData data;
    public FriendListJSON friendList;

    public JSONFriendsParser(UserData data) {
        this.data = data;
        friendList = new FriendListJSON();
    }

    public UserData addFriendsToUserData(){
        FriendListJSON friendList = readFriends();
        for (FriendRelationshipJSON link : friendList.getMasterFriendList()){
            UserModel temp = data.searchUser(link.getUserName());
            for (String friend : link.getUserNameFriends()){
                UserModel tempFriend = data.searchUser(friend);
                temp.addFriend(tempFriend);
            }
        }
        return data;
    }


    public void initializeFriends(){
        for (UserModel user: data.getUserDataList()){
            FriendRelationshipJSON rel = new FriendRelationshipJSON();
            rel.setUserName(user.getUsername());
            for (UserModel friend: user.getFriendList()){
                if (friend != null) {
                    rel.addFriend(friend);
                }
            }
            friendList.getMasterFriendList().add(rel);
        }
        writeFriends();
    }

    private void writeFriends(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("friendList.json"), friendList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public FriendListJSON readFriends(){
        ObjectMapper mapper = new ObjectMapper();
        FriendListJSON friendList = new FriendListJSON();
        try {
            friendList = mapper.readValue(new File("friendList.json"), FriendListJSON.class);
        } catch (Exception e) {
           // e.printStackTrace();
        }
//        System.out.println(friendList.getMasterFriendList().get(0).getUserName());
//        System.out.println(friendList.getMasterFriendList().get(0).getUserNameFriends());
        return friendList;
    }

    public FriendListJSON getFriendList() {
        return friendList;
    }

    public void setFriendList(FriendListJSON friendList) {
        this.friendList = friendList;
    }
}
