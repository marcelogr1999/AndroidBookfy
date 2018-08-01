package com.example.marcelogr.bookfy.DAL;


import android.widget.Toast;

import com.example.marcelogr.bookfy.Models.ItemLista;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.Models.Usuario;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioDAO {

    static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    static AtomicInteger geradorID = new AtomicInteger(1);

//    public int salvarInicial() {
//        Usuario us1 = new Usuario();
//        us1.setUser("user");
//        us1.setPass("pass");
//
//        Usuario us2 = new Usuario();
//        us2.setUser("user2");
//        us2.setPass("pass2");
//
//        salvar(us1);
//        salvar(us2);
//
//        return usuarios.size();
//    }
//
//    public void salvar(Usuario usuario){
//        if(!encontrarUsuario(usuario.getUser())) {
//            usuario.setUsuario_id(geradorID.getAndIncrement());
//            usuarios.add(usuario);
//        }else{
//            for (Usuario u: usuarios) {
//                if(u.getUsuario_id()==usuario.getUsuario_id()){
//                    u = usuario;
//                }
//            }
//        }
//    }
//
//    public Boolean encontrarUsuario(String nome){
//        for (Usuario u: usuarios) {
//            if(u.getUser().equals(nome)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public int logar(String user, String pass){
//        for (Usuario u : usuarios) {
//            if((u.getUser().equals(user))&&(u.getPass().equals(pass))){
//                return u.getUsuario_id();
//            }
//        }
//        return -1;
//    }
//
//    public Usuario buscarPorId(int id){
//        for (Usuario u : usuarios) {
//            if(u.getUsuario_id()==id){
//                return u;
//            }
//        }
//        return null;
//    }
//
//    public int retornarIndex(int id){
//        for (int p = 0; p < usuarios.size() ; p++) {
//            if(usuarios.get(p).getUsuario_id()==id){
//                return p;
//            }
//        }
//        return -1;
//    }
//
//    public void adicionarLista(ItemLista il, int id){
//        Usuario u = buscarPorId(id);
//        int posicao = procurarLivroLista(il.getLivro_id(), id);
//
//        if(posicao!=-1){
//            u.getLista().get(posicao).setEstado(il.getEstado());
//            u.getLista().get(posicao).setLivro_id(il.getLivro_id());
//        }else{
//            u.getLista().add(il);
//        }
//
//    }
//
//    public void removerLista(Livro l, int id){
//        int p = procurarLivroLista(l.getLivro_id(), id);
//        usuarios.get(retornarIndex(id)).getLista().remove(p);
//    }
//
//    public ArrayList<ItemLista> retornaLista(int id){
//        Usuario u = buscarPorId(id);
//        return u.getLista();
//    }
//
//    public int procurarLivroLista(int livro_id, int id) {
//
//        Usuario u = buscarPorId(id);
//
//        for (int p = 0; p < u.getLista().size() ; p++) {
//            if(u.getLista().get(p).getLivro_id()==livro_id){
//                return p;
//            }
//        }
//        return -1;
//    }
//
//    public ItemLista procurarLivroRetornaItem(Livro l, int id) {
//
//        Usuario u = buscarPorId(id);
//
//        for (ItemLista item_lista: u.getLista()) {
//            if(item_lista.getLivro_id()==l.getLivro_id()){
//                return item_lista;
//            }
//        }
//        return null;
//    }


}
