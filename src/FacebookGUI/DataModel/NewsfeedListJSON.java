package FacebookGUI.DataModel;

import java.util.ArrayList;

public class NewsfeedListJSON {

    public ArrayList<NewsfeedEntryJSON> masterPostList;

    public NewsfeedListJSON() {
        masterPostList = new ArrayList<NewsfeedEntryJSON>();
    }

    public ArrayList<NewsfeedEntryJSON> getMasterPostList() {
        return masterPostList;
    }

    public void setMasterPostList(ArrayList<NewsfeedEntryJSON> masterPostList) {
        this.masterPostList = masterPostList;
    }
}
