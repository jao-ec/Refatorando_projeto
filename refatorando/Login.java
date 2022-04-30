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
            input.nextLine();
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
        int choise, choise2;
        String user, name;
        boolean active = true, flag = true;
        Profile self = accounts.get(index);

        while(active)
        {
            System.out.println(self.showPerfil());
            System.out.printf("\n[1] - Profile\n[2] - Community\n[3] - Recover data\n[4] - Delete my account\n[5] - Exit\n=>");
            choise = input.nextInt();
            input.nextLine();
            
            switch(choise)
            {
                case 1:
                    self.menuProfile(accounts);
                    break;
                
                case 2:
                flag = false;
                    System.out.printf("\n[1] - Creat community\n[2] - Search community\n[3] - All community\n[4] - Exit\n=>");

                    choise2 = input.nextInt();
                    input.nextLine();

                    switch(choise2)
                    {
                        case 1:
                            Community comunidade = new Community(self.getUser());

                            for(int i=0; i<community.size(); i++)
                            {
                                if(community.get(i).getCommunity_name().intern() == comunidade.getCommunity_name().intern())
                                {
                                    System.out.printf("\nThis community name already is registed!\n");
                                    input.nextLine();
                                    flag = true;
                                }
                            }

                            if(!flag)
                            {
                                comunidade.members.add(new Friend(self.getUser()));
                                community.add(comunidade);
                                self.admin_communities.add(comunidade);
                            }

                            break;
                        ////////////////////////////////////////////////////////////////////////////////////////////////
                        case 2:
                            System.out.printf("\nWhat is community' name:\n=>");
                            name = input.nextLine();

                            flag = false;

                            for(int i=0; i<community.size(); i++)
                            {
                                if(community.get(i).getCommunity_name().intern() == name.intern())
                                {
                                    community.get(i).menuCommunity(self, accounts, community);
                                    flag = true;
                                    break;
                                }
                            }

                            if(!flag)
                            {
                                System.out.println("\nThis community does not exist\n");
                                input.nextLine();
                            }
                            break;
                        ///////////////////////////////////////////////////////////////////////////////////////////////
                        case 3:
                            System.out.printf("\n");
                            for(int i=0; i<community.size(); i++)
                            {
                                System.out.printf("-->"+community.get(i).showCommunity());
                            }
                            break;
                        
                        ///////////////////////////////////////////////////////////////////////////////////////////////
                        case 4:
                            break;                
                    }
                    break;
                ///////////////////////////////////////////////////////////////////////////////////////////////////
                case 3:
                    if(backups.isEmpty())
                    {
                        System.out.printf("\nThere is no account deleted\n");
                        input.nextLine();
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
