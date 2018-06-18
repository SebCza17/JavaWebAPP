package Model.Entity;

import java.util.Date;
import java.util.List;

public class BookedEntity {

    private int id;
    private int idClasses;
    private Date day;
    private List<Integer> hours;
    private int idUser;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClasses() {
        return idClasses;
    }

    public void setIdClasses(int idClasses) {
        this.idClasses = idClasses;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public List<Integer> getHours() {
        return hours;
    }

    public void setHours(List<Integer> hours) {
        this.hours = hours;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
