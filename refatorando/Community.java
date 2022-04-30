import java.util.ArrayList;
import java.util.Scanner;

public class Community {

    Scanner input = new Scanner(System.in);
    Search searcher = new Search();

    ArrayList<Friend>   members     = new ArrayList<Friend>();
    ArrayList<Post>     newsletters = new ArrayList<Post>();
    ArrayList<Message>  messages    = new ArrayList<Message>();

    private String user_admin;
    private String name_community;
    private String theme_community;
    private String about_community;
    String res;
    boolean active, flag, member;
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

    public void menuCommunity(Profile self, ArrayList<Profile> accounts, ArrayList<Community> community)
    {
        active = true;
        member = false;
        String noMember = "\n[1] - Entry\n[2] - Members\n[3] - Newsletters\n[4] - Exit\n=>";
        String Member = "\n[1] - Leave the community\n[2] - Members\n[3] - Newsletters\n[4] - Exit\n=>";
        String adminMember = "\n[1] - Members\n[2] - Messages\n[3] - Newsletters\n[4] - Delete community\n[5] - Exit\n=>";

        for(int i=0; i<this.members.size(); i++)
        {
            if(this.members.get(i).getUsername().intern() == self.getUser().intern())
            {
                this.member = true;
                break;
            }
        }

        while(active)
        {
            if(this.getUser_admin().intern() == self.getUser().intern())
            {
                System.out.printf(this.showCommunity()+adminMember);
                this.choise = input.nextInt();
                input.nextLine();
                active = this.adminMenu(self, accounts, community);
            }else
            {
                if(member)
                {
                    System.out.printf(this.showCommunity()+Member);
                    this.choise = input.nextInt();
                    input.nextLine();
                    active = this.memberMenu(self);
                }
                else
                {
                    System.out.printf(this.showCommunity()+noMember);
                    this.choise = input.nextInt();
                    input.nextLine();
                    active = this.noMemberMenu(self);
                }
            }
        }
    }

    private boolean noMemberMenu(Profile self)
    {
        switch(this.choise)
        {
            case 1:
                    members.add(new Friend(self.getUser()));
                    self.communities.add(this);
                    this.member = true;
                    System.out.println("\n"+self.getUser()+" welcome to the "+getCommunity_name()+" community");
                    input.nextLine();
                    break;
            ////////////////////////////////////////////////////////////////////////////////////////////
            case 2:
                this.showMembers();
                break;
            ///////////////////////////////////////////////////////////////////////////////////////////
            case 3:
            System.out.printf("\n[1] - See Posts\n[2] - Exit\n=>");
            choise2 = input.nextInt();
            input.nextLine();

            switch(choise2)
            {
                 case 1:
                    this.posts(self, false);
                    break;
                case 2:
                    break;
            }
            break;
            //////////////////////////////////////////////////////////////////////////////////////////////
            case 4:
                return false;
        }
        return true;
    }

    private boolean memberMenu(Profile self)
    {
        switch(this.choise)
        {
            case 1:
                for(int i=0; i<this.members.size(); i++)
                {
                    if(this.members.get(i).getUsername().intern() == self.getUser().intern())
                    {
                        this.members.remove(i);
                        this.member = false;
                        
                        for(int j=0; j<self.communities.size(); i++)
                        {
                            if(self.communities.get(j).getCommunity_name().intern() == this.getCommunity_name().intern())
                            {
                                self.communities.remove(j);
                                break;
                            }
                        }
                        System.out.printf("\nSuccessful exit from "+getCommunity_name()+" community\n");
                        input.nextLine();
                        break;
                    }
                }
                break;
            //////////////////////////////////////////////////////////////////////////////////////////////
            case 2:
                this.showMembers();
                break;
            ///////////////////////////////////////////////////////////////////////////////////////////////
            case 3:
                System.out.printf("\n[1] - Make a post\n[2] - See Posts\n[3] - Exit\n=>");
                choise2 = input.nextInt();
                input.nextLine();

                switch(choise2)
                {
                    case 1:
                        Post post = new Post(self);
                        this.newsletters.add(post);
                        System.out.printf("\nPost successfully published.\n");
                        input.nextLine();
                        break;
                    case 2:
                        this.posts(self, true);
                        break;
                    case 3:
                        break;
                }
                break;
            //////////////////////////////////////////////////////////////////////////////////////////////
            case 4:
                return false;
        }
        return true;
    }


    private boolean adminMenu(Profile self, ArrayList<Profile> accounts, ArrayList<Community> community)
    {
        switch(this.choise)
        {
            case 1:
                this.showMembers();
                break;
            ////////////////////////////////////////
            case 2:
                this.showMessages();
                break;
            //////////////////////////////////////
            case 3:
            System.out.printf("\n[1] - Make a post\n[2] - See Posts\n[3] - Exit\n=>");
            choise2 = input.nextInt();
            input.nextLine();

            switch(choise2)
            {
                case 1:
                    Post post = new Post(self);
                    System.out.printf("\nPost successfully published.\n");
                    input.nextLine();
                    this.newsletters.add(post);
                    break;
                case 2:
                    this.posts(self, member);
                    break;
            }
            break;
            ///////////////////////////////////////////
            case 4:
                self.admin_communities.remove(this);
                community.remove(this);
                System.out.printf("\nCommunity successfully deleted.\n");
                input.nextLine();
                return this.delet(accounts);
            case 5:
                return false;
        }
        return true;
    }

    public void posts(Profile self, boolean member)
    {
        if(this.newsletters.isEmpty())
        {
            System.out.printf("\nThis community does not have any post\n");
            input.nextLine();
            return;
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

    public boolean delet(ArrayList<Profile> accounts)
    {  
        this.newsletters.clear();
        this.messages.clear();

        for(int i=0; i<this.members.size(); i++)
        {
            accounts.get(searcher.search(accounts, this.members.get(i).getUsername())).communities.remove(this);
        }
        this.members.clear();
        return false;
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
