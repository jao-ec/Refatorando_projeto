import java.util.ArrayList;

public class Backup implements Gets
{   
    private String user;
    private String name;
    private String gender;
    private String age;
    private String schooling;
    private String country;
    private String about;

    ArrayList<Friend>    friends     = new ArrayList<Friend>();
    ArrayList<Message>   messages    = new ArrayList<Message>();
    ArrayList<Community> communities = new ArrayList<Community>();

    public Backup(Profile backup)
    {
        setUser(backup.getUser());
        setName(backup.getName());
        setGender(backup.getGender());
        setAge(backup.getAge());
        setSchooling(backup.getSchooling());
        setAbout(backup.getAbout());
        this.friends = (ArrayList<Friend>) backup.friends.clone();
        this.messages = (ArrayList<Message>) backup.messages.clone();
        
        for(int i=0; i<backup.admin_communities.size(); i++)
        {
            this.communities.add(backup.admin_communities.get(i));
        }
    }

    public String showBackup()
    {
        return "User: "+getUser()+"\n"+
               getName()+getGender()+getAge()+
               getSchooling()+getCountry()+getAbout();
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public void setSchooling(String schooling)
    {
        this.schooling = schooling;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public void setAbout(String about)
    {
        this.about = about;
    }

    @Override
    public String getName()
    {
        return "Name: "+this.name+"\n";
    }

    @Override
    public String getGender()
    {
        return "Gender: "+this.gender+"\n";
    }

    @Override
    public String getAge()
    {
        return "Age: "+this.age+"\n";
    }

    @Override
    public String getSchooling()
    {
        return "Schooling: "+this.schooling+"\n";
    }

    @Override
    public String getCountry()
    {
        return "Country: "+this.country+"\n";
    }

    @Override
    public String getAbout()
    {
        return "About: "+this.about+"\n";
    }

    public String getUser()
    {
        return this.user;
    }
}
