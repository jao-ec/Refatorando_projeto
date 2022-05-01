import java.util.Scanner;

public class Post extends Message_abstract
{
    Scanner input = new Scanner(System.in);

    public boolean just_members;
     
    public Post(Profile self)
    {
        this.user = self.getUser();
        setTitle();
        setContent("");
    }

    public String show()
    {
        return "Post published by "+this.user+"\n\t\t "+this.title+"\n"+this.content+"\n\n";
    }

    public void setTitle()
    {
        System.out.printf("\nWhat is your title post:\n=>");
        this.title = input.nextLine();
    }

    public void setContent(String content)
    {
        System.out.printf("\nWhat is your post:\n=>");
        this.content = input.nextLine();
    }

    public void setTo() 
    {
        this.to = "Type community post";
    }
}
