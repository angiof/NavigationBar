package com.example.navigationbar.LeTueSchedePackage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Schede implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int Id;

    @ColumnInfo(name = "title_colum")
    private String title;

    @ColumnInfo(name = "message_colum")
    private String message;

    @ColumnInfo(name = "date_colum")
    private String data;

    public Schede(String title, String message) {
        this.title = title;
        this.message = message;
        data = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime());
    }

    public Schede() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
