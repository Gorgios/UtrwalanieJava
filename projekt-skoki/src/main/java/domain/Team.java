package domain;

import java.util.List;

public class Team {
    private int id;
    private String name;
    private String sponsor;
    private float budget;
    private List<Jumper> jumpers;

    public Team(){};

    public Team(String name, String sponsor, float budget, List<Jumper> jumpers) {
        this.name = name;
        this.sponsor = sponsor;
        this.budget = budget;
        this.jumpers = jumpers;
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public List<Jumper> getJumpers() {
        return jumpers;
    }

    public void setJumpers(List<Jumper> jumpers) {
        this.jumpers = jumpers;
    }
}
