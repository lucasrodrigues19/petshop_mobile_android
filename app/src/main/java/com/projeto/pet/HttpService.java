package com.projeto.pet;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, List<Clientes>> {

    private String stringUrl;

    //metodo construtor
    public HttpService() {
        this.stringUrl = "https://testesistemasmoveis.000webhostapp.com/query_todos.php";
        Log.d("https://testesistemasmoveis.000webhostapp.com/", this.stringUrl);
    }

    public HttpService(String pesquisa, String p) {
        if (pesquisa.equals("id")) {
            this.stringUrl = "https://testesistemasmoveis.000webhostapp.com/query_id.php?id=" + p;
        } else if (pesquisa.equals("usuario")) {
            this.stringUrl = "https://testesistemasmoveis.000webhostapp.com/query_usuario.php?usuario="+p;
        }
    }

    @Override
    protected List<Clientes> doInBackground(Void... voids) {
        StringBuilder res = new StringBuilder();
        try {
            URL url = new URL(stringUrl);
            //abrir a conexao
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type","application/json");//web sevice devolva um json
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset","UTF-8");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();


            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()){
                res.append(scanner.nextLine());
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type cType = new TypeToken<ArrayList<Clientes>>(){}.getType();
        List<Clientes> lClients;
        lClients = new Gson().fromJson(res.toString(),cType);
        return lClients;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Clientes> clientes) {
        super.onPostExecute(clientes);
    }
}
