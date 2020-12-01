package com.example.navigationbar.LeTueSchedePackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.example.navigationbar.LeTueSchedePackage.Database.SchedeDatabase;
import com.example.navigationbar.R;
import com.example.navigationbar.databinding.ActivityEditBinding;
import com.example.navigationbar.views.SchedeFragment;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class EditActivity extends AppCompatActivity {


    ActivityEditBinding activityEditBinding;
    private static final  String TAG = EditActivity.class.getName();
    private static final  String ACTION_KEY = TAG + ".action.key";
    private static final String NOTA_KEY = TAG + ".nota.key";
    private char action;
    private SchedeDatabase db;
    private EditText textschede;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditBinding= ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(activityEditBinding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(" "); //spazio obbligatorio

        //textnote è richiamato nei due contest scrolling
        textschede =findViewById(R.id.textschede);

        //dataBase richiamato
        db = Room.databaseBuilder(this, SchedeDatabase.class, "schede_database")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        //definito come azione iniziale n inteso per "ancora non avviata nessuna azione"
        action=getIntent().getCharExtra(ACTION_KEY,'n');

        //serializable è una funzione per distruggere e far ricomparire dati da un'altra parte(il concetto il teletrasporto)
        final Schede schede = (Schede) getIntent().getSerializableExtra(NOTA_KEY);

        //setta il titolo e la nota nella fase di modifica nota
        if (action=='e'){
         textschede.setText(schede.getMessage());
         activityEditBinding.editTitle.setText(schede.getTitle());

        }

        //aggiunge o modifica database
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((view)-> {
           String titleEdit = activityEditBinding.editTitle.getText().toString().trim();
            String textEdit = textschede.getText().toString().trim();
            if(!titleEdit.isEmpty() || !textEdit.isEmpty()){
               if (action=='a'){
                   //aggiungi al database
                   db.schedeDao().insertALL(new Schede(titleEdit,textEdit));

               }else if (action=='e'){
                   //modifica database
                   db.schedeDao().delete(schede);
                   db.schedeDao().insertALL(new Schede(titleEdit,textEdit));
               }
               SchedeFragment.adapterNotifyAll();
                finish();
                Intent intent = new Intent(this,SchedeFragment.class);
                startActivity(intent);

            } else {
                Snackbar.make(view,"Inserisci qualcosa", Snackbar.LENGTH_SHORT).show();
            }


        });
    }

    //funzioni che riceve dati a seconda dell'azione
    public static Intent getIntentEdit(Context context,char action,Schede schede){
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(ACTION_KEY, action);
        intent.putExtra(NOTA_KEY, schede);

        return intent;


    }

    //funzioni che riceve dati a seconda dell'azione
    public static Intent getIntentEdit(Context context, char action){
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(ACTION_KEY, action);

        return intent;


    }

}