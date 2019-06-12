package com.projeto.pet;

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

public class CadastroActivity extends AppCompatActivity {


    private EditText nome, usuario, telefone, endereco, nome_animal, especie, raca;
    private Button btenviar, btVoltarCadastro;
    private String TempNome, TempUsua, TempTelefone, TempEndereco, TempNome_a, TempEspecie, TempRaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.editTextNome);
        usuario = (EditText) findViewById(R.id.editTextUsuarioC);
        telefone = (EditText) findViewById(R.id.editTextTelefone);
        endereco = (EditText) findViewById(R.id.editTextEndereco);
        nome_animal = (EditText) findViewById(R.id.editTextNome_animal);
        especie = (EditText) findViewById(R.id.editTextEspecie);
        raca = (EditText) findViewById(R.id.editTextRaca);
        btenviar = (Button) findViewById(R.id.button);
        btVoltarCadastro = (Button) findViewById(R.id.buttonVoltarCadstro);


        /// acao do notao
        btenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData(TempNome, TempUsua, TempTelefone, TempEndereco, TempNome_a, TempEspecie, TempRaca);

            }
        });

        //botao voltar

        btVoltarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void GetData() {

        TempNome = nome.getText().toString();
        TempEndereco = endereco.getText().toString();
        TempUsua = usuario.getText().toString();
        TempTelefone = telefone.getText().toString();

        TempNome_a = nome_animal.getText().toString();
        TempRaca = raca.getText().toString();
        TempEspecie = especie.getText().toString();

    }

    public void InsertData(final String nome, final String usuarioo, final String telefone, final String endereco, final String nome_an, final String especie, final String raca) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {



                String up_url = "https://testesistemasmoveis.000webhostapp.com/insert.php";

                try {
                    String NameHolder = nome;
                    String UsuarioHolder = usuarioo;
                    String TelefoneHolder = telefone;
                    String EnderecoHolder = endereco;
                    String Nome_aHolder = nome_an;
                    String EspecieHolder = especie;
                    String RacaHolder = raca;
                    URL url = new URL(up_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("nome", "UTF-8") + "=" + URLEncoder.encode(NameHolder, "UTF-8") + "&"
                            + URLEncoder.encode("telefone", "UTF-8") + "=" + URLEncoder.encode(TelefoneHolder, "UTF-8") + "&"
                            + URLEncoder.encode("endereco", "UTF-8") + "=" + URLEncoder.encode(EnderecoHolder, "UTF-8") + "&"
                            + URLEncoder.encode("usuario", "UTF-8") + "=" + URLEncoder.encode(UsuarioHolder, "UTF-8") + "&"
                            + URLEncoder.encode("nome_animal", "UTF-8") + "=" + URLEncoder.encode(Nome_aHolder, "UTF-8") + "&"
                            + URLEncoder.encode("especie", "UTF-8") + "=" + URLEncoder.encode(EspecieHolder, "UTF-8") + "&"
                            + URLEncoder.encode("raca", "UTF-8") + "=" + URLEncoder.encode(RacaHolder, "UTF-8");
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
                if (result.equals("dados enviados com sucesso")) {

                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected void onPreExecute() {
                Toast.makeText(getApplicationContext(), "cadastrando...", Toast.LENGTH_SHORT).show();

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nome,usuarioo,telefone,endereco,nome_an,especie,raca);
    }
}



