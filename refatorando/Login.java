import java.util.ArrayList;
import java.util.Scanner;

public class Login 
{
    public Scanner input = new Scanner(System.in);
    public Search searcher = new Search();

    public int checkLogin(ArrayList<Profile> accounts)
    {
        String user, password;
        boolean check = false;
        int index = 0;

        if(accounts.isEmpty())
        {
            System.out.printf("\nNo registers.\n");
            return -1;
        }

        System.out.printf("\nUsername:\n=>");
        user = input.nextLine();
        System.out.printf("Password:\n=>");
        password = input.nextLine();

        for(int i=0; i<accounts.size(); i++)
        {
            if(user.intern()     == (accounts.get(i).getUser()).intern() && 
               password.intern() == (accounts.get(i).getPassword()).intern()) 
               {
                    index = i;
                    check = true;
                    break;
               }
        }

        if(!check)
        {
            System.out.println("\nUser or Password are incorrect.");
            input.nextLine();
            return -1;
        }
        else return index;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void menu(ArrayList<Profile> accounts, ArrayList<Backup> backups, ArrayList<Community> community, int index)
    {
        int choise, choise2, choise3, aux;
        String user, res, name;
        boolean active = true, flag = true;
        Profile self = accounts.get(index);

        while(active)
        {
            System.out.println(self.showPerfil());
            System.out.printf("\n[1] - Profile\n[2] - My communities\n[3] - Recover data\n[4] - Delete my account\n[5] - Exit\n=>");
            choise = input.nextInt();
            input.nextLine();
            
            switch(choise)
            {
                case 1:
                    System.out.printf("\n[1] - Edit\n[2] - Add friend\n[3] - Invites\n"+
                                        "[4] - Send Message\n[5] - Read messages\n"+
                                        "[6] - Friends\n[7] - Communities\n[8] - Exit\n=>");
                    choise2 = input.nextInt();
                    input.nextLine();

                    switch(choise2)
                    {
                        case 1:
                            System.out.printf("\nWhat attribute do you want to edit?\n"+
                                              "[1] - All attributes\n[2] - Name\n"+
                                              "[3] - Gender\n[4] - Age\n[5] - Schooling\n"+
                                              "[6] - Country\n[7] - About\n[8] - Exit\n=>");
                            choise3 = input.nextInt();
                            input.nextLine();

                            switch(choise3)
                            {
                                case 1:
                                    self.edit();
                                    break;
                                case 2:
                                    self.setName();
                                    break;
                                case 3:
                                    self.setGender();
                                    break;
                                case 4:
                                    self.setAge();
                                    break;
                                case 5:
                                    self.setSchooling();
                                    break;
                                case 6:
                                    self.setCountry();
                                    break;
                                case 7:
                                    self.setAbout();
                                    break;
                                case 8:
                                    break;
                                default:
                                    break;
                            }
                            break;
                        ///////////////////////////////////////////////////////////////////////////
                        case 2:
                            Invite convite = new Invite();
                            convite.setUsername(self.getUser());
        
                            System.out.printf("\nWhat user would you like send a invite?\n=>");
                            user = input.nextLine();
        
                            aux = searcher.search(accounts, user.intern());

                            if(aux >= 0)
                            {
                                convite.setInvite(self.apresentation(accounts.get(aux)));
                                accounts.get(aux).invites.add(convite);
                                System.out.printf("\nInvite send succefully.\n");
                            }
                            else
                            {
                                System.out.printf("\nThis user is not registered yet.\n");
                            }
                            break;
                        /////////////////////////////////////////////////////////////////////////////
                        case 3:
                            if(self.invites.size() == 0)
                            {
                                System.out.printf("\nYou do not have any invite by for now.\n");
                            }
                            else
                            {
                                for(int i=0; i<self.invites.size(); i++)
                                {
                                    System.out.println("\n"+self.invites.get(i).getInvite_message());
                                    String user_friend = self.invites.get(i).getUsername();

                                    System.out.printf("Do you accept? [Y/N]");
                                    res = input.nextLine();

                                    if(res.toUpperCase().intern() == "Y")
                                    {
                                        Friend amigo1 = new Friend(self.getUser());
                                        Friend amigo2 = new Friend(accounts.get(searcher.search(accounts, user_friend)).getUser());

                                        self.friends.add(amigo2);
                                        accounts.get(searcher.search(accounts, user_friend)).friends.add(amigo1);
                                    }
                                }
                                self.invites.clear();
                            }
                            break;
                        //////////////////////////////////////////////////////////////////////////////////
                        case 4:
                            Message mensagem = new Message(accounts, self.getUser().intern());
                            accounts.get(searcher.search(accounts, mensagem.getTo().intern())).messages.add(mensagem);
                            break;
                        //////////////////////////////////////////////////////////////////////////////////
                        case 5:
                            self.showMessages();
                            break;
                        //////////////////////////////////////////////////////////////////////////////////
                        case 6:
                            self.showFriends();
                            break;
                        //////////////////////////////////////////////////////////////////////////////////
                        case 7:
                            self.showCommunities();
                            break;       
                        /////////////////////////////////////////////////////////////////////////////////
                        case 8:
                            break;
                        ////////////////////////////////////////////////////////////////////////////////
                        default:
                            break;                
                    }
                    break;
                
                case 2:
                    System.out.printf("\n[1] - Creat community\n[2] - Add Community\n[3] - Send message\n"+
                                      "[4] - Make a post\n[5] - See posts\n[6] - See messages\n[7] - See members\n"+
                                      "[8] - See communities\n[9] - Exit\n=>");

                    choise2 = input.nextInt();
                    input.nextLine();

                    switch(choise2)
                    {
                        case 1:
                            Community comunidade = new Community(self.getUser());
                            comunidade.members.add(new Friend(self.getUser()));
                            community.add(comunidade);
                            self.admin_communities.add(comunidade);
                            break;
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        case 2:
                            System.out.printf("What is community' name:\n=>");
                            name = input.nextLine();

                            flag = false;

                            for(int i=0; i<community.size(); i++)
                            {
                                if(community.get(i).getCommunity_name().intern() == name.intern())
                                {
                                    community.get(i).members.add(new Friend(self.getUser()));
                                    self.communities.add(community.get(i));
                                    flag = true;
                                    break;
                                }
                            }

                            if(!flag)
                            {
                                System.out.println("This community does not exist\n");
                            }
                            break;
                        ///////////////////////////////////////////////////////////////////////////////////////////////
                        case 3:
                            System.out.printf("What is community' name:\n=>");
                                name = input.nextLine();

                                flag = false;

                                for(int i=0; i<community.size(); i++)
                                {
                                    if(community.get(i).getCommunity_name().intern() == name.intern())
                                    {
                                        community.get(i).messages.add(new Message(accounts, self.getUser()));
                                        flag = true;
                                        break;
                                    }
                                }

                                if(!flag)
                                {
                                    System.out.println("This community does not exist\n");
                                }
                                break;
                        ///////////////////////////////////////////////////////////////////////////////////////////////
                        case 4:
                            System.out.printf("You must to be on community to make a post!\nWhat is community' name:\n=>");
                            name = input.nextLine();

                            flag = false;

                            for(int i=0; i<community.size(); i++)
                            {
                                if(community.get(i).getCommunity_name().intern() == name.intern())
                                {
                                    Post poste = new Post();
                                    if(community.get(i).getUser_admin() == self.getUser())
                                    {                                        
                                        System.out.printf("\nWould you like only members to see this post?? [Y/N]\n=>");
                                        res = input.nextLine();

                                        if(res.toUpperCase() == "Y"){
                                            poste.just_members = true;
                                        }
                                        else
                                        {
                                            poste.just_members = false;
                                        }                                        
                                    }
                                    else
                                    {
                                        poste.just_members = false;
                                    }
                                    System.out.printf("\nPost made successfully.\n");
                                    community.get(i).newsletters.add(poste);
                                    flag = true;
                                    break;
                                }
                            }

                            if(!flag)
                            {
                                System.out.printf("\nThis community does not exist\n");
                            }
                            break;
                        ///////////////////////////////////////////////////////////////////////////////////////////////
                        case 5:
                        System.out.printf("\nWhat is community' name:\n=>");
                        name = input.nextLine();

                        flag = false;

                        for(int i=0; i<community.size(); i++)
                        {
                            if(community.get(i).getCommunity_name().intern() == name.intern())
                            {
                                community.get(i).posts(self);
                                flag = true;
                                break;
                            }
                        }

                        if(!flag)
                        {
                            System.out.println("\nThis community does not exist\n");
                        }
                        break;
                    ///////////////////////////////////////////////////////////////////////////////////////////////
                    case 6:
                        System.out.printf("\nYou must be the community admin!\nWhat is community' name\n=>");
                        name = input.nextLine();

                        flag = false;

                        for(int i=0; i<community.size(); i++)
                        {
                            if(community.get(i).getCommunity_name().intern() == name.intern() && community.get(i).getUser_admin().intern() == self.getUser().intern())
                            {
                                for(int j=0; j<community.get(i).messages.size(); j++)
                                {
                                    System.out.printf(community.get(i).messages.get(j).show_message());
                                    flag = true;
                                }
                            }
                            if(flag) break;
                        }

                        if(!flag)
                        {
                            System.out.println("\nThis community does not exist\n");
                        }
                        break;
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    case 7:
                        System.out.printf("\nWhat is community' name\n=>");
                        name = input.nextLine();

                        flag = false;

                        for(int i=0; i<community.size(); i++)
                        {
                            if(community.get(i).getCommunity_name().intern() == name.intern())
                            {
                                System.out.printf("\n");
                                community.get(i).showMembers();
                                flag = true;
                                break;
                            }
                        }

                        if(!flag)
                        {
                            System.out.printf("\nThis community does not exist\n");
                        }
                        break;
                    ///////////////////////////////////////////////////////////////////////////////////////////////
                    case 8:
                        self.showCommunities();
                        break;
                    ///////////////////////////////////////////////////////////////////////////////////////////////
                    default:
                        break;                
                    }
                    break;
                ///////////////////////////////////////////////////////////////////////////////////////////////////
                case 3:
                    if(backups.isEmpty())
                    {
                        System.out.printf("\nThere is no account deleted\n");
                    }
                    else
                    {
                        System.out.printf("\nWhich user to recover data:\n=>");
                        user = input.nextLine();

                        for(int i=0; i<backups.size(); i++)
                        {
                            if(backups.get(i).getUser().intern() == user.intern())
                            {
                                System.out.println("\n"+backups.get(i).showBackup());
                                break;
                            }
                        }
                    }
                    break;
                ///////////////////////////////////////////////////////////////////////////////////////////////////    
                case 4:
                    Backup conta = new Backup(self);
                    accounts.remove(accounts.get(searcher.search(accounts, self.getUser())));
                    backups.add(conta);
                    active = false;
                    break;
                ///////////////////////////////////////////////////////////////////////////////////////////////////
                case 5:
                    active = false;
                    break;
                default:
                    break;                    
            }
        }
    }
}
