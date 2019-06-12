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

public class DeleteActivity extends AppCompatActivity {
Button btVoltarDel, btDel;
EditText deleId;
String getIdDel;
    String idDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        btVoltarDel = findViewById(R.id.buttonVoltarDelete);
        btDel = findViewById(R.id.buttonDeletarCadastro);
        btVoltarDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleId=findViewById(R.id.editTextidRemover);

        Intent iDel = getIntent();

        if (iDel != null) {
            Bundle parametroMenu = iDel.getExtras();
            if (parametroMenu != null) {

                idDel = parametroMenu.getString("idDeletar");

            }
        }
        deleId.setText(idDel);
        deleId.setEnabled(false);
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDadosDel();
                delDados(getIdDel);



            }
        });



        }
    public void getDadosDel(){
        getIdDel = deleId.getText().toString();
    }

    public void delDados(final String iddel){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {



                String up_url = "https://testesistemasmoveis.000webhostapp.com/query_deleteid.php";

                try {
                    String DeletarId = iddel;

                    URL url = new URL(up_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(DeletarId, "UTF-8");

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
                if (result.equals("dados deletados com sucesso!")) {
                    // aqui o redirecionamento para a activity desejada
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DeleteActivity.this, MainActivity.class));


                } else {
                    Toast.makeText(getApplicationContext(),  result, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected void onPreExecute() {
                Toast.makeText(getApplicationContext(), "deletando... ", Toast.LENGTH_SHORT).show();

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(iddel);
    }
}
