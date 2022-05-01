public abstract class Message_abstract{

    protected String user;
    protected String to;
    protected String title;
    protected String content;
    
    public abstract String show();
    public abstract void   setTitle();
    public abstract void   setContent(String content);
    public abstract void   setTo();

    public void setFrom(String user)
    {
        this.user = user;
    }

    public String getUser()
    {
        return this.user;
    }

    public String getTo()
    {
        return this.to;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getContent()
    {
        return this.content;
    }

    
}
