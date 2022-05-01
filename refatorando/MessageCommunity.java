import java.util.Scanner;

public class MessageCommunity extends Message_abstract
{
    Scanner input = new Scanner(System.in);


    public MessageCommunity(String community, String username)
    {
        this.to = community;
        setFrom(username);
        setContent("");
    }
    public String show() 
    {
        return "\nthe following message cannot be answered.\nHello community "+this.to+", the "+this.user+" send the follow message:\n=>"+this.content;
    }

    public void setTitle() 
    {
        System.out.printf("\nwhat is you message' title:\n=>");
        this.content = input.nextLine();        
    }

    public void setContent(String content) 
    {
        System.out.printf("\nWrite bellow your message:\n=>");
        this.content = input.nextLine();
    }

    public void setTo() 
    {
        this.to = "";
    }
    
}
