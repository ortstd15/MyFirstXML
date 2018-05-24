package model;

public class Employee {
    private String name, role, gender;
    private int age;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "\n" +
                "Employee \n" +
                "name   =   " + name + '\n' +
                "role   =   " + role + '\n' +
                "gender =   " + gender + '\n' +
                "age    =   " + age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
