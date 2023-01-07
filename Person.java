import java.io.Serializable;

public class Person implements Serializable{
    private String name;
    private int age;
    private String gender;
    private String ncic;
    
    public Person(String name, int age, String gender, String ncic) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ncic = ncic;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNcic() {
        return ncic;
    }
    public void setNcic(String ncic) {
        this.ncic = ncic;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", ncic=" + ncic + "]";
    }
    

}
