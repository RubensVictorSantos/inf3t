package br.senai.sp.AgendaContato;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.senai.sp.AgendaContato.CadastroContatoActivity;

import br.senai.sp.modelo.Contato;

public class CadastroContatoHelper {

    private TextInputLayout layoutTxtNome;
    private TextInputLayout layoutTxtEndereco;
    private TextInputLayout layoutTxtCidade;
    private TextInputLayout layoutTxtTelefone;
    private TextInputLayout layoutTxtEmail;
    private TextInputLayout layoutTxtLinkein;

    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtCidade;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtLinkedin;
    private Contato contato;

    public CadastroContatoHelper(CadastroContatoActivity activity) {
        txtNome = activity.findViewById(R.id.txt_nome);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtCidade = activity.findViewById(R.id.txt_cidade);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtLinkedin = activity.findViewById(R.id.txt_linkedin);

        layoutTxtNome = activity.findViewById(R.id.input_txt_nome);
        layoutTxtEndereco= activity.findViewById(R.id.input_txt_end);
        layoutTxtCidade = activity.findViewById(R.id.input_txt_cid);
        layoutTxtTelefone = activity.findViewById(R.id.input_txt_tel);
        layoutTxtEmail = activity.findViewById(R.id.input_txt_email);
        layoutTxtLinkein = activity.findViewById(R.id.input_txt_lin);

        contato = new Contato();
    }

    public Contato getContato(){

        contato.setNome(txtNome.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setCidade(txtCidade.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());

        return contato;

    }

    public void preencherFormulario(Contato contato) {

        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtCidade.setText(contato.getCidade());
        txtTelefone.setText(contato.getTelefone());
        txtEmail.setText(contato.getEmail());
        txtLinkedin.setText(contato.getLinkedin());
        this.contato = contato;

    }

    public boolean validar() {

        boolean validado = true;
        if (txtNome.getText().toString().isEmpty()) {
            layoutTxtNome.setErrorEnabled(true);
            layoutTxtNome.setError("Por favor digite seu Nome");
            validado = false;

        } else {

            layoutTxtNome.setErrorEnabled(false);

        }

        if (txtEndereco.getText().toString().isEmpty()) {
            layoutTxtEndereco.setErrorEnabled(true);
            layoutTxtEndereco.setError("Por favor digite seu Endereço");
            validado = false;

        } else {

            layoutTxtEndereco.setErrorEnabled(false);

        }

        if (txtCidade.getText().toString().isEmpty()) {
            layoutTxtCidade.setErrorEnabled(true);
            layoutTxtCidade.setError("Por favor digite sua Cidade");
            validado = false;

        } else {

            layoutTxtCidade.setErrorEnabled(false);

        }

        if (txtTelefone.getText().toString().isEmpty()) {
            layoutTxtTelefone.setErrorEnabled(true);
            layoutTxtTelefone.setError("Por favor digite seu Telefone");
            validado = false;

        } else {

            layoutTxtTelefone.setErrorEnabled(false);

        }


        if (txtEmail.getText().toString().isEmpty()) {
            layoutTxtEmail.setErrorEnabled(true);
            layoutTxtEmail.setError("Por favor digite sue Email");
            validado = false;

        } else {

            layoutTxtEmail.setErrorEnabled(false);

        }

        if (txtLinkedin.getText().toString().isEmpty()) {
            layoutTxtLinkein.setErrorEnabled(true);
            layoutTxtLinkein.setError("Por favor digite seu Linkedin");
            validado = false;

        } else {

            layoutTxtLinkein.setErrorEnabled(false);

        }

        return validado;
    }

}
