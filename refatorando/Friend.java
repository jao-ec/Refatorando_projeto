public class Friend {
    private String userFriend;

    public Friend(String self)
    {
        setUserFriend(self);
    }
    
    public void setUserFriend(String name){
        this.userFriend = name;
    }

    public String getUsername()
    {
        return this.userFriend;
    }
}
