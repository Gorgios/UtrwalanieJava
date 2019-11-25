package domain;

import java.sql.Date;

public class Jumper {

    private int id;
    private String name;
    private String surname;
    private Date date_of_birth;
    private float personal_best;
    private int carrer_wins;
    private Team team;

    public Jumper(){};

    public Jumper(String name, String surname, Date date_of_birth, float personal_best, int carrer_wins, Team team) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.personal_best = personal_best;
        this.carrer_wins = carrer_wins;
        this.team = team;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public float getPersonal_best() {
        return personal_best;
    }

    public void setPersonal_best(float personal_best) {
        this.personal_best = personal_best;
    }

    public int getCarrer_wins() {
        return carrer_wins;
    }

    public void setCarrer_wins(int carrer_wins) {
        this.carrer_wins = carrer_wins;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
