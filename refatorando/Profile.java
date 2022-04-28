import java.util.ArrayList;

public class Profile extends User 
{
    ArrayList<Friend>    friends           = new ArrayList<Friend>();
    ArrayList<Message>   messages          = new ArrayList<Message>();
    ArrayList<Invite>    invites           = new ArrayList<Invite>();
    ArrayList<Community> communities       = new ArrayList<Community>();
    ArrayList<Community> admin_communities = new ArrayList<Community>();

    public Profile()
    {
        super.setUser();
        super.setName();
    }

    public String getPassword()
    {
        return this.password;
    }

    public String showPerfil()
    {
        return "\nUser: "+getUser()+"\nName: "+this.getName()+
               "\nGender: "+this.getGender()+"\nAge: "+this.getAge()+
               "\nSchooling: "+this.getSchooling()+"\nCountry: "+this.getCountry()+
               "\nAbout: "+this.getAbout()+"\nFriends: "+friends.size()+
               "\nMessages: "+messages.size()+"\nInvites: "+invites.size()+
               "\nCommunities: "+(communities.size()+admin_communities.size())+"\n";
    }

    public void showFriends()
    {
        if(this.friends.isEmpty())
        {
            System.out.printf("\nDoes not have any friend. [F for respect]\n");
        }
        else
        {
            for(int i=0; i<this.friends.size(); i++)
            {
                System.out.println(this.friends.get(i).getUsername());
            }
        }
    }

    public void showMessages()
    {
        if(this.messages.isEmpty())
        {
            System.out.printf("\nDoes not have any message.\n");
        }
        else
        {
            for(int i=0; i<this.messages.size(); i++)
            {
                System.out.printf(this.messages.get(i).show_message());
            }
        }
    }

    public void showCommunities()
    {
        if(this.communities.isEmpty() && this.admin_communities.isEmpty())
        {
            System.out.printf("\nDoes not have any community on your account.\n");
        }
        else
        {
            if(!this.communities.isEmpty())
            {
                for(int i=0; i<this.communities.size(); i++)
                {
                    System.out.println(this.communities.get(i).showCommunity());
                }
            }

            if(!this.admin_communities.isEmpty())
            {
                for(int i=0; i<this.admin_communities.size(); i++)
                {
                    System.out.println(this.admin_communities.get(i).showCommunity());
                }
            }
        }
    }
}
