package br.senai.sp.AgendaContato;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.adapter.ContatoAdapter;
import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class MainActivity extends AppCompatActivity {

    private ListView listaContatos;
    private Button btnNovoContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = findViewById(R.id.list_contato);

        btnNovoContato = findViewById(R.id.btn_novo_contato);

        btnNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cadastroContato = new Intent(MainActivity.this,CadastroContatoActivity.class);
                startActivity(cadastroContato);

            }
        });

        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);

                Intent cadastro = new Intent(MainActivity.this, CadastroContatoActivity.class);
                cadastro.putExtra("contato", contato);

                startActivity(cadastro);

                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();

            }
        });


        registerForContextMenu(listaContatos);

        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//Ele retorna AdapterView,View,posição e o id
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);

                Intent cadastro = new Intent(MainActivity.this, CadastroContatoActivity.class);
                //tudo na intent tem que ser serializado, para que possam ser remontados em outra activity
                cadastro.putExtra("contato", contato);

                startActivity(cadastro);

                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contex_lista_contatos, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onContextItemSelected (final MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final ContatoDAO dao = new ContatoDAO(MainActivity.this);
        final Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);

        new AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Tem certeza que deseja deletar esse contato?")
                .setPositiveButton("sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(MainActivity.this, contato.getNome(), Toast.LENGTH_LONG).show();

                                dao.excluir(contato);

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

        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.getContatos();
        dao.close();

        ContatoAdapter adapter = new ContatoAdapter(this, contatos);
        listaContatos.setAdapter(adapter);

    }

}
