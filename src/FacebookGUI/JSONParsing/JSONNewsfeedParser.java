package FacebookGUI.JSONParsing;
import FacebookGUI.DataModel.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONNewsfeedParser {

    public UserData data;
    public NewsfeedListJSON postList;

    public JSONNewsfeedParser(UserData data) {
        this.data = data;
        postList = new NewsfeedListJSON();
    }

    public UserData addNewsfeedToUserData(){
        NewsfeedListJSON postList = readNewsfeed();
        for (NewsfeedEntryJSON post : postList.getMasterPostList()){
            UserModel temp = data.searchUser(post.getOwner());
            for (String postContent : post.getPostList()){
                temp.addWallPost(postContent);
            }
        }
        return data;
    }

    public void initializeNewsfeed(){
        for (UserModel user: data.getUserDataList()){
            NewsfeedEntryJSON userPosts = new NewsfeedEntryJSON();
            userPosts.setOwner(user.getUsername());
            for (WallPost post : user.getWallPosts()){
                userPosts.getPostList().add(post.getPostContent());
            }
            postList.getMasterPostList().add(userPosts);
        }
        writeNewsfeed();
    }

    private void writeNewsfeed(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("newsfeedList.json"), postList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public NewsfeedListJSON readNewsfeed(){
        ObjectMapper mapper = new ObjectMapper();
        NewsfeedListJSON newsfeedList = new NewsfeedListJSON();
        try {
            newsfeedList = mapper.readValue(new File("newsfeedList.json"), NewsfeedListJSON.class);
        } catch (Exception e) {
           // e.printStackTrace();
        }
//        System.out.println(newsfeedList.getMasterPostList().get(0).getPostList().get(0));
        return newsfeedList;
    }



}