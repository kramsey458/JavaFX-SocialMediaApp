package FacebookGUI.DataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

public class FriendListJSON {

    public ArrayList<FriendRelationshipJSON> masterFriendList;

    public FriendListJSON() {
        masterFriendList = new ArrayList<FriendRelationshipJSON>();
    }

    public ArrayList<FriendRelationshipJSON> getMasterFriendList() {
        return masterFriendList;
    }

    public void setMasterFriendList(ArrayList<FriendRelationshipJSON> masterFriendList) {
        this.masterFriendList = masterFriendList;
    }
}
