import java.util.Scanner;

public class Post 
{
    Scanner input = new Scanner(System.in);

    private String title;
    private String content;
    private String user;
    public boolean just_members;
     
    public Post(Profile self)
    {
        this.user = self.getUser();
        setTitle();
        setcontent();
    }

    public String showPost()
    {
        return "Post published by "+this.user+"\n\t\t "+this.title+"\n"+this.content+"\n\n";
    }

    public void setTitle()
    {
        System.out.printf("\nWhat is your title post:\n=>");
        this.title = input.nextLine();
    }

    public void setcontent()
    {
        System.out.printf("\nWhat is your post:\n=>");
        this.content = input.nextLine();
    }
}
