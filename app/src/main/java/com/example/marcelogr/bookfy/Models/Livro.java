package com.example.marcelogr.bookfy.Models;

import java.io.Serializable;

public class Livro implements Serializable {

    private int livro_id;
    private String livro_nome;
    private int genero_Id;
//    private boolean assistindo;
//    private boolean tarde;
//    private boolean terminado;

    public int getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(int livro_id) {
        this.livro_id = livro_id;
    }

    public String getLivro_nome() {
        return livro_nome;
    }

    public void setLivro_nome(String livro_nome) {
        this.livro_nome = livro_nome;
    }

    public int getGenero_Id() {
        return genero_Id;
    }

    public void setGenero_Id(int generoId) {
        this.genero_Id = generoId;
    }




}
