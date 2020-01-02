package org.ug.project2.model;

import javax.persistence.*;

@Entity
@Table(name = "jumper")
public class Jumper {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "personal_best")
    private Double personalBest;
    @Column(name = "carrer_wins")
    private short carrerWins;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="id_team")
    private Team team;
    public Jumper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getPersonalBest() {
        return personalBest;
    }

    public void setPersonalBest(Double personalBest) {
        this.personalBest = personalBest;
    }

    public short getCarrerWins() {
        return carrerWins;
    }

    public void setCarrerWins(short carrerWins) {
        this.carrerWins = carrerWins;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
