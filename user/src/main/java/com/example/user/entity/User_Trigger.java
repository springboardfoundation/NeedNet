package com.example.user.entity;


import javax.persistence.*;

@Entity
@Table(name="User_Trigger")
public class User_Trigger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userNumber", referencedColumnName = "id")
    private signup usernumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public signup getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(signup usernumber) {
        this.usernumber = usernumber;
    }
}
