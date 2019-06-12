package com.projeto.pet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btlogin, btcadastro;
    EditText emailEt, telefoneEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEt = findViewById(R.id.editTextUsuario);
        telefoneEt = findViewById(R.id.editTextSenha);

        btcadastro = findViewById(R.id.buttonCadastro);
        btlogin = findViewById(R.id.buttonLogin);
        btcadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btcadastrarTela();
            }

            public void btcadastrarTela() {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }

        });

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuariomain= emailEt.getText().toString();
                String telefone = telefoneEt.getText().toString();
                String type = "login";
                LoginServer loginServer = new LoginServer(MainActivity.this);
                loginServer.execute(type, usuariomain, telefone);


            }
        });


    }


}