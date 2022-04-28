import java.util.Scanner;

public class User implements Base
{
    Scanner input = new Scanner(System.in);


    protected String user;
    protected String password;
    private String name;
    private String gender    = "edit_gender";
    private String age       = "edit_age";
    private String schooling = "edit_schooling";
    private String country   = "edit_country";
    private String about     = "edit_about";

    protected void setUser()
    {
        System.out.printf("What is your username:\n=>");
        this.user = input.nextLine();

    }

    protected void setPassword()
    {
        System.out.printf("What is your password:\n=>");
        this.password = input.nextLine();
    }

    public void edit()
    {
        this.setName();
        this.setGender();
        this.setAge();
        this.setSchooling();
        this.setCountry();
        this.setAbout();
    }

    public String apresentation(Profile user)
    {
        return "hi "+user.getUser()+", I am "+this.user+" and I would like to be you friend!\n";
    }

    public void setName()
    {
        System.out.printf("What is your real name:\n=>");
        this.name = input.nextLine();
    }

    public void setGender()
    {
        System.out.printf("What is your gender:\n=>");
        this.gender = input.nextLine();
    }

    public void setAge()
    {
        System.out.printf("What is your age:\n=>");
        this.age = input.nextLine();
    }

    public void setSchooling()
    {
        System.out.printf("What is your schooling:\n=>");
        this.schooling = input.nextLine();
    }

    public void setCountry()
    {
        System.out.printf("What is your country:\n=>");
        this.country = input.nextLine();
    }

    public void setAbout()
    {
        System.out.printf("What you would like say about you:\n=>");
        this.about = input.nextLine();
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getGender()
    {
        return this.gender;
    }

    @Override
    public String getAge()
    {
        return this.age;
    }

    @Override
    public String getSchooling()
    {
        return this.schooling;
    }

    @Override
    public String getCountry()
    {
        return this.country;
    }

    @Override
    public String getAbout()
    {
        return this.about;
    }

    public String getUser()
    {
        return this.user;
    }

}
