package br.senai.sp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.AgendaContato.R;
import br.senai.sp.modelo.Contato;

public class ContatoAdapter extends BaseAdapter {

    private List<Contato> contatos;
    private Context context;

    public ContatoAdapter(Context context, List<Contato> contatos){
        this.contatos = contatos;
        this.context = context;

    }


    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contato contato = contatos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_lista_contatos,null);

        TextView txtNome = view.findViewById(R.id.input_txt_nome);
        txtNome.setText(contato.getNome());

        TextView txtTelefone = view.findViewById(R.id.input_txt_tel);
        txtTelefone.setText(contato.getTelefone());

        return view;
    }
}
