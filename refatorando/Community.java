import java.util.ArrayList;
import java.util.Scanner;

public class Community {

    Scanner input = new Scanner(System.in);

    ArrayList<Friend>   members     = new ArrayList<Friend>();
    ArrayList<Post>     newsletters = new ArrayList<Post>();
    ArrayList<Message>  messages    = new ArrayList<Message>();

    private String user_admin;
    private String name_community;
    private String theme_community;
    private String about_community;

    public Community(String self)
    {
        setUser_admin(self);
        setName_community();
        setTheme_community();
        setAbout_community();
    }

    public String showCommunity()
    {
        return "User_admin: "     +this.user_admin+"\n"+
               "Community name: " +this.name_community+"\n"+
               "Post amount: "    +newsletters.size()+"\n"+
               "Community theme: "+this.theme_community+"\n"+
               "Community about: "+this.about_community+"\n";
    }

    public void posts(Profile self)
    {
        boolean flag = true;

        if(!newsletters.isEmpty())
        {
            System.out.println("\nThis community does not have any post.");
            input.nextLine();
        }
        else
        {
            for(int i=0; i<this.members.size(); i++)
            {
                if(this.members.get(i).getUsername().intern() == self.getUser().intern())
                {
                    for(int j=0; j<newsletters.size(); j++)
                    {
                        newsletters.get(j).showPost();
                        flag = true;
                        input.nextLine();
                    }
                    break;
                }
            }

            if(!flag)
            {
                for(int j=0; j<newsletters.size(); j++)
                {
                    if(newsletters.get(j).just_members == false)
                    {
                        newsletters.get(j).showPost();
                        input.nextLine();
                    }
                }
            }
        }
    }

    public void showMembers()
    {
        for(int i=0; i<this.members.size(); i++)
        {
            System.out.println(this.members.get(i).getUsername());
        }
    }

    private void setUser_admin(String self_user)
    {
        this.user_admin = self_user;
    }

    private void setName_community()
    {
        System.out.printf("\nwhat is your community name:\n=>");
        this.name_community = input.nextLine();
    }

    private void setTheme_community()
    {
        System.out.printf("\nwhat is your community theme:\n=>");
        this.theme_community = input.nextLine();
    }

    private void setAbout_community()
    {
        System.out.printf("\nwhat is your community are:\n=>");
        this.about_community = input.nextLine();
    }

    public String getUser_admin()
    {
        return this.user_admin;
    }

    public String getCommunity_name()
    {
        return this.name_community;
    }
}
