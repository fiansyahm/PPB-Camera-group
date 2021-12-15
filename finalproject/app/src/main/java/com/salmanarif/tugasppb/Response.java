package com.salmanarif.tugasppb;

public class Response {
    private boolean status;
    private String remarks;
    private String id;
    private String nama;
    private String posisi;
    private String schedule[];
    private String scheduledet[];
    private int totalrow;

    public boolean isStatus() {
        return status;
    }

    public String getRemark() {
        return remarks;
    }

    public String getID() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getPosisi() {
        return posisi;
    }
    public String [] getSchedule() {
        return schedule;
    }
    public String [] getScheduledet() {
        return scheduledet;
    }
    public int getTotalrow() {
        return totalrow;
    }
}
