package domain;

import java.sql.Date;
import java.util.Objects;

public class Jumper {

    private int id;
    private String name;
    private String surname;
    private Date date_of_birth;
    private Double personal_best;
    private int carrer_wins;
    private int team;

    public Jumper(){};

    public Jumper(int id, String name, String surname, Date date_of_birth, double personal_best, int carrer_wins, int team) {
        this.id = id;
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

    public Double getPersonal_best() {
        return personal_best;
    }

    public void setPersonal_best(double personal_best) {
        this.personal_best = personal_best;
    }

    public int getCarrer_wins() {
        return carrer_wins;
    }

    public void setCarrer_wins(int carrer_wins) {
        this.carrer_wins = carrer_wins;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jumper jumper = (Jumper) o;
        return id == jumper.id &&
                carrer_wins == jumper.carrer_wins &&
                team == jumper.team &&
                Objects.equals(name, jumper.name) &&
                Objects.equals(surname, jumper.surname) &&
                Objects.equals(date_of_birth, jumper.date_of_birth) &&
                Objects.equals(personal_best, jumper.personal_best);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, date_of_birth, personal_best, carrer_wins, team);
    }

    @Override
    public String toString() {
        return "Jumper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", personal_best=" + personal_best +
                ", carrer_wins=" + carrer_wins +
                ", team=" + team +
                '}';
    }
}
