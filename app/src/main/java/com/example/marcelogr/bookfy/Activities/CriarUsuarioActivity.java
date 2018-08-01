package com.example.marcelogr.bookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.marcelogr.bookfy.DAL.UsuarioDAO;
import com.example.marcelogr.bookfy.DBHelper;
import com.example.marcelogr.bookfy.Models.Usuario;
import com.example.marcelogr.bookfy.R;

public class CriarUsuarioActivity extends AppCompatActivity {

    TextView user;
    TextView pass;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_criar_usuario);
        user = findViewById(R.id.userCriar);
        pass = findViewById(R.id.passwordCriar);
        cadastrar = findViewById(R.id.btnCadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario();
                //UsuarioDAO ud = new UsuarioDAO();
                u.setUser(user.getText().toString());
                u.setPass(pass.getText().toString());
                //ud.salvar(u);
                DBHelper dbh = new DBHelper(CriarUsuarioActivity.this);
                dbh.salvarUsuario(u);
                Intent i = new Intent(CriarUsuarioActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
