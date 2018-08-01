package com.example.marcelogr.bookfy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcelogr.bookfy.DAL.LivroDAO;
import com.example.marcelogr.bookfy.DAL.UsuarioDAO;
import com.example.marcelogr.bookfy.Models.Genero;
import com.example.marcelogr.bookfy.Models.Livro;
import com.example.marcelogr.bookfy.Models.Usuario;
import com.example.marcelogr.bookfy.R;
import com.example.marcelogr.bookfy.DBHelper;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button logar;
    TextView criar;
    EditText userInput;
    EditText passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Inicializar();

        userInput = findViewById(R.id.user);
        passInput = findViewById(R.id.password);
        logar = findViewById(R.id.email_sign_in_button);
        criar = findViewById(R.id.TextViewCriar);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultado = TentarLogin(userInput.getText().toString(), passInput.getText().toString());
                if(resultado!=-1){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("id", resultado);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CriarUsuarioActivity.class);
                startActivity(i);
            }
        });
    }

    public void Inicializar(){

        //this.deleteDatabase("meuDB");

        DBHelper dbh = new DBHelper(this);
        if(!(dbh.encontrarUsuario("user"))){
            Genero gen = new Genero();
            gen.setDescricao("Terror");
            dbh.salvarGenero(gen);

            gen.setDescricao("Aventura");
            dbh.salvarGenero(gen);

            gen.setDescricao("Suspense");
            dbh.salvarGenero(gen);

            gen.setDescricao("Comédia");
            dbh.salvarGenero(gen);

            Livro liv = new Livro();
            liv.setLivro_nome("Exorcista");
            liv.setGenero_Id(1);
            dbh.salvarLivro(liv);

            liv.setLivro_nome("Indiana Jones");
            liv.setGenero_Id(2);
            dbh.salvarLivro(liv);

            liv.setLivro_nome("Ghost In The Shell");
            liv.setGenero_Id(3);
            dbh.salvarLivro(liv);

            liv.setLivro_nome("Livro De Comédia");
            liv.setGenero_Id(4);
            dbh.salvarLivro(liv);

            Usuario ud = new Usuario();
            ud.setUser("user");
            ud.setPass("pass");
            dbh.salvarUsuario(ud);
        }

    }

    public int TentarLogin(String user, String pass){
        //UsuarioDAO ud = new UsuarioDAO();
        //return ud.logar(user, pass);

        DBHelper dbh = new DBHelper(this);
        return dbh.logar(user, pass);
    }
}
