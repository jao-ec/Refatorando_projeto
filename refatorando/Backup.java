public class Backup implements Base
{   
    private String user;
    private String name;
    private String gender;
    private String age;
    private String schooling;
    private String country;
    private String about;

    public Backup(String user, String name, String gender,
                  String age, String schooling, String country, String about)
    {
        setName(user);
        setName(name);
        setGender(gender);
        setAge(age);
        setSchooling(schooling);
        setAbout(about);
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