package org.sbf.user.entity;


import javax.persistence.*;

@Entity
@Table(name="user_trigger")
public class UserTrigger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userNumber", referencedColumnName = "id")
    private User usernumber;

    private String responseId;

    private String responseCode;

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(User usernumber) {
        this.usernumber = usernumber;
    }
}
