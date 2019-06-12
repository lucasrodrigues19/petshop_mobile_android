package com.projeto.pet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private Button btconsultar, btdel, btvoltarMenu;
    private TextView verS;
    String enviarUsuC, enviarIdDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btvoltarMenu = findViewById(R.id.buttonVoltarMenu);
        btconsultar = findViewById(R.id.buttonConsultar);
        verS = findViewById(R.id.textViewVerS);

        btdel = findViewById(R.id.buttonAltDel);

        Intent iUpC = getIntent();

        if (iUpC != null) {
            Bundle parametroMenu = iUpC.getExtras();
            if (parametroMenu != null) {
                String Musua = parametroMenu.getString("usuMenu");
                verS.setText(Musua);
                String MidDel = parametroMenu.getString("idDel");

                enviarIdDel = MidDel;
                enviarUsuC = Musua;
            }
        }

        btvoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });

        btconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Consu = new Intent(MenuActivity.this, ConsultaActivity.class);
                Bundle Coem = new Bundle();
                String Conmail = enviarUsuC;
                Coem.putString("usuConsu", Conmail);

                Consu.putExtras(Coem);
                startActivity(Consu);
            }


        });


        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Dele = new Intent(MenuActivity.this, DeleteActivity.class);
                Bundle deID = new Bundle();
                String DELidenviar = enviarIdDel;
                deID.putString("idDeletar", DELidenviar);

                Dele.putExtras(deID);
                startActivity(Dele);
            }



        });


    }

}
