package com.salmanarif.tugasppb;

public class Response {
    private boolean status;
    private String remarks;
    private String id;
    private String nama;
    private String posisi;
    private String myimage;

    private String currentdatetime[];
    private String workfromhome[];
    private String attendancestatus[];
    private String photo[];
    private String signature[];

    private String schedule[];
    private String scheduledet[];
    private int totalrow;
    private String kesuksesan;



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
    public String getMyimage(){return myimage;}
    public String [] getCurrentdatetime(){return currentdatetime;}
    public String [] getWorkfromhome(){return workfromhome;}
    public String [] getAttendancestatus(){return attendancestatus;}
    public String [] getPhoto(){return photo;}
    public String [] getSignature(){return signature;}

    public String [] getSchedule() {
        return schedule;
    }
    public String [] getScheduledet() {
        return scheduledet;
    }
    public int getTotalrow() {
        return totalrow;
    }
    public String getKesuksesan(){
        return kesuksesan;
    }


}
