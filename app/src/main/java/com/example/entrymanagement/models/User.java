package com.example.entrymanagement.models;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class User {
    private String uId;
    private String visitorName;
    private String visitorEmail;
    private String visitorPhone;
    private String hostName;
    private String hostEmail;
    private String hostPhone;
    private boolean checkedIn;
    private Date checkInTime;
    private Date checkOutTime;

    public User(){}

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorEmail() {
        return visitorEmail;
    }

    public void setVisitorEmail(String visitorEmail) {
        this.visitorEmail = visitorEmail;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public String getHostPhone() {
        return hostPhone;
    }

    public void setHostPhone(String hostPhone) {
        this.hostPhone = hostPhone;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public String getCheckOutTime(int a){
        return String.valueOf(checkOutTime.getHours()) + ":" + String.valueOf(checkOutTime.getMinutes()) + "  " + String.valueOf(checkOutTime.getDate()) + "/" + String.valueOf(checkInTime.getMonth()) + "/" + String.valueOf(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR));
    }
    public String getCheckInTime(int a){
        return String.valueOf(checkInTime.getHours()) + ":" + String.valueOf(checkInTime.getMinutes()) + "  " + String.valueOf(checkInTime.getDate()) + "/" + String.valueOf(checkInTime.getMonth()) + "/" + String.valueOf(Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR)) ;
    }


    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public User(String uId,String visitorName, String visitorEmail, String visitorPhone, String hostName, String hostEmail, String hostPhone) {
        this.uId = uId;
        this.visitorName = visitorName;
        this.visitorEmail = visitorEmail;
        this.visitorPhone = "+91"+visitorPhone;
        this.hostName = hostName;
        this.hostEmail = hostEmail;
        this.hostPhone = "+91"+hostPhone;
        this.checkedIn = true;
        this.checkInTime = Calendar.getInstance().getTime();
    }

    public void checkOut(){
        this.checkedIn = false;
        this.checkOutTime = Calendar.getInstance().getTime();
    }

    public String getName() {
        return visitorName;
    }

    public void setName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getEmail() {
        return visitorEmail;
    }

    public void setEmail(String visitorEmail) {
        this.visitorEmail = visitorEmail;
    }

    public String getPhoneNumber() {
        return visitorPhone;
    }

    public void setPhoneNumber(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }


}
