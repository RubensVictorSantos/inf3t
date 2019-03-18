package br.senai.sp.dao;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.catalogodefilmes.MainActivity;
import br.senai.sp.catalogodefilmes.R;
import br.senai.sp.modelo.Filme;

//clica na linha e alt + enter, para extender tambem os dois metodos abaixo
//e o construtor

//metodo executor da aplicacao
//SQLiteOpenHelper = tenta achar o banco, se ele n existir, ele cria o banco
public class FilmeDAO extends SQLiteOpenHelper {

    //classe mae
    //version = versao do banco, no nosso caso 1, pq é a primeira
    //contexto = onde eu estou, quem chama o dao, qual a activity
    public FilmeDAO(Context context) {
        super(context, "db_filme", null, 1);
    }


    //criamos a tabela do banco
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbl_filme (" +
                "id INTEGER PRIMARY KEY, " +
                "titulo TEXT NOT NULL, " +
                "diretor TEXT NOT NULL, " +
                "genero TEXT NOT NULL, " +
                "data_lancamento TEXT NOT NULL, " +
                "duracao TEXT NOT NULL, " +
                "nota INTEGER NOT NULL)";

        db.execSQL(sql);
    }


    //permite que o banco nao seja perdido, salvando os dados do usuario a cada atualizacao
    //do (apk) do app. Qualquer atualizacao feita no banco, deve ser por aqui
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //db é o banco de dados com permissao de escrita
    //inserindo dados no banco
    public void salvar(Filme filme) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValues(filme);

        db.insert("tbl_filme", null, dados);

    }

    @NonNull
    private ContentValues getContentValues(Filme filme) {
        ContentValues dados = new ContentValues();

        dados.put("titulo", filme.getTitulo());
        dados.put("diretor", filme.getDiretor());
        dados.put("genero", filme.getGenero());
        dados.put("data_lancamento", filme.getDataLancamento());
        dados.put("duracao", filme.getDuracao());
        dados.put("nota", filme.getNota());
        return dados;
    }

    public List<Filme> getFilmes() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM tbl_filme";

        Cursor c = db.rawQuery(sql, null);

        List<Filme> filmes = new ArrayList<>();

        //enquanto houver filmes na lista, while permanece mostrando a lista
        while(c.moveToNext()){

            Filme filme = new Filme();

            //retornar o inteiro que esta na coluna id
            filme.setId(c.getInt(c.getColumnIndex("id")));
            filme.setTitulo(c.getString(c.getColumnIndex("titulo")));
            filme.setDiretor(c.getString(c.getColumnIndex("diretor")));
            filme.setGenero(c.getString(c.getColumnIndex("genero")));
            filme.setDataLancamento(c.getString(c.getColumnIndex("data_lancamento")));
           // filme.getDataLancamento(c.getString())
            filme.setDuracao(c.getString(c.getColumnIndex("duracao")));
            filme.setNota(c.getInt(c.getColumnIndex("nota")));
            filmes.add(filme);
        }

        return filmes;

    }

    //metodo do botao excluir
    //chama o botao e passa um filme para ele excluir
    public void excluir(Filme filme){
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(filme.getId())};

        db.delete("tbl_filme", "id= ?", params);
    }

    public void atualizar(Filme filme ) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(filme.getId())};
        ContentValues dados = getContentValues(filme);

        db.update("tbl_filme", dados, "id=?", params);


    }
}
