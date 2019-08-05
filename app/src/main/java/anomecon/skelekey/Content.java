package anomecon.skelekey;

public class Content{
    //region Variable Declaration
    private int id;
    private String contentName;
    private String contentMedia;
    private String contentUserName;
    private String contentPassword;
    private String contentEmail;
    private int creationTime;
    private int lastEditTime;
    //endregion

    //region Getters
    public int getId() {

        return id;
    }
    public String getContentName(){

        return contentName;
    }
    public String getContentMedia(){

        return contentMedia;
    }
    public String getContentUserName(){

        return contentUserName;
    }
    public String getContentPassword(){

        return contentPassword;
    }
    public String getContentEmail(){

        return contentEmail;
    }
    public int getCreationTime(){

        return creationTime;
    }
    public int getLastEditTime(){

        return lastEditTime;
    }
    //endregion

    //region Setters
    public void setId(int id) {

        this.id = id;
    }
    public void setContentName(String name){

        this.contentName = name;
    }
    public void setContentMedia(String media){

        this.contentMedia = media;
    }
    public void setContentUserName(String userName){

        this.contentUserName = userName;
    }
    public void setContentPassword(String password){

        this.contentPassword = password;
    }
    public void setContentEmail(String email){

        this.contentEmail = email;
    }
    public void setCreationTime(int time){

        this.creationTime = time;
    }
    public void setLastEditTime(int editTime){

        this.lastEditTime = editTime;
    }
    //endregion
}
