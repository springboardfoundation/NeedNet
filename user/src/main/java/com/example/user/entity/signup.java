package com.example.user.entity;


import javax.persistence.*;

@Entity
@Table(name="UserTable",schema = "Needs")
public class signup {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name="UserNumber")
    private int userNumber;
    private String name;
    private String email;
    private String place;
    private int age;
    private String gender;
    private String occupation;

    private int MobileNumber;

    @OneToOne(mappedBy = "UserTable")
    private User_Trigger user;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
