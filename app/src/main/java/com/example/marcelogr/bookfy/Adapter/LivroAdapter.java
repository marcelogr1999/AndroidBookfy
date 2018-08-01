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

import com.example.marcelogr.bookfy.Activities.AcaoActivity;
import com.example.marcelogr.bookfy.Activities.MainActivity;
import com.example.marcelogr.bookfy.DAL.GeneroDAO;
import com.example.marcelogr.bookfy.DAL.UsuarioDAO;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.ItemLista;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.R;

import java.util.List;

public class LivroAdapter extends BaseAdapter {

    private List<Livro> livros;
    private Activity act;
    private int id;

    public LivroAdapter(List<Livro> livros, Activity act, int id){
        this.livros = livros;
        this.act = act;
        this.id = id;
    }

    @Override
    public int getCount() {
        return this.livros.size();
    }

    @Override
    public Object getItem(int position) {
        return this.livros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(act.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_livro, null);
        }
        final Livro livro = livros.get(position);

        final TextView nome = view.findViewById(R.id.nome);
        final TextView genero = view.findViewById(R.id.genero);
        final TextView acao = view.findViewById(R.id.textAdicionarEditar);

        nome.setText(livro.getLivro_nome().toString());
        final DBHelper dbh = new DBHelper(view.getContext());
        genero.setText(dbh.retornaGeneroPorId(livro.getGenero_Id()).getDescricao());

        if(dbh.achaItemUsuario(livro.getLivro_id(), id)!=-1){
            acao.setText("Editar");
        }

        acao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO ud = new UsuarioDAO();
                Intent i = new Intent(act, AcaoActivity.class);
                i.putExtra("id", id);
                Bundle bundle = new Bundle();

                if(dbh.achaItemUsuario(livro.getLivro_id(), id)==-1){
                    bundle.putSerializable("livroObjeto", livro);
                }else{
                    ItemLista il = dbh.encontrarLivroRetornaItem(livro, id);
                    bundle.putSerializable("itemObjeto", il);
                    i.setAction("pessoal");
                }

                i.putExtras(bundle);
                act.startActivity(i);
                //act.finish();
            }
        });

        return view;
    }
}
