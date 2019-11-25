package domain;

import java.util.List;
import java.util.Objects;

public class Team {
    private int id;
    private String name;
    private String sponsor;
    private Double budget;

    public Team(){};

    public Team(int id, String name, String sponsor, Double budget) {
        this.id = id;
        this.name = name;
        this.sponsor = sponsor;
        this.budget = budget;
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Double.compare(team.budget, budget) == 0 &&
                name.equals(team.name) &&
                sponsor.equals(team.sponsor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sponsor, budget);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", budget=" + budget +
                '}';
    }
}
