import java.util.Scanner;

public class Post 
{
    Scanner input = new Scanner(System.in);

    public String title;
    public String content;
     
    public Post()
    {
        setTitle();
        setcontent();
    }

    public String showPost()
    {
        return "\t\t "+this.title+"\n"+this.content+"\n";
    }

    public void setTitle()
    {
        System.out.println("What is your title post:\n=>");
        this.title = input.nextLine();
    }

    public void setcontent()
    {
        System.out.println("What is your post:\n=>");
        this.content = input.nextLine();
    }
}
