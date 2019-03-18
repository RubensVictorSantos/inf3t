package br.senai.sp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(position + " - Agr funcionou!");
        return textView;
    }
}
