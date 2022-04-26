public class Invite {

    private String username;
    private String invite_message;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setInvite(String message)
    {
        this.invite_message = message;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getInvite_message()
    {
        return this.invite_message;
    }

}
