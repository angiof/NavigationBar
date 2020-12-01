package com.example.navigationbar.LeTueSchedePackage.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.navigationbar.LeTueSchedePackage.Schede;
import com.example.navigationbar.LeTueSchedePackage.Schede;


@Database(entities = {Schede.class},version = 1)
public abstract class SchedeDatabase extends RoomDatabase
{
public abstract SchedeDao schedeDao();



}
