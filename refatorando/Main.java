import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String args[])
    {
        ArrayList<Profile> accounts = new ArrayList<Profile>();
        ArrayList<Backup> backups = new ArrayList<Backup>();
        boolean active = true;
        int choise;

        while(active)
        {
            System.out.println("\nBem-vindo ao Iface!");
            System.out.print("[1] Loggin\n[2] Register\n[3] Logout\n=>");
            choise = input.nextInt();
            input.nextLine();

            switch(choise)
            {
                case 1:

                    Login login = new Login();
                    int index = login.checkLogin(accounts);

                    if(index > -1)
                    {
                        login.menu(accounts, backups, index);
                    }

                    break;

                case 2:
                    
                    Register register = new Register();
                    register.setRigister(accounts);
                    break;

                case 3:

                    active = false;
                    break;

                default:
                    break;
            }
        }
        System.out.println("Users registed: ["+accounts.size()+"]");

        for(int i=0; i<accounts.size(); i++)
        {
            System.out.println(accounts.get(i).user);
        }
    }
}
