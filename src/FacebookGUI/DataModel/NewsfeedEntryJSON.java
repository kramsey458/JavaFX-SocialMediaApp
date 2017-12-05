package FacebookGUI.DataModel;

import java.util.ArrayList;

public class NewsfeedEntryJSON {
    public String owner;
    public ArrayList<String> postList;

    public NewsfeedEntryJSON() {
        postList = new ArrayList<String>();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getPostList() {
        return postList;
    }

    public void setPostList(ArrayList<String> postList) {
        this.postList = postList;
    }
}
