package com.example.marcelogr.bookfy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.marcelogr.bookfy.Models.Genero;
import com.example.marcelogr.bookfy.Models.ItemLista;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.Models.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String nome_banco = "meuDB";
    private static final int versao_banco = 1;

    private static final String tabela_id = "id";

    private static final String tabela_genero = "generos";
    private static final String Genero_Descricao = "descricao_genero";

    private static final String tabela_livro = "livros";
    private static final String Livro_nome = "nome";
    private static final String Livro_genero_id = "livro_genero_id";

    private static final String tabela_usuario = "usuarios";
    private static final String Usuario_user = "user";
    private static final String Usuario_pass = "pass";

    private static final String tabela_itemlista = "itens";
    private static final String ItemLista_usuario_id = "itemlista_usuario_id";
    private static final String ItemLista_livro_id = "itemlista_livro_id";
    private static final String ItemLista_estado = "estado";




    public DBHelper(Context context) {
        super(context, nome_banco, null, versao_banco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + tabela_genero + "(" +
                tabela_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Genero_Descricao + " TEXT" + ")";

        db.execSQL(create);

        String create2 = "CREATE TABLE " + tabela_livro + "(" +
                tabela_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Livro_nome + " TEXT," +
                Livro_genero_id + " INTEGER," +
                " FOREIGN KEY ("+Livro_genero_id+") REFERENCES "+tabela_genero+"("+tabela_id+"));";

        db.execSQL(create2);

        String create3 = "CREATE TABLE " + tabela_usuario + "(" +
                tabela_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Usuario_user + " TEXT," +
                Usuario_pass + " TEXT" + ")";

        db.execSQL(create3);

        String create4 = "CREATE TABLE " + tabela_itemlista + "(" +
                tabela_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ItemLista_estado + " TEXT," +
                ItemLista_livro_id + " INTEGER," +
                ItemLista_usuario_id + " INTEGER," +
                " FOREIGN KEY ("+ItemLista_livro_id+") REFERENCES "+tabela_livro+"("+tabela_id+")," +
                " FOREIGN KEY ("+ItemLista_usuario_id+") REFERENCES "+tabela_usuario+"("+tabela_id+"));";

        db.execSQL(create4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabela_genero);
        onCreate(db);
        db.close();
    }

    public void salvarUsuario(Usuario usuario){
        if(!encontrarUsuario(usuario.getUser())) {
            ContentValues cv = new ContentValues();
            SQLiteDatabase db = this.getWritableDatabase();
            cv.put(Usuario_user, usuario.getUser());
            cv.put(Usuario_pass, usuario.getPass());
            db.insert(tabela_usuario, null, cv);
            db.close();
        }else{
            List<Usuario> usuarios = retornaUsuarios();
            for (Usuario u: usuarios) {
                if(u.getUsuario_id()==usuario.getUsuario_id()){
                    ContentValues cv = new ContentValues();
                    SQLiteDatabase db = this.getWritableDatabase();
                    cv.put(Usuario_user, usuario.getUser());
                    cv.put(Usuario_pass, usuario.getPass());
                    db.update(tabela_usuario, cv, tabela_id + "="  + usuario.getUsuario_id(), null);
                }
            }
        }
    }


    public Boolean encontrarUsuario(String nome){

        List<Usuario> usuarios = retornaUsuarios();

        for (Usuario u: usuarios) {
            if(u.getUser().equals(nome)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Usuario> retornaUsuarios(){String [] colunas = {tabela_id, Usuario_user, Usuario_pass};

        Cursor cursor = getWritableDatabase().query(tabela_usuario, colunas, null, null, null,
                null, null, null);

        ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();

        while (cursor.moveToNext()){
            Usuario u = new Usuario();

            u.setUsuario_id(cursor.getInt(0));
            u.setUser(cursor.getString(1));
            u.setPass(cursor.getString(2));

            listUsuario.add(u);
        }

        return listUsuario;

    }

    public int logar(String user, String pass){

        for (Usuario u : retornaUsuarios()) {
            if((u.getUser().equals(user))&&(u.getPass().equals(pass))){
                return u.getUsuario_id();
            }
        }
        return -1;
    }

    public Usuario buscarPorId(int id){
        for (Usuario u : retornaUsuarios()) {
            if(u.getUsuario_id()==id){
                return u;
            }
        }
        return null;
    }

    public List<ItemLista> retornaListaItens(int idUser){String [] colunas = {tabela_id,
            ItemLista_estado, ItemLista_livro_id, ItemLista_usuario_id};

        String where = ItemLista_usuario_id + "=" + idUser;

        Cursor cursor = getReadableDatabase().query(tabela_itemlista, colunas, where, null, null,
                null, null, null);

        ArrayList<ItemLista> listItens = new ArrayList<ItemLista>();

        while (cursor.moveToNext()){
            ItemLista il = new ItemLista();

            il.setItemlista_id(cursor.getInt(0));
            il.setEstado(cursor.getString(1));
            il.setLivro_id(cursor.getInt(2));
            il.setUsuario_id(cursor.getInt(3));

            listItens.add(il);
        }

        return listItens;

    }


    public void salvarGenero(Genero genero){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Genero_Descricao, genero.getDescricao());

        db.insert(tabela_genero, null, cv);
        db.close();
    }

    public ArrayList<Genero> retornaGeneros(){String [] colunas = {tabela_id, Genero_Descricao};

        Cursor cursor = getWritableDatabase().query(tabela_genero, colunas, null, null, null,
                null, null, null);

        ArrayList<Genero> listGenero = new ArrayList<Genero>();

        while (cursor.moveToNext()){
            Genero g = new Genero();

            g.setGenero_id(cursor.getInt(0));
            g.setDescricao(cursor.getString(1));

            listGenero.add(g);
        }

        return listGenero;

    }

    public Genero retornaGeneroPorId(int idGenero){String [] colunas = {tabela_id, Genero_Descricao};

        String where = tabela_id + "=" + idGenero;

        Cursor cursor = getReadableDatabase().query(tabela_genero, colunas, where, null, null,
                null, null, null);

        ArrayList<Genero> listGenero = new ArrayList<Genero>();

        while (cursor.moveToNext()){
            Genero g = new Genero();

            g.setGenero_id(cursor.getInt(0));
            g.setDescricao(cursor.getString(1));

            listGenero.add(g);
        }

        return listGenero.get(0);

    }

    public void salvarLivro(Livro livro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Livro_nome, livro.getLivro_nome());
        cv.put(Livro_genero_id, livro.getGenero_Id());

        db.insert(tabela_livro, null, cv);
        db.close();
    }

    public ArrayList<Livro> retornaLivros(){String [] colunas = {tabela_id, Livro_nome, Livro_genero_id};

        Cursor cursor = getWritableDatabase().query(tabela_livro, colunas, null, null, null,
                null, null, null);

        ArrayList<Livro> listLivros = new ArrayList<Livro>();

        while (cursor.moveToNext()){
            Livro l = new Livro();

            l.setLivro_id(cursor.getInt(0));
            l.setLivro_nome(cursor.getString(1));
            l.setGenero_Id(cursor.getInt(2));

            listLivros.add(l);
        }
        if(listLivros.size()==0){
            return null;
        }else{
            return listLivros;
        }
    }

    public Livro encontrarLivroPorId(int id){
        Livro livroEncontrado = new Livro();
        for (Livro l: retornaLivros()) {
            if(l.getLivro_id()==id){
                livroEncontrado = l;
                return livroEncontrado;
            }
        }
        return null;
    }


    public ItemLista encontrarLivroRetornaItem(Livro l, int idUser){

        List<ItemLista> il = retornaListaItens(idUser);

        for (ItemLista item_lista: il) {
            if(item_lista.getLivro_id()==l.getLivro_id()){
                return item_lista;
           }
        }
        return null;
    }

    public void adicionarItem(ItemLista itl, int idUser){
        if((achaItemUsuario(itl.getLivro_id(), idUser)==-1)){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(ItemLista_estado, itl.getEstado());
            cv.put(ItemLista_livro_id, itl.getLivro_id());
            cv.put(ItemLista_usuario_id, idUser);

            db.insert(tabela_itemlista, null, cv);
            db.close();
        }else{
            List<ItemLista> itens = retornaListaItens(idUser);
            for (ItemLista item: itens) {
                if(item.getLivro_id()==itl.getLivro_id()){
                    ContentValues cv = new ContentValues();
                    SQLiteDatabase db = this.getWritableDatabase();
                    cv.put(ItemLista_estado, itl.getEstado());
                    db.update(tabela_itemlista, cv,tabela_id + "=" + item.getItemlista_id(), null);
                }
            }
        }
    }

    public void removerItem(Livro l, int idUser){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ItemLista_livro_id + "=" + l.getLivro_id();
        db.delete(tabela_itemlista, where, null);

        db.close();
    }

    public int achaItemUsuario(int livro_id, int idUser){

        List<ItemLista> il = retornaListaItens(idUser);

       for (int p = 0; p < il.size() ; p++) {
            if(il.get(p).getLivro_id()==livro_id){
               return p;
            }
        }
        return -1;
    }


}
