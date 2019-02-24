package anomecon.skelekey;

import android.graphics.drawable.Icon;

public class Content {

    private int id;
    private Icon contentMedia;
    private String contentName;
    private String contentPassword;
    private int creationTime;
    private int lastEditTime;

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setContentMedia(Icon media){
        this.contentMedia = media;
    }

    public void setContentName(String name){
        this.contentName = name;
    }

    public void setPassword(String password){
        this.contentPassword = password;
    }

    public void setCreationTime(int time){
        this.creationTime = time;
    }

    public void setLastEditTime(int editTime){
        this.lastEditTime = editTime;
    }

    //Getters
    public int getId() {
        return id;
    }
    public Icon getcontentMedia(){
        return contentMedia;
    }
    public String getContentName(){
        return contentName;
    }
    public String getContentPassword(){
        return contentPassword;
    }
    public int getCreationTime(){
        return creationTime;
    }
    public int getLastEditTime(){
        return lastEditTime;
    }
}
