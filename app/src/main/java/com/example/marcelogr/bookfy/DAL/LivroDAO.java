package com.example.marcelogr.bookfy.DAL;

import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LivroDAO {

    static ArrayList<Livro> livros = new ArrayList<Livro>();
    static AtomicInteger geradorID = new AtomicInteger(1);

    public void salvarInicial() {

        Livro liv1 = new Livro();
        liv1.setLivro_nome("Stein's Gate");
        liv1.setGenero_Id(1);


        Livro liv2 = new Livro();
        liv2.setLivro_nome("Batata");
        liv2.setGenero_Id(1);

        salvar(liv1);
        salvar(liv2);
    }

    public void salvar(Livro livro){
        if(!encontrarLivro(livro.getLivro_nome())) {
            livro.setLivro_id(geradorID.getAndIncrement());
            livros.add(livro);
        }


    }

    public List<Livro> listar() {
        return livros;
    }

    public Boolean encontrarLivro(String nome){
        for (Livro l: livros) {
            if(l.getLivro_nome().equals(nome)){
                return true;
            }
        }
        return false;
    }

    public Livro encontrarLivroPorId(int id){
        for (Livro l: livros) {
            if(l.getLivro_id()==id){
                return l;
            }
        }
        return null;
    }
//
//    public void excluir(Contato c) {
//        contatos.remove(c);
//    }
}
