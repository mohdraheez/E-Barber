package com.example.hairsalonbookingapp;

public class Booking {

    String id;
    String date;
    String time;
    String barber;
    String status;

    public Booking(String id,String date,String time, String barber,String status){
        this.id =id;
        this.date = date;
        this.time = time;
        this.barber = barber;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getTime() {

        return time;
    }
    public void setTime(String time){
        this.time = time;
    }


    public String getBarber() {
        return barber;
    }
    public void setBarber(String barber){
        this.barber = barber;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

}
