package br.senai.sp.catalogodefilmes;

import android.content.Intent;
import android.database.DefaultDatabaseErrorHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class CadastroFilmeActivity extends AppCompatActivity {

    private CadastroFilmeHelper helper;

    //private Button btnSalvar;
    //alem de aparecer a mesnagem, voltamos para a tela de cadastro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filme);

        helper = new CadastroFilmeHelper(this);

        Intent intent = getIntent();

        Filme filme = (Filme) intent.getSerializableExtra("filme");

        if(filme != null){

            helper.preencherFormulario(filme);

        }
        //findViewById(R.id.btn_salvar);

    }

    //fazer o menu a parecer na activity(tres pontinhos)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        //ctrl + P, para mostrar oq eu preciso passar como parametro
        //inflar = colocar conteudo
        //onCreate cria o menu da tela
        menuInflater.inflate(R.menu.menu_cadastro_filmes, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //saber quando o botao foi blicado, atraves do id dele
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_salvar:

               //os valores das views nos txts sao pegados pelo helper
                Filme filme = helper.getFilme();

                FilmeDAO dao = new FilmeDAO(this);

                if (filme.getId() == 0){
                    dao.salvar(filme);

                }else{

                    dao.atualizar(filme);
                }

                dao.close();
                Toast.makeText(this, filme.getTitulo() + "gravado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
                //abrir o banco
                //criar uma query de insert
                //fechar o banco

                break;
            case R.id.menu_del:
                Toast.makeText(CadastroFilmeActivity.this,
                        "excluir", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_configuracao:
                Toast.makeText(CadastroFilmeActivity.this,
                        "configurações", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(CadastroFilmeActivity.this,
                        "nada", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
