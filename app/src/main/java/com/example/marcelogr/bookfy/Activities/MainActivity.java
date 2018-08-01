package com.example.marcelogr.bookfy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Fragments.OneFragment;
import com.example.marcelogr.bookfy.Models.ItemLista;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.R;
import com.example.marcelogr.bookfy.Fragments.TwoFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static MainActivity instanceMain;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        instanceMain = this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent i = getIntent();
        idUser = (int) i.getSerializableExtra("id");
        if(i.getAction()!=null){
            TwoFragment twoFragment = new TwoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, twoFragment).commit();
            setTitle("Minha Biblioteca");
            navigationView.setCheckedItem(R.id.nav_minhaBib);
        }else{
            OneFragment oneFragment = new OneFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, oneFragment).commit();
            setTitle("Biblioteca Geral");
            navigationView.setCheckedItem(R.id.nav_bibGeral);

        }

        View hview = navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hview.findViewById(R.id.userNome);
        //UsuarioDAO ud = new UsuarioDAO();
        //nav_user.setText((ud.buscarPorId(idUser).getUser()));

        DBHelper dbh = new DBHelper(this);
        nav_user.setText((dbh.buscarPorId(idUser).getUser()));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_configuracoes) {
            Intent i = new Intent(MainActivity.this, ConfiguracoesActivity.class);
            i.putExtra("id", idUser);
            startActivity(i);
            //finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_minhaBib) {
            TwoFragment twoFragment = new TwoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, twoFragment).commit();
            setTitle("Minha Biblioteca");
        } else if (id == R.id.nav_bibGeral) {
            OneFragment oneFragment = new OneFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, oneFragment).commit();
            setTitle("Biblioteca Geral");

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");

            String livrosConcatenados = "";
            //UsuarioDAO u = new UsuarioDAO();
            //List<ItemLista> il = u.retornaLista(idUser);
            DBHelper dbh = new DBHelper(this);
            List<ItemLista> il = dbh.retornaListaItens(idUser);
            if(il!=null){
                livrosConcatenados = "COMPARTILHADO DE BOOKFY! - Lista: ";
                for (ItemLista item_lista: il) {
                    //LivroDAO ld = new LivroDAO();
                    //Livro l = ld.encontrarLivroPorId(item_lista.getLivro_id());
                    Livro l = dbh.encontrarLivroPorId(item_lista.getLivro_id());
                    livrosConcatenados += l.getLivro_nome();
                    if(!(item_lista==il.get(il.size()-1))){
                        livrosConcatenados += " + ";
                    }
                }
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, livrosConcatenados);
                //sharingIntent.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int retornaId() {
        return this.idUser;
    }
}
