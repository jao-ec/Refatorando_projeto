import java.util.Scanner;

public class Invite extends Message_abstract
{
    Scanner input = new Scanner(System.in);

    public Invite(String from)
    {
        setFrom(from);
    }

    public String show() 
    {
        return "\nWho send: "+getUser()+"\n\t\t"+getTitle()+"\n"+getContent()+"\n";
    }

    public void setTitle() 
    {
        System.out.printf("what is you title invite?\n=>");
        this.title = input.nextLine();   
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public void setTo() 
    {
        System.out.printf("\nWhat user would you like send a invite?\n=>");
        this.to = input.nextLine();
    }
}
