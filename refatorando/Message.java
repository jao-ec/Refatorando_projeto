import java.util.ArrayList;
import java.util.Scanner;

public class Message{

    Scanner input = new Scanner(System.in);

    protected String message;
    private String from_user;
    private String to_user;

    public String show_message()
    {
        return "Hello "+this.to_user+", the "+this.from_user+" send the follow message:\n=>"+this.message+"\n";
    }

    public Message(ArrayList<Profile> accounts, String who)
    {
        setFrom_user(who);
        setTo_user();
        Boolean aux = false;

        for(int i=0; i< accounts.size(); i++)
        {
            if(accounts.get(i).getUser().intern() == this.to_user.intern())
            {
                setMessage();
                aux = true;
                break;
            }
        }

        if(!aux)
        {
            System.out.println("\nUnregisterede user.\nSorry try another user or try again latter.");
            return;
        }
        else
        {
            System.out.println("\nMessage send successfully");
        }
    }

    public void setMessage()
    {
        System.out.printf("\nWrite bellow your message:\n=>");
        this.message = input.nextLine();
    }

    public void setFrom_user(String self_user)
    {
        this.from_user = self_user.intern();
    }

    public void setTo_user()
    {
        System.out.printf("\nWho do you woukd like send the message:\n=>");
        this.to_user = input.nextLine();
    }

    public String getTo()
    {
        return this.to_user;
    }
    
}
