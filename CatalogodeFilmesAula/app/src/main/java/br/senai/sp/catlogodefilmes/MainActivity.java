package br.senai.sp.catlogodefilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listaFilmes;
    private Button btnNovoFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovoFilme = findViewById(R.id.bt_novo_filme);

        // *** MATRIZ DE FILMES QUE SERÃO EXIBIDOS NO ListView
        String[] arrayListaFilmes = {"Como treinar o seu dragão 3",
                "Creed 2", "Homen-Aranha no aranhaverso",
                "Vidro", "Minha vida em Marte", "Como treinar o seu dragão 3",
                "Creed 2", "Homen-Aranha no aranhaverso",
                "Vidro", "Minha vida em Marte", "Como treinar o seu dragão 3",
                "Creed 2", "Homen-Aranha no aranhaverso",
                "Vidro", "Minha vida em Marte"};

        // *** Associo o objeto ListView do Java à View ListView do layout xml
        listaFilmes = findViewById(R.id.list_filmes);

        // *** Definimos um adapter para carregar os dados da matriz na ListView
        // *** utilizando um layout pronto (simple_list_item_1)
        ArrayAdapter<String> listaFilmesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListaFilmes);

        // *** Injetamos o adapter no objeto ListView
        listaFilmes.setAdapter(listaFilmesAdapter);

        // *** Ação do botão novo
        btnNovoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroFilme = new Intent(MainActivity.this, CadastroFilmeActivity.class);
                startActivity(cadastroFilme);
            }
        });

        Toast.makeText(this, "Estou no método ON-CREATE", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Estou no método ON-RESUME", Toast.LENGTH_LONG).show();
    }

}
