package com.example.marcelogr.bookfy.Models;

import java.io.Serializable;

public class ItemLista implements Serializable{

    private int itemlista_id;
    private int livro_id;
    private String estado;
    private int usuario_id;

//    public ItemLista(){
//        this.livro = new Livro();
//    }
//
//    public Livro getLivro() {
//        return livro;
//    }
//
//    public void setLivro(Livro livro) {
//        this.livro = livro;
//    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(int livro_id) {
        this.livro_id = livro_id;
    }

    public int getItemlista_id() {
        return itemlista_id;
    }

    public void setItemlista_id(int itemlista_id) {
        this.itemlista_id = itemlista_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}
