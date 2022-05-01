import java.util.ArrayList;
import java.util.Scanner;

public class Profile extends User 
{
    Scanner input = new Scanner(System.in);

    ArrayList<Friend>    friends           = new ArrayList<Friend>();
    ArrayList<Message>   messages          = new ArrayList<Message>();
    ArrayList<Invite>    invites           = new ArrayList<Invite>();
    ArrayList<Community> communities       = new ArrayList<Community>();
    ArrayList<Community> admin_communities = new ArrayList<Community>();

    int choise;

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

    public void menuProfile(ArrayList<Profile> accounts)
    {
        int choise, choise2, aux;
        String res;
        boolean active = true;
        Search searcher = new Search();

        while(active)
        {
            System.out.printf("\n[1] - Edit\n[2] - Add friend\n[3] - Invites\n"+
                              "[4] - Send Message\n[5] - Read messages\n"+
                              "[6] - Friends\n[7] - My communities\n[8] - Exit\n=>");
            choise = input.nextInt();
            input.nextLine();

            switch(choise)
            {
                case 1:
                    System.out.printf("\nWhat attribute do you want to edit?\n"+
                                        "[1] - All attributes\n[2] - Name\n"+
                                        "[3] - Gender\n[4] - Age\n[5] - Schooling\n"+
                                        "[6] - Country\n[7] - About\n[8] - Exit\n=>");
                    choise2 = input.nextInt();
                    input.nextLine();

                    switch(choise2)
                    {
                        case 1:
                            this.edit();
                            break;
                        case 2:
                            this.setName();
                            break;
                        case 3:
                            this.setGender();
                            break;
                        case 4:
                            this.setAge();
                            break;
                        case 5:
                            this.setSchooling();
                            break;
                        case 6:
                            this.setCountry();
                            break;
                        case 7:
                            this.setAbout();
                            break;
                        case 8:
                            break;
                    }
                    break;
                ///////////////////////////////////////////////////////////////////////////
                case 2:
                    Invite convite = new Invite(this.getUser());
                    convite.setTo();
                    aux = searcher.search(accounts, convite.getTo());
                    
                    if(aux >= 0)
                    {
                        convite.setContent(this.apresentation(accounts.get(aux)));
                        accounts.get(aux).invites.add(convite);
                        System.out.printf("\nInvite send succefully.\n");
                        input.nextLine();
                    }
                    else
                    {
                        System.out.printf("\nThis user is not registered yet.\n");
                        input.nextLine();
                    }
                    break;
                ///////////////////////////////////////////////////////////////////
                case 3:
                    if(this.invites.size() == 0)
                    {
                        System.out.printf("\nYou do not have any invite by for now.\n");
                        input.nextLine();
                    }
                    else
                    {
                        for(int i=0; i<this.invites.size(); i++)
                        {
                            System.out.println("\n"+this.invites.get(i).getContent());
                            String user_friend = this.invites.get(i).getUser();

                            System.out.printf("Do you accept? [Y/N]");
                            res = input.nextLine();

                            if(res.toUpperCase().intern() == "Y")
                            {
                                Friend amigo1 = new Friend(this.getUser());
                                Friend amigo2 = new Friend(accounts.get(searcher.search(accounts, user_friend)).getUser());

                                this.friends.add(amigo2);
                                accounts.get(searcher.search(accounts, user_friend)).friends.add(amigo1);
                            }
                        }
                        this.invites.clear();
                    }
                    break;
                //////////////////////////////////////////////////////////////////////////////////
                case 4:
                    new Message(accounts, this.getUser(), false, "");
                    break;
                //////////////////////////////////////////////////////////////////////////////////
                case 5:
                    this.showMessages(accounts);
                    break;
                //////////////////////////////////////////////////////////////////////////////////
                case 6:
                    this.showFriends();
                    break;
                //////////////////////////////////////////////////////////////////////////////////
                case 7:
                    this.showCommunities();
                    break;       
                /////////////////////////////////////////////////////////////////////////////////
                case 8:
                    active = false;
                    break;           
            }
        }    
    }

    public void showFriends()
    {
        if(this.friends.isEmpty())
        {
            System.out.printf("\nDoes not have any friend. [F for respect]\n");
            input.nextLine();
        }
        else
        {
            for(int i=0; i<this.friends.size(); i++)
            {
                System.out.println("-->"+this.friends.get(i).getUsername());
            }
            input.nextLine();
        }
    }

    public void showMessages(ArrayList<Profile> accounts)
    {
        if(this.messages.isEmpty())
        {
            System.out.printf("\nDoes not have any message.\n");
            input.nextLine();
        }
        else
        {
            for(int i=0; i<this.messages.size(); i++)
            {
                System.out.printf(this.messages.get(i).show());
                System.out.printf("\n[1] - Delet this message\n[2] - Reply\n[3] - Continue\n=>");
                choise = input.nextInt();
                input.nextLine();

                switch(choise)
                {
                    case 1:
                        this.messages.remove(i);
                        i--;
                        System.out.printf("\nMessage deleted successfully.\n");
                        input.nextLine();
                        break;

                    case 2:
                        new Message(accounts, this.getUser(), true, this.messages.get(i).getUser());
                        System.out.print("\nSuccessfully answered.\n");
                        break;

                    default:
                        break;

                }
            }
            System.out.printf("\nEvery message was read.\n");
        }
    }

    public void showCommunities()
    {
        if(this.communities.isEmpty() && this.admin_communities.isEmpty())
        {
            System.out.printf("\nDoes not have any community on your account.\n");
            input.nextLine();
        }
        else
        {
            if(!this.communities.isEmpty())
            {
                for(int i=0; i<this.communities.size(); i++)
                {
                    System.out.println(this.communities.get(i).showCommunity());
                    input.nextLine();
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
