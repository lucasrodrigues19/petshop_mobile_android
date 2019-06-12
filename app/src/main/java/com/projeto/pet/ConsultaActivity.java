package com.projeto.pet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ConsultaActivity extends AppCompatActivity {
    LinearLayout grupoEntrada;
    public EditText editor;
    Button enviar, voltar;
    ListView lista;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String voltarDeleta;

    public List<Clientes> listCAPI;
    ArrayList<String> listaNomeClientes = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String acao;
    String usuMc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        //chamar os objetos
        grupoEntrada = (LinearLayout) findViewById(R.id.grupoEntrada);
        radioGroup = (RadioGroup) findViewById(R.id.opc);
        enviar = (Button) findViewById(R.id.buttonEnviar);
        voltar = (Button) findViewById(R.id.buttonVoltarCadstro);
        lista = (ListView) findViewById(R.id.ListViewCliente);
        editor = (EditText) findViewById(R.id.editTextEntrada);
        lista.setVisibility(GONE);
        editor.setEnabled(false);
        editor.setHint("pesquise 1º pelo usuario!");
        Intent iCs = getIntent();

        if (iCs != null) {
            Bundle parametroConsulta = iCs.getExtras();
            if (parametroConsulta != null) {
                String MusulC = parametroConsulta.getString("usuConsu");
                usuMc = MusulC.toString();

            }
        }
        //criar acao para o potao
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //lista ficou visivel
                //verificar execoes na opcao
                try {
                    if (acao.equals("id")) {
                        //listCAPI ira retornar o retorno da funcao HttpService
                        listCAPI = new HttpService("id", editor.getText().toString()).execute().get();
                    } else if (acao.equals("usuario")) {
                        listCAPI = new HttpService("usuario", editor.getText().toString()).execute().get();
                        editor.setHint("");
                    } else {
                        Toast.makeText(getApplicationContext(), "informe a opcao", Toast.LENGTH_LONG).show();
                    }


                    editor.setText("");

                    listaNomeClientes.clear();
                    //alimentando a lista(list)
                    for (Clientes client : listCAPI) {
                        //add elementos a arraylist
                        listaNomeClientes.add("-------------------------------");
                        listaNomeClientes.add("DADOS DO(A) CLIENTE");
                        listaNomeClientes.add("ID: " + Integer.toString(client.getId()) + "\n" + "Nome: " + client.getNome() + "\n" + "Celular/fixo: " +client.getTelefone() + "\n" + "Endereco: " + client.getEndereco()
                                + "\n" + "usuario: " + client.getUsuario() + "\n" + "DADOS DO ANIMAL" + "\n" + "Nome: " + client.getNome_animal() + "\n" + "Especie: " + client.getEspecie() + "\n" + "Raca: " + client.getRaca());
                        listaNomeClientes.add("-------------------------------");

                        voltarDeleta = Integer.toString(client.getId());

                        //enviar os dados para a tela de Update
                        Intent telaUp = new Intent(ConsultaActivity.this, UpdateActivity.class);
                        //criando um pacote de dados
                        Bundle parametroUp = new Bundle();


                        //atribuir valores
                        parametroUp.putString("idUp", Integer.toString(client.getId()));
                        parametroUp.putString("nomeUp", client.getNome().toString());
                        parametroUp.putString("usuarioUp", client.getUsuario().toString());
                        parametroUp.putString("celularUp", client.getTelefone().toString());
                        parametroUp.putString("enderecoUp", client.getEndereco().toString());
                        parametroUp.putString("nome_aniUp", client.getNome_animal().toString());
                        parametroUp.putString("especieUp", client.getEspecie().toString());
                        parametroUp.putString("racaUp", client.getRaca().toString());

                        //atribuindo valores a telaUp
                        telaUp.putExtras(parametroUp);
                        startActivity(telaUp);


                    }
                    //estrutura do adapter
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaNomeClientes);
                    //configurando a lista junto o adapter
                    lista.setAdapter(adapter);
                    verific();

                } catch (ExecutionException e) {
                    Toast.makeText(getApplicationContext(), "falha Tente novamente: " , Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "falha Interruption! tente novamnte: " , Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "falha Ouve uma excessao: ", Toast.LENGTH_LONG).show();

                }


            }

            public void verific() {
                if (!listCAPI.isEmpty()) {
                    Toast.makeText(getApplicationContext(), " dados recuperados com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), " erro ao consultar cadastro!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaMD = new Intent(ConsultaActivity.this, MenuActivity.class);
                //criando um pacote de dados
                Bundle parametroMD = new Bundle();

                parametroMD.putString("idDel", voltarDeleta);
                telaMD.putExtras(parametroMD);

                startActivity(telaMD);
            }
        });

    }

    //checando as opcoes
    public void checar(View v) {
        int rId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(rId);
        acao = "";
        if (!listaNomeClientes.isEmpty()) {
            listaNomeClientes.clear();
            adapter.notifyDataSetChanged();
        }
        if (radioButton.getText().equals("id") || radioButton.getText().equals("usuario")) {
            grupoEntrada.setVisibility(VISIBLE);
            if (radioButton.getText().equals("id")) {
                acao = "id";
                editor.setEnabled(false);
                editor.setText(voltarDeleta);
            } else if (radioButton.getText().equals("usuario")) {
                editor.setEnabled(false);
                acao = "usuario";
                editor.setText(usuMc);

            }
        } else {
            grupoEntrada.setVisibility(GONE);
        }
    }

}