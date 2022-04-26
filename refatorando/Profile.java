import java.util.ArrayList;

public class Profile extends User 
{
    ArrayList<Friend>    friends     = new ArrayList<Friend>();
    ArrayList<Message>   messages    = new ArrayList<Message>();
    ArrayList<Invite>    invites     = new ArrayList<Invite>();
    ArrayList<Community> communities = new ArrayList<Community>();

    public Profile()
    {
        super.setUser();
    }

    public String getPassword()
    {
        return this.password;
    }

    public String showPerfil()
    {
        return "\nUser: "+getUser()+"\nFriends: "+friends.size()+
               "\nMessages: "+messages.size()+"\nInvites: "+invites.size()+
               "\nCommunities: "+communities.size()+"\n";
    }

    public void showFriends()
    {
        if(this.friends.isEmpty())
        {
            System.out.println("Does not have any friend. [F for respect]\n");
        }
        else
        {
            for(int i=0; i<this.messages.size(); i++)
            {
                System.out.println(this.friends.get(i).getUsername());
            }
        }
    }

    public void showMessages()
    {
        if(this.messages.isEmpty())
        {
            System.out.println("Does not have any message.\n");
        }
        else
        {
            for(int i=0; i<this.messages.size(); i++)
            {
                System.out.println(this.messages.get(i).show_message());
            }
        }
    }

    public void showCommunities()
    {
        if(this.communities.isEmpty())
        {
            System.out.println("Does not have any community on your account.\n");
        }
        else
        {
            for(int i=0; i<this.communities.size(); i++)
            {
                System.out.println(this.communities.get(i).showCommunity());
            }
        }
    }
}