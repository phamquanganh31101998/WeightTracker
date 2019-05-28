package com.example.weighttracker;

public class CaloTracker {
    String date = "";
    int calo = 0;
    String note = "";

    public CaloTracker(String date, int calo, String note) {
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

    @Override
    public String toString() {
        return ("Ngày " + getDate() + " tiêu thụ " + getCalo() + ", ghi chú: " + getNote());
    }

    public boolean equals(CaloTracker obj) {
        if((this.date == obj.date) && (this.note == obj.note) && (this.calo == obj.calo))
            return true;
        else
            return false;
    }
}
