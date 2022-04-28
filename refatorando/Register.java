import java.util.Scanner;
import java.util.ArrayList;

public class Register 
{
    Scanner input = new Scanner(System.in);

    Profile entire = new Profile();


    public void setRigister(ArrayList<Profile> accounts)
    {
        if(!accounts.isEmpty()){
            for(int i=0; i<accounts.size(); i++)
            {
                if(this.entire.getUser().intern() == (accounts.get(i).getUser()).intern())
                {
                    System.out.printf("\nThis user already is registed!\n");
                    return;
                }
            }
        }
        entire.setPassword();
        accounts.add(entire);
        System.out.print("\nWelcome to the Iface "+entire.getUser()+"\n");
    }
}
