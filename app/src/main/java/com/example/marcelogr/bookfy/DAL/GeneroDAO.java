package com.example.marcelogr.bookfy.DAL;
import android.database.sqlite.SQLiteDatabase;

import com.example.marcelogr.bookfy.Models.Genero;
import com.example.marcelogr.bookfy.Models.Livro;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneroDAO {

    static ArrayList<Genero> generos = new ArrayList<Genero>();
    static AtomicInteger geradorID = new AtomicInteger(1);


    public void salvarInicial() {
        Genero gen1 = new Genero();
        gen1.setDescricao("Drama");
        salvar(gen1);
    }

    public void salvar(Genero genero){
        if(!encontrarGenero(genero.getDescricao())) {
            genero.setGenero_id(geradorID.getAndIncrement());
            generos.add(genero);
        }
    }

    public String retornaGeneroPorId(int id) {
        for (Genero genero: generos) {
            if(genero.getGenero_id()==id){
                return genero.getDescricao();
            }
        }
        return null;
    }

    public Boolean encontrarGenero(String nome){
        for (Genero g: generos) {
            if(g.getDescricao().equals(nome)){
                return true;
            }
        }
        return false;
    }


//
//    public void excluir(Contato c) {
//        contatos.remove(c);
//    }
}


