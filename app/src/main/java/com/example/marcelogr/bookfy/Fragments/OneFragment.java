package com.example.marcelogr.bookfy.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marcelogr.bookfy.Activities.MainActivity;
import com.example.marcelogr.bookfy.Adapter.LivroAdapter;
import com.example.marcelogr.bookfy.DAL.LivroDAO;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        int id1 = activity.retornaId();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);

        //LivroDAO ld = new LivroDAO();
        //List<Livro> livros = ld.listar();
        DBHelper dbh = new DBHelper(getActivity());
        List<Livro> livros = dbh.retornaLivros();


        LivroAdapter livroAdapter = new LivroAdapter(livros, getActivity(), id1);

        ListView listView = (ListView) rootView.findViewById(R.id.biblioteca_array);
        listView.setAdapter(livroAdapter);

        return rootView;
    }

}
