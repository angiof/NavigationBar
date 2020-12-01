package com.example.navigationbar.LeTueSchedePackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.example.navigationbar.LeTueSchedePackage.Database.SchedeDatabase;
import com.example.navigationbar.R;
import com.example.navigationbar.views.SchedeFragment;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DettailsActivity extends AppCompatActivity {


    private static final String TAG = DettailsActivity.class.getName() ;
    private static final String KEY = TAG + ".key";
    private SchedeDatabase db;
    private TextView textschede;
    private Schede schede;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettails);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        textschede =findViewById(R.id.textschede);

        //richiamo Database
        db = Room.databaseBuilder(this, SchedeDatabase.class, "schede_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        schede = (Schede) getIntent().getSerializableExtra(KEY);

        assert schede !=null;

        toolBarLayout.setTitle(schede.getTitle());

        textschede.setText(schede.getMessage());




           //ritorni nel Activity di modifica
           FloatingActionButton editfab = (FloatingActionButton) findViewById(R.id.editFab);
           editfab.setOnClickListener((view)-> {
             startActivity(EditActivity.getIntentEdit(DettailsActivity.this,'e', schede));


           });


           //tramite funzione showAlert elimini la nota
           FloatingActionButton deletefab = (FloatingActionButton) findViewById(R.id.deleteFab);
           deletefab.setOnClickListener((view)-> {
             showAlert("Conferma cancellazione","Vuoi davvero cancellare la tua nota?");


           });
    }

    private void showAlert(String title,String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(DettailsActivity.this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              db.schedeDao().delete(schede);
              SchedeFragment.adapterNotifyAll();
              finish();
                Toast.makeText(DettailsActivity.this, "Nota eliminata", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             dialog.dismiss();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    //funzione che riceve dati a seconda dell'azione
    public static Intent getDettailsIntent(Context context,Schede schede){
     Intent intent = new Intent(context, DettailsActivity.class);
     intent.putExtra( KEY, schede);
     
     return intent;

    }
}