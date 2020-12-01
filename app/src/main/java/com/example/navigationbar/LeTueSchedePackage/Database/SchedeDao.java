package com.example.navigationbar.LeTueSchedePackage.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.navigationbar.LeTueSchedePackage.Schede;
import com.example.navigationbar.LeTueSchedePackage.Schede;

import java.util.List;

@Dao
public interface SchedeDao {
     @Query("SELECT * FROM schede")
    List<Schede> getAll();

   @Insert
    void insertALL(Schede... schedes);

   @Delete
    void delete(Schede schede);
}
