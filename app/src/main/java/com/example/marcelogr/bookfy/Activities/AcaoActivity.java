package com.example.marcelogr.bookfy.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcelogr.bookfy.DAL.GeneroDAO;
import com.example.marcelogr.bookfy.DAL.LivroDAO;
import com.example.marcelogr.bookfy.DAL.UsuarioDAO;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.ItemLista;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.R;

public class AcaoActivity extends AppCompatActivity {

    TextView nome;
    TextView genero;
    Spinner spinner;
    Button acao;
    Button deletar;
    int idPassar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao);

        spinner = (Spinner) findViewById(R.id.spinnerSituacao);
        acao = findViewById(R.id.btnAcao);
        deletar = findViewById(R.id.btnDeletar);
        nome = findViewById(R.id.nome);
        genero = findViewById(R.id.genero);

        final Intent i = getIntent();
        Bundle bundle = i.getExtras();
        final int id = (int) bundle.getInt("id");
        idPassar = id;
        final ItemLista il = (ItemLista) bundle.getSerializable("itemObjeto");
        final Livro l = (Livro) bundle.getSerializable("livroObjeto");
        DBHelper dbh = new DBHelper(this);
        if(i.getAction()!=null){
            Livro livroPesquisado = dbh.encontrarLivroPorId(il.getLivro_id());
            nome.setText(livroPesquisado.getLivro_nome().toString());
            GeneroDAO gd = new GeneroDAO();
            genero.setText(dbh.retornaGeneroPorId(livroPesquisado.getGenero_Id()).getDescricao().toString());
            if(il.getEstado().startsWith("L")){
                spinner.setSelection(1);
            }else{
                if(il.getEstado().startsWith("P")){
                    spinner.setSelection(2);
                }else{
                    spinner.setSelection(3);
                }
            }
            acao.setText("Editar");
            deletar.setEnabled(true);
            deletar.setTextColor(this.getResources().getColor(R.color.White));
            deletar.setBackgroundColor(this.getResources().getColor(R.color.Red));

        }else{
            nome.setText(l.getLivro_nome().toString());
            genero.setText(dbh.retornaGeneroPorId(l.getGenero_Id()).getDescricao().toString());
            acao.setText("Adicionar");
        }

        acao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItemPosition()==0){
                    Toast.makeText(AcaoActivity.this, "SELECIONE UMA OPÇÃO", Toast.LENGTH_SHORT).show();
                }else{

                    //UsuarioDAO ud = new UsuarioDAO();
                    DBHelper dbh = new DBHelper(AcaoActivity.this);
                    String escolha = spinner.getSelectedItem().toString();

                    ItemLista itl = new ItemLista();
                    if((i.getAction()!=null)){
                        itl.setLivro_id(il.getLivro_id());
                        itl.setEstado(escolha);
                        notificacao(dbh.encontrarLivroPorId(il.getLivro_id()).getLivro_nome(),false);
                    }else{
                        itl.setLivro_id(l.getLivro_id());
                        itl.setEstado(escolha);
                        notificacao(dbh.encontrarLivroPorId(itl.getLivro_id()).getLivro_nome(),true);
                    }
                    if(MainActivity.instanceMain!=null){
                        MainActivity.instanceMain.finish();
                        MainActivity.instanceMain = null;
                    }
                    dbh.adicionarItem(itl, id);

                    Intent i = new Intent(AcaoActivity.this, MainActivity.class);
                    i.setAction("pessoal");
                    i.putExtra("id", id);
                    startActivity(i);
                    finish();

                }
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.instanceMain.finish();
                MainActivity.instanceMain = null;
                DBHelper dbh = new DBHelper(AcaoActivity.this);
                Livro livroPesquisado = dbh.encontrarLivroPorId(il.getLivro_id());
                dbh.removerItem(livroPesquisado, id);
                Intent i = new Intent(AcaoActivity.this, MainActivity.class);
                i.setAction("pessoal");
                i.putExtra("id", id);
                startActivity(i);
                finish();

            }
        });

    }

    public void notificacao(String nomeLivro, Boolean acao){
        String acaoString;
        if(acao){
            acaoString = "cadastrado";
        }else{
            acaoString = "editado";
        }

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setContentTitle("Notificação do Boofky")
                .setContentText(nomeLivro + " foi " + acaoString + " com sucesso!" );

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

}
