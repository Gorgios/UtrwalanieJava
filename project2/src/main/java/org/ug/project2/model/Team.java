package org.ug.project2.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="team")
public class Team {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @OneToMany(mappedBy = "team",
            cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Coach> coaches;
    @OneToMany(mappedBy = "team",
            cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Jumper> jumpers;

    public Team() {
    }
    public Team(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<Jumper> getJumpers() {
        return jumpers;
    }

    public void setJumpers(List<Jumper> jumpers) {
        this.jumpers = jumpers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coaches=" + coaches +
                ", jumpers=" + jumpers +
                '}';
    }
}
