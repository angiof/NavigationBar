package com.example.navigationbar.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.navigationbar.LeTueSchedePackage.Database.SchedeDatabase;
import com.example.navigationbar.LeTueSchedePackage.DettailsActivity;
import com.example.navigationbar.LeTueSchedePackage.EditActivity;
import com.example.navigationbar.LeTueSchedePackage.Schede;
import com.example.navigationbar.R;
import com.example.navigationbar.adapter.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class SchedeFragment extends Fragment {



    private static SchedeDatabase db;
    private static List<Schede> schedes;
    private static RecyclerView recyclerView;
    private static RecyclerViewAdapter adapter;
    private static View viewLayout;
    private static Context baseContext;
    private static FloatingActionButton button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schede, container, false);


        //a causa della mancanza di staticità ho richiamato il contest rendendolo statico(vedere get value)

        baseContext = getContext();

        //ho dovuto richiamare il layout per poter richiamare la recycler dentro get value(vedere get value)
        viewLayout = view.findViewById(R.id.viewLayout);


        //richiamo il database per correttezza creo un costrutto if ponendo istanza uguale a null
        if (savedInstanceState == null) {
            db = Room.databaseBuilder(baseContext, SchedeDatabase.class, "note_database")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        //riprendo tutti i valori dal database e tramite linear layout e adapter li dispongo nella recycler
        getValue();


        button = view.findViewById(R.id.aggiungi);
        //intent che porta all'editactivity che usa come azione la chear a
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EditActivity.getIntentEdit(baseContext, 'a'));
            }
        });


        return view;
    }
    private static void getValue() {
        schedes = db.schedeDao().getAll();
        recyclerView = viewLayout.findViewById(R.id.recyclerView);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(baseContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new RecyclerViewAdapter(schedes,baseContext);
        recyclerView.setAdapter(adapter);


    }

    //salvataggio delle modifiche richiamato nei layout scrolling
    public static void adapterNotifyAll(){

        getValue();

    }

    //funzione per settare nella recycler che quando viene cliccata una nota si rimanda alla modifica nota
    public static void sendObject(Context context, int position) {
        context.startActivity(DettailsActivity.getDettailsIntent(context, schedes.get(position)));
    }
}