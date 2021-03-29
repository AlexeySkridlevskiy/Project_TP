package alkofind;

public class User {

    private Long ID;
    private String Name;
    private String Surname;
    private int Age;
    private String Email;
    private String Password;

    public User(Long id, String name, String surname, int age, String email, String passw){
        this.ID =id;
        this.Name =name;
        this.Surname =surname;
        this.Age =age;
        this.Email =email;
        this.Password =passw;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
