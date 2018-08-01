package com.example.marcelogr.bookfy.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.marcelogr.bookfy.Activities.MainActivity;
import com.example.marcelogr.bookfy.Adapter.LivroPessoalAdapter;
import com.example.marcelogr.bookfy.DAL.UsuarioDAO;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.ItemLista;
import com.example.marcelogr.bookfy.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        int id2 = activity.retornaId();
        // Inflate the layout for this fragment
        View rootView = (View) inflater.inflate(R.layout.fragment_two, container, false);

        DBHelper dbh = new DBHelper(getActivity());
        UsuarioDAO ud = new UsuarioDAO();
        if(dbh.retornaListaItens(id2)==null){

        }else{
            List<ItemLista> lista = dbh.retornaListaItens(id2);
            LivroPessoalAdapter livro_pessoa_adapter = new LivroPessoalAdapter(lista, getActivity(), id2);

            ListView listView = (ListView) rootView.findViewById(R.id.biblioteca_pessoal_array);
            listView.setAdapter(livro_pessoa_adapter);
        }

        return rootView;
    }

}
