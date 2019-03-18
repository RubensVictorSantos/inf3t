package br.senai.sp.AgendaContato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class CadastroContatoActivity extends AppCompatActivity {

    private CadastroContatoHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        helper = new CadastroContatoHelper(this);

        Intent intent = getIntent();

        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if(contato != null){

            helper.preencherFormulario(contato);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_contatos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_salvar:

                Contato contato = helper.getContato();

                ContatoDAO dao = new ContatoDAO(this);

                if (contato.getId() == 0){
                    //dao.salvar(contato);

                   CadastroContatoHelper helper =  new CadastroContatoHelper(this);
                    if(helper.validar()){

                        dao.salvar(contato);
                        Toast.makeText(CadastroContatoActivity.this, "Contato salvo", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }else if(helper.validar()) {

                        dao.atualizar(contato);
                        finish();
                }

                dao.close();


                break;

            case R.id.menu_del:
                Toast.makeText(CadastroContatoActivity.this, "excluir", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_configuracao:
                Toast.makeText(CadastroContatoActivity.this, "configurações", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(CadastroContatoActivity.this, "nada", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}