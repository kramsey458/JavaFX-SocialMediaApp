package FacebookGUI.JSONParsing;
import FacebookGUI.DataModel.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONUserParser {

    public UserData data;
    public UserListJSON userList;

    public JSONUserParser(UserData data) {
        this.data = data;
        userList = new UserListJSON();
    }

    public UserData addUsersToUserData(){
        UserListJSON userList = readUsers();
        for (UserModelJSON user: userList.getMasterUserList()){
            data.addUser(new UserModel(user.getFirstName(), user.getLastName(), user.getUserName(),user.getPassword()));
        }
        return data;
    }

    public void initializeUsers(){
        for (UserModel user: data.getUserDataList()){
            UserModelJSON userEntry = new UserModelJSON();
            userEntry.setFirstName(user.getFirstName());
            userEntry.setLastName(user.getLastName());
            userEntry.setUserName(user.getUsername());
            userEntry.setPassword(user.getPassword());
            //System.out.println("!! USER ADDED" + user.getUsername());
            userList.getMasterUserList().add(userEntry);
        }
        writeUsers();
    }

    private void writeUsers(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("userList.json"), userList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserListJSON readUsers(){
        ObjectMapper mapper = new ObjectMapper();
        UserListJSON userList = new UserListJSON();
        try {
            userList = mapper.readValue(new File("userList.json"), UserListJSON.class);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        //System.out.println(userList.getMasterUserList().get(0).getFirstName());
        return userList;
    }

}
