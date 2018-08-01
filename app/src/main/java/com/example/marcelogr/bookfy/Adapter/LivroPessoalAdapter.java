package com.example.marcelogr.bookfy.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcelogr.bookfy.Activities.AcaoActivity;
import com.example.marcelogr.bookfy.Activities.MainActivity;
import com.example.marcelogr.bookfy.DAL.GeneroDAO;
import com.example.marcelogr.bookfy.DAL.LivroDAO;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.ItemLista;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.R;

import java.util.List;

public class LivroPessoalAdapter extends BaseAdapter {

    private List<ItemLista> itens;
    private Activity act;
    private int id;

    public LivroPessoalAdapter(List<ItemLista> itens, Activity act, int id){
        this.itens = itens;
        this.act = act;
        this.id = id;
    }

    @Override
    public int getCount() {
        return this.itens.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(act.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_livro_pessoal, null);
        }
        final ItemLista item = itens.get(position);

        final TextView nome = view.findViewById(R.id.nome);
        final TextView genero = view.findViewById(R.id.genero);
        final TextView editar = view.findViewById(R.id.textEditar);
        final TextView estado = view.findViewById(R.id.estado);

        //LivroDAO ld = new LivroDAO();
        //Livro l = ld.encontrarLivroPorId(item.getLivro_id());
        DBHelper dbh = new DBHelper(view.getContext());
        Livro l = dbh.encontrarLivroPorId(item.getLivro_id());
        nome.setText(l.getLivro_nome().toString());
        //GeneroDAO gd = new GeneroDAO();
        genero.setText(dbh.retornaGeneroPorId(l.getGenero_Id()).getDescricao().toString());
        estado.setText(item.getEstado().toUpperCase());

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(act, AcaoActivity.class);
                i.putExtra("id", id);
                Bundle bundle = new Bundle();
                bundle.putSerializable("itemObjeto", item);
                i.putExtras(bundle);
                i.setAction("editar");
                act.startActivity(i);
                //act.finish();
            }
        });

        return view;
    }
}
