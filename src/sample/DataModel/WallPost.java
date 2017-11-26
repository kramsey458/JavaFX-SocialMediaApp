package sample.DataModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class WallPost implements Comparable<WallPost>{
    String ownerName;
    private ObjectProperty<LocalDate> postDate;
    private StringProperty postContent;

    public WallPost(LocalDate date, String content, String ownerName){
        this.ownerName = ownerName;
        postDate = new SimpleObjectProperty<>(date);
        postContent = new SimpleStringProperty(content);
    }

    public LocalDate getPostDate() {
        return postDate.get();
    }

    public StringProperty ownerNameProperty() {
        return new SimpleStringProperty(ownerName);
    }

    public ObjectProperty<LocalDate> postDateProperty() {
        return postDate;
    }


    public String getPostContent() {
        return postContent.get();
    }

    public StringProperty postContentProperty() {
        return postContent;
    }

    public int compareTo(WallPost other){
        return (postDate.get().compareTo(other.postDate.get()))*-1;
    }
}
