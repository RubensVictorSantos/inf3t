package br.senai.sp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.catalogodefilmes.R;
import br.senai.sp.conversores.Imagem;
import br.senai.sp.modelo.Filme;

public class FilmesAdapter extends BaseAdapter {//Classe Abstrata

    private List<Filme> filmes;
    private Context context;

    public FilmesAdapter(Context context, List<Filme> filmes){
        this.filmes = filmes;
        this.context = context;

    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int position) {
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return filmes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Filme filme = filmes.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_lista_filmes, null);

        TextView txtDiretor = view.findViewById(R.id.txt_diretor);
        txtDiretor.setText(filme.getDiretor());

        TextView txtTitulo = view.findViewById(R.id.txt_titulo);
        txtTitulo.setText(filme.getTitulo());


        RatingBar nota = view.findViewById(R.id.rate_nota);
        nota.setRating(filme.getNota());

        ImageView foto = view.findViewById(R.id.image_filme);

        if(filme.getFoto() != null) {

            foto.setImageBitmap(Imagem.arrayToBitmap(filme.getFoto()));
        }

        return view;
    }
}
