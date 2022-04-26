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
            System.out.println("No registers.\n\n");
            return -1;
        }

        System.out.printf("Username:\n=>");
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
            System.out.println("User or Password are incorrect.");
            input.nextLine();
            return -1;
        }
        else return index;
    }

    public void menu(ArrayList<Profile> accounts, ArrayList<Backup> backups, int index)
    {
        int choise;
        boolean active = true;
        Profile self = accounts.get(index);

        while(active)
        {
            System.out.println(self.showPerfil());
            System.out.printf("\n[1] - Edit perfil\n[2] - Analiser invites\n[3] - Send invite\n[4] - Send message\n[5] - Show messages\n"+
                              "[6] - Delete all message\n[7] - creat community\n[8] - Show Communities\n[9] - Show communities on community\n"+
                              "[10] - Entry on community\n[11] - Show members of a community\n[12] - Post on my communities\n[13] - Show posts\n[14] - Exit\n=>");
            choise = input.nextInt();
            input.nextLine();
            
            switch(choise)
            {
                case 1:
                    self.edit();
                    break;

                case 2:
                    if(self.invites.isEmpty())
                    {
                        System.out.println("You do not have any invite by for now.");
                    }
                    else
                    {
                        for(int i=0; i<self.invites.size(); i++)
                        {
                            System.out.println(self.invites.get(i).getInvite_message());
                            String user_friend = self.invites.get(i).getUsername();

                            System.out.println("Do you accept? [Y/N]");
                            String aux = input.nextLine();

                            if(aux.intern() == "Y".intern())
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

                case 3:
                    Invite convite = new Invite();
                    convite.setUsername(self.getUser());

                    System.out.print("What user would you like send a invite?\n=>");
                    String user = input.nextLine();

                    int aux = searcher.search(accounts, user.intern());

                    if(aux >= 0)
                    {
                        convite.setInvite(self.apresentation(accounts.get(aux)));
                        accounts.get(aux).invites.add(convite);
                        System.out.println("Invite send succefully.\n");
                    }
                    else
                    {
                        System.out.println("This user is not registered yet.\n");
                    }
                    break;
                
                case 4:
                    Message mensagem = new Message(accounts, self.getUser().intern());
                    accounts.get(searcher.search(accounts, mensagem.getTo().intern())).messages.add(mensagem);
                    break;
                
                case 5:
                    self.showMessages();
                    break; 

                case 6:
                    self.messages.clear();
                    break;

                case 7:
                    Community comunidade = new Community(self.getUser());
                    comunidade.members.add(new Friend(self.getUser().intern()));
                    self.communities.add(comunidade);
                    break;

                case 8: 
                    self.showCommunities();
                    break;
                
                case 9:
                    for(int i=0; i<accounts.size(); i++)
                    {
                        for(int j=0; j<accounts.get(i).communities.size(); j++)
                        {
                            if(accounts.get(i).communities.get(j).getUser_admin().intern() == accounts.get(i).getUser().intern())
                            {
                                System.out.println(accounts.get(i).communities.get(j).showCommunity());
                            }
                        }
                    }
                    break;
                
                case 10:
                    System.out.printf("What is community' name:\n=>");
                    String name = input.nextLine();
                    boolean flag = false;

                    for(int i=0; i<accounts.size(); i++)
                    {
                        for(int j=0; j<accounts.get(i).communities.size(); j++)
                        {
                            if(accounts.get(i).communities.get(j).getCommunity_name().intern() == name.intern())
                            {
                                accounts.get(i).communities.get(j).members.add(new Friend(self.getUser()));
                                flag = true;
                                break;
                            }
                        }
                        if(flag == true) break;
                    }
                    break;
                
                case 11:
                    System.out.printf("What is community' name:\n=>");
                    name = input.nextLine();
                    flag = false;

                    for(int i=0; i<accounts.size(); i++)
                    {
                        for(int j=0; j<accounts.get(i).communities.size(); j++)
                        {
                            if(accounts.get(i).communities.get(j).getCommunity_name().intern() == name.intern())
                            {
                                accounts.get(i).communities.get(j).showMembers();
                                flag = true;
                                break;
                            }
                        }
                        if(flag) break;
                    }
                    break;
                
                case 12:
                    System.out.printf("What is community' name:\n=>");
                    name = input.nextLine();

                    for(int i=0; i<self.communities.size(); i++)
                    {
                        if(self.communities.get(i).getCommunity_name().intern() == name.intern())
                        {
                            self.communities.get(i).newsletters.add(new Post());
                        }
                    }
                    break;
                
                case 13:
                    System.out.printf("What is community' name:\n=>");
                    name = input.nextLine();

                    for(int i=0; i<self.communities.size(); i++)
                    {
                        if(self.communities.get(i).getCommunity_name().intern() == name.intern())
                        {
                            self.communities.get(i).posts();
                        }
                    }
                    break;
                    
                case 14:
                    active = false;
                    break;

                default: 
                    break;
            }
        }

    }
}
