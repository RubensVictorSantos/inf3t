package br.senai.sp.catalogodefilmes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.adapter.FilmesAdapter;
import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class MainActivity extends AppCompatActivity {

    private ListView listaFilmes;
    private Button btnNovofilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associo o objeto ListView do java a view ListView do layout xml
        listaFilmes = findViewById(R.id.list_filmes);

        btnNovofilme = findViewById(R.id.btn_novo_filme);


      //acao do botao novo
      btnNovofilme.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //tenho a intençao da mainActivity chamar a CadastroFilmeActivity
              Intent cadastroFilme = new Intent(MainActivity.this,CadastroFilmeActivity.class);
              //ir para activity "cadastroFilme no clique do botao"
              startActivity(cadastroFilme);
          }
      });

      //menu de contexto = onde estou
      //*** DEFINICAO DE UM MENU DE CONTEXTO PARA  A LISTVIEW(listadefilmes)
        registerForContextMenu(listaFilmes);

        //intent = intencao
        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//Ele retorna AdapterView,View,posição e o id
              Filme filme = (Filme) listaFilmes.getItemAtPosition(position);

              Intent cadastro = new Intent(MainActivity.this, CadastroFilmeActivity.class);
              //tudo na intent tem que ser serializado, para que possam ser remontados em outra activity
              cadastro.putExtra("filme", filme);

              startActivity(cadastro);

             Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //vou inflar este menu com o menu_context_lista_filmes
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_lista_filmes, menu);

       /* MenuItem deletar = menu.add("Excluir");
        MenuItem editar = menu.add("Editar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "Deletar", Toast.LENGTH_LONG).show();
                return false;
            }
        });*/

        //menu, view, informacoes do menu
        super.onCreateContextMenu(menu, v, menuInfo);
    }



    //me diz qual foi o item selecionado do menu
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(this, "cliquei", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);

    }

    //final = garante que a variavel nao mudará de valor, que em nenhum outro momento esse filme será outra coisa

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        new AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Tem certeza que deseja deletar esse filme?")
                .setPositiveButton("sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FilmeDAO dao = new FilmeDAO(MainActivity.this);
                                //dao.excluir();

                                //pegar a posicao do item clicado numa listview
                                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                                Filme filme = (Filme) listaFilmes.getItemAtPosition(info.position);

                                Toast.makeText(MainActivity.this, filme.getTitulo(), Toast.LENGTH_LONG).show();

                                dao.excluir(filme);

                                dao.close();

                                carregarLista();
                            }
                        })
                .setNegativeButton("não", null)
                .show();

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        carregarLista();



        super.onResume();
    }

    private void carregarLista(){
        //MATRIZ DE FILMES QUE SERÃO EXIBIDOS NO listView

        FilmeDAO dao = new FilmeDAO(this);
        List<Filme> filmes = dao.getFilmes();
        dao.close();
        // abrir o banco de dados
        //rodar uma query de consulta
        //retornar um arraylist

        //estou passando pelo adaptador o arrayListaFilmes para a listaFilmesAdapter...
        //definimos um adapter para carregar um dado da matriz no listView
        //utilizando um layout pronto(simple_list_item_1)
        //ArrayAdapter<Filme> listaFilmesAdapter = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, filmes);


        FilmesAdapter adapter = new FilmesAdapter(this, filmes);
        listaFilmes.setAdapter(adapter);

        //injetamos o adapter no objeto listView
        //listaFilmes.setAdapter(listaFilmesAdapter);
    }

}
