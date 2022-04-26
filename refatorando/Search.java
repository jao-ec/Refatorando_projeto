import java.util.ArrayList;

public class Search {

    public int search(ArrayList<Profile> accounts, String user)
    {
        for(int i=0; i<accounts.size(); i++)
        {
            if(user == (accounts.get(i).getUser()).intern())
            {
                return i;
            }
        }
        return -1;
    }
}
