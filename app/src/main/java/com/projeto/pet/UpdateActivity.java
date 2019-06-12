package com.projeto.pet;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    Button btVoltarUpdate, btUp;
    EditText novonome, novotelefone, novoendereco, novousuario, novonomeanimal, novoespecie, novoraca, editTextid;
    String getId, getNovonome, getNovotelefone, getNovoEndereco, getNovousuario, getNovonomeanimal, getNovoRaca, getNovoEspecie;
    String UpCid, UpCnome, UpCcelular, UpCend, UpCusua, UpCnome_a, UpCespe, UpCraca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btVoltarUpdate = findViewById(R.id.buttonVoltarUpdate);
        btUp = findViewById(R.id.buttonAtualizar);

        editTextid = findViewById(R.id.editTextId);
        novonome = findViewById(R.id.editTextNovoNome);
        novotelefone = findViewById(R.id.editTextNovoTelefone);
        novoendereco = findViewById(R.id.editTextNovoEndereco);
        novousuario = findViewById(R.id.editTextNovoUsuario);
        novonomeanimal = findViewById(R.id.editTextNovoNome_animal);
        novoespecie = findViewById(R.id.editTextNovaEspecie);
        novoraca = findViewById(R.id.editTextNovaRaca);

        Intent itUpC = getIntent();

        if (itUpC != null) {
            Bundle parametroUp = itUpC.getExtras();
            if (parametroUp != null) {
                UpCid = parametroUp.getString("idUp");
                UpCnome = parametroUp.getString("nomeUp");
                UpCcelular = parametroUp.getString("celularUp");
                UpCend = parametroUp.getString("enderecoUp");
                UpCusua = parametroUp.getString("usuarioUp");
                UpCnome_a = parametroUp.getString("nome_aniUp");
                UpCespe = parametroUp.getString("especieUp");
                UpCraca = parametroUp.getString("racaUp");

                editTextid.setEnabled(false);
                editTextid.setText(UpCid);
                novonome.setText(UpCnome);
                novousuario.setText(UpCusua);
                novoendereco.setText(UpCend);
                novotelefone.setText(UpCcelular);
                novonomeanimal.setText(UpCnome_a);
                novoespecie.setText(UpCespe);
                novoraca.setText(UpCraca);


            }


        }


        btVoltarUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        btUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getId = editTextid.getText().toString();
                getNovonome = novonome.getText().toString();
                getNovotelefone = novotelefone.getText().toString();
                getNovoEndereco = novoendereco.getText().toString();
                getNovousuario = novousuario.getText().toString();
                getNovonomeanimal = novonomeanimal.getText().toString();
                getNovoEspecie = novoespecie.getText().toString();
                getNovoRaca = novoraca.getText().toString();

                UpdateDados(getId, getNovonome, getNovotelefone, getNovoEndereco, getNovousuario, getNovonomeanimal, getNovoEspecie, getNovoRaca);

            }
        });

    }

    public void UpdateDados(final String nid, final String nnome, final String ntel, final String nend, final String nusuario, final String nnomeani, final String nespe, final String nraca) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {


                String up_url = "https://testesistemasmoveis.000webhostapp.com/query_update.php";

                try {
                    String idH = nid;
                    String nomeH = nnome;
                    String endH = nend;
                    String telH = ntel;
                    String usuarioH = nusuario;
                    String nomeaniH = nnomeani;
                    String racaH = nraca;
                    String espeH = nespe;
                    URL url = new URL(up_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(idH, "UTF-8") + "&"
                            + URLEncoder.encode("nome", "UTF-8") + "=" + URLEncoder.encode(nomeH, "UTF-8") + "&"
                            + URLEncoder.encode("telefone", "UTF-8") + "=" + URLEncoder.encode(telH, "UTF-8") + "&"
                            + URLEncoder.encode("endereco", "UTF-8") + "=" + URLEncoder.encode(endH, "UTF-8") + "&"
                            + URLEncoder.encode("usuario", "UTF-8") + "=" + URLEncoder.encode(usuarioH, "UTF-8") + "&"
                            + URLEncoder.encode("nome_animal", "UTF-8") + "=" + URLEncoder.encode(nomeaniH, "UTF-8") + "&"
                            + URLEncoder.encode("especie", "UTF-8") + "=" + URLEncoder.encode(espeH, "UTF-8") + "&"
                            + URLEncoder.encode("raca", "UTF-8") + "=" + URLEncoder.encode(racaH, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;


                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }
                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                if (result.equals("dados alterados com sucesso")) {
                    // aqui o redirecionamento para a activity desejada
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "erro verifique os campos", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected void onPreExecute() {
                Toast.makeText(getApplicationContext(), "atualizando... ", Toast.LENGTH_SHORT).show();

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nid,nnome,ntel,nend,nusuario,nnomeani,nespe,nraca);
    }
}
