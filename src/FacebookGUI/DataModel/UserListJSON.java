package FacebookGUI.DataModel;

import java.util.ArrayList;

public class UserListJSON {
    public ArrayList<UserModelJSON> masterUserList;

    public UserListJSON() {
        masterUserList = new ArrayList<UserModelJSON>();
    }

    public ArrayList<UserModelJSON> getMasterUserList() {
        return masterUserList;
    }

    public void setMasterUserList(ArrayList<UserModelJSON> masterUserList) {
        this.masterUserList = masterUserList;
    }
}
