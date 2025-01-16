package org.restaurant.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    String name = null;
    String tel = null;
    LocalDate date;
    LocalTime startedTime;
    LocalTime finishedTime;
    int numberOfCustomer;
    int numberOfTables;

    public Reservation(String name, String tel, String date, String startedTime, String finishedTime, int numberOfCustomer) {
        this.name = name;
        this.tel = tel;
        this.date = LocalDate.parse(date);
        this.startedTime = LocalTime.parse(startedTime);
        this.finishedTime = LocalTime.parse(finishedTime);
        this.numberOfCustomer = numberOfCustomer;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartedTime() {
        return startedTime;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public int getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public LocalTime getFinishedTime() {
        return finishedTime;
    }
}
