package br.senai.sp.catalogodefilmes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.ByteArrayOutputStream;

import br.senai.sp.conversores.Imagem;
import br.senai.sp.modelo.Filme;

//pega os dados digitados pelo usuario nos texts e constrói um filme
public class CadastroFilmeHelper {



    private EditText txtTitulo;
    private EditText txtDiretor;
    private EditText txtDataLancamento;
    private EditText txtDuracao;
    private EditText txtGenero;
    private RatingBar nota;
    private ImageView foto;
    private Filme filme;

    //metodo construtor, pega os dados do text
    public CadastroFilmeHelper(CadastroFilmeActivity activity){
    txtTitulo = activity.findViewById(R.id.txt_titulo);
    txtDiretor = activity.findViewById(R.id.txt_diretor);
    txtDataLancamento = activity.findViewById(R.id.txt_data_lancamento);
    txtDuracao = activity.findViewById(R.id.txt_duracao);
    txtGenero = activity.findViewById(R.id.txt_genero);
    nota = activity.findViewById(R.id.rate_nota);
    filme = new Filme();

    }

    public Filme getFilme(){

    filme.setDataLancamento(txtDataLancamento.getText().toString());
    filme.setDiretor(txtDiretor.getText().toString());
    filme.setDuracao(txtDuracao.getText().toString());
    filme.setGenero(txtGenero.getText().toString());
    filme.setTitulo(txtTitulo.getText().toString());
    filme.setNota(nota.getProgress());

    //Conteudo imageview convertido em bitmap (Extraindo o bitmap)
    Bitmap bm = ((BitmapDrawable)foto.getDrawable()).getBitmap();
    //(Reduzindo o bitmat)
    Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bm, 300, 300, true);

        //Converter o bitmap em array de byte
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapReduzido.compress(Bitmap.CompressFormat.PNG,0 ,byteArrayOutputStream);
        byte[] fotoArray = byteArrayOutputStream.toByteArray();

    filme.setFoto(fotoArray);

    return filme;

    }

    public void preencherFormulario(Filme filme) {

        txtTitulo.setText(filme.getTitulo());
        txtGenero.setText(filme.getGenero());
        txtDuracao.setText(filme.getDuracao());
        txtDiretor.setText(filme.getDiretor());
        txtDataLancamento.setText(filme.getDataLancamento());
        nota.setProgress(filme.getNota());

        //transformar o array de bytes em bitmap
        if(filme.getFoto().length>0){

            foto.setImageBitmap(Imagem.arrayToBitmap(filme.getFoto()));

        }

        this.filme = filme;


    }
}
