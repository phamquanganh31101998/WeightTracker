package com.example.weighttracker;

public class CaloTracker {
    String date = "";
    String note = "";
    int calo = 0;

    public CaloTracker(String date, String note, int calo) {
        this.date = date;
        this.note = note;
        this.calo = calo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }
}
