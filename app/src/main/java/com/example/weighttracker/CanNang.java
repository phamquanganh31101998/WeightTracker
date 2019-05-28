package com.example.weighttracker;

public class CanNang {
    private int id;
    private int cannang;
    private String ngay;

    public CanNang(int id, int cannang, String ngay) {
        this.id = id;
        this.cannang = cannang;
        this.ngay = ngay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCannang() {
        return cannang;
    }

    public void setCannang(int cannang) {
        this.cannang = cannang;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
