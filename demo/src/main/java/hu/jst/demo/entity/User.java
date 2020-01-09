package hu.jst.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="users_login")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "budget", nullable = false)
    private long budget = 10000000;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        if(budget > 0) this.budget = budget;
        else {
            System.out.println("The budget can't be negative!");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", budget=" + budget +
                '}';
    }
}
