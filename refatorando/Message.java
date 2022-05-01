import java.util.ArrayList;
import java.util.Scanner;

public class Message extends Message_abstract{

    Scanner input = new Scanner(System.in);
    Search searcher = new Search();

    public String show()
    {
        return "Hello "+this.to+", the "+this.user+" send the follow message:\n=>"+this.content+"\n";
    }

    public Message(ArrayList<Profile> accounts, String who)
    {
        setFrom(who);
        setTo();
        Boolean aux = false;

        for(int i=0; i< accounts.size(); i++)
        {
            if(accounts.get(i).getUser().intern() == this.to.intern())
            {
                setContent("");
                aux = true;
                break;
            }
        }

        if(!aux)
        {
            System.out.printf("\nUnregisterede user.\nSorry try another user or try again latter.\n");
            input.nextLine();
        }
        else
        {
            accounts.get(searcher.search(accounts, this.getTo().intern())).messages.add(this);
            System.out.printf("\nMessage send successfully.\n");
            input.nextLine();
        }
    }

    public void setContent(String content)
    {
        System.out.printf("\nWrite bellow your message:\n=>");
        this.content = input.nextLine();
    }

    public void setTo()
    {
        System.out.printf("\nWho do you woukd like send the message:\n=>");
        this.to = input.nextLine();
    }

    public void setTitle() {
       this.title = "***";
    } 
}
