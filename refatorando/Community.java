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
    String res;
    boolean active, flag;
    int choise, choise2;

    public Community(String self)
    {
        setUser_admin(self);
        setName_community();
        setTheme_community();
        setAbout_community();
    }

    public String showCommunity()
    {
        return "\nUser_admin: "   +this.user_admin     +"\n"+
               "Community name: " +this.name_community +"\n"+
               "Community theme: "+this.theme_community+"\n"+
               "Community about: "+this.about_community+"\n"+
               "Members amount: " +members.size()      +"\n"+
               "Post amount: "    +newsletters.size()  +"\n";
    }

    public void menuCommunity(Profile self)
    {
        active = true;
        while(active)
        {
            
            System.out.printf(this.showCommunity()+"\n[1] - Entry\n[2] - Leave the community\n[3] - Members\n"+
                                                   "[4] - Newsletters\n[5] - Exit\n=>");
            choise = input.nextInt();
            input.nextLine();

            switch(choise)
            {
                case 1:
                    this.members.add(new Friend(self.getUser()));
                    System.out.println("\nWelcome to the "+getCommunity_name()+" community");
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////
                case 2:
                    flag = false;

                    for(int i=0; i<this.members.size(); i++)
                    {
                        if(this.members.get(i).getUsername().intern() == self.getUser().intern())
                        {
                            this.members.remove(i);
                            
                            for(int j=0; j<self.communities.size(); i++)
                            {
                                if(self.communities.get(j).getCommunity_name().intern() == this.getCommunity_name().intern())
                                {
                                    self.communities.remove(j);
                                    break;
                                }
                            }

                            System.out.printf("\nSuccessful exit from "+getCommunity_name()+" community\n");
                            flag = true;
                        }
                    }

                    if(!flag)
                    {
                        System.out.println("\nYou must be in the community to be able to leave.\n");
                    }
                    break;
                //////////////////////////////////////////////////////////////////////////////////////////////
                case 3:
                    this.showMembers();
                    break;
                ///////////////////////////////////////////////////////////////////////////////////////////////
                case 4:
                    System.out.printf("\n[1] - Make a post\n[2] - See Posts\n[3] - Exit\n=>");
                    choise2 = input.nextInt();
                    input.nextLine();

                    switch(choise2)
                    {
                        case 1:
                            Post post = new Post(self);
                            this.newsletters.add(post);
                            break;
                        case 2:
                            this.posts(self);
                            break;
                        case 3:
                            break;
                    }
                    break;
                //////////////////////////////////////////////////////////////////////////////////////////////
                case 5:
                    active = false;
                    break;
            }
        }

    }

    public void posts(Profile self)
    {
        boolean member = false;

        if(this.newsletters.isEmpty())
        {
            System.out.println("\nThis community does not have any post");
            return;
        }

        for(int i=0; i<members.size(); i++)
        {
            if(members.get(i).getUsername().intern() == self.getUser().intern())
            {
                member = true;
                break;
            }
        }

        for(int i=0; i<this.newsletters.size(); i++)
        {
            if(member)
            {
                System.out.println("\n"+this.newsletters.get(i).showPost());
                input.nextLine();
            }
            else
            {
                if(!this.newsletters.get(i).just_members)
                {
                    System.out.println("\n"+this.newsletters.get(i).showPost());
                    input.nextLine();
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

    public void showMessages()
    {
        for(int i=0; i<this.messages.size(); i++)
        {
            System.out.println(this.messages.get(i).show_message());
            System.out.printf("\nWould you like delete this message? [Y/N]\n=>");
            res = input.nextLine();

            if(res.toUpperCase().intern() == "Y")
            {
                this.messages.remove(messages.get(i));
            }
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
