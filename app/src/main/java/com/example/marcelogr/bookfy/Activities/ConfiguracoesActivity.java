package com.example.marcelogr.bookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcelogr.bookfy.DAL.UsuarioDAO;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.Usuario;
import com.example.marcelogr.bookfy.R;

public class ConfiguracoesActivity extends AppCompatActivity {

    EditText senhaAntiga;
    EditText senhaNova;
    EditText senhaNovaConfirma;
    Button salvar;
    int idPassar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);


        senhaAntiga = findViewById(R.id.etSenhaAntiga);
        senhaNova = findViewById(R.id.etSenhaNova);
        senhaNovaConfirma = findViewById(R.id.etConfirmaSenhaNova);
        salvar = findViewById(R.id.btnSalvarConfig);

        Intent i = getIntent();

        final int idUser = (int) i.getSerializableExtra("id");
        idPassar = idUser;
        final DBHelper dbh = new DBHelper(ConfiguracoesActivity.this);
        final Usuario u = dbh.buscarPorId(idUser);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!senhaAntiga.getText().toString().equals(u.getPass())){
                    Toast.makeText(ConfiguracoesActivity.this, "Senha antiga incorreta", Toast.LENGTH_SHORT).show();
                }else{
                    if(!senhaNova.getText().toString().equals(senhaNovaConfirma.getText().toString())){
                        Toast.makeText(ConfiguracoesActivity.this,
                                "Senha nova e sua confirmação não são iguais", Toast.LENGTH_SHORT).show();
                    }else{
                        if(MainActivity.instanceMain!=null){
                            MainActivity.instanceMain.finish();
                            MainActivity.instanceMain = null;
                        }
                        u.setPass(senhaNovaConfirma.getText().toString());
                        dbh.salvarUsuario(u);

                        Intent i = new Intent(ConfiguracoesActivity.this, MainActivity.class);
                        i.putExtra("id", idUser);
                        startActivity(i);
                        finish();

                    }
                }
            }
        });

    }

//    @Override
//    public void onBackPressed()
//    {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("id", idPassar);
//        startActivity(intent);
//        finish();
//    }
}
