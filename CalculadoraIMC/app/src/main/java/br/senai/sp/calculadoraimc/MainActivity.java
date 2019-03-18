package br.senai.sp.calculadoraimc;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {



    private TextInputLayout layoutTxtPeso;
    private TextInputLayout layoutTxtAltura;
    private EditText txtPeso;
    private EditText txtAltura;

    private TextView txtImc;
    private TextView txtResultado;
    private TextView txtResumo;

    private ImageButton btCalcular;
    private ImageButton btLimpar;

    private RelativeLayout cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutTxtAltura = findViewById(R.id.input_txt_altura);
        layoutTxtPeso = findViewById(R.id.input_txt_peso);
        txtPeso = findViewById(R.id.txt_peso);
        txtAltura = findViewById(R.id.txt_altura);
        txtImc = findViewById(R.id.view_imc);
        txtResultado = findViewById(R.id.view_imc_resultado);
        txtResumo = findViewById(R.id.view_resumo);
        btCalcular = findViewById(R.id.bt_calcular);
        btLimpar = findViewById(R.id.bt_limpar);
        cardView = findViewById(R.id.card_view);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( validar()){
                    calcularImc();
                }
            }
        });

    }

    private void calcularImc(){

        DecimalFormat df = new DecimalFormat(".##");

        double imc = 0;
        double peso = Double.parseDouble(txtPeso.getText().toString());
        double altura = Double.parseDouble(txtAltura.getText().toString());
        imc = peso/(Math.pow(altura, 2));

        txtImc.setText(String.valueOf(df.format(imc)));

        if(imc<15){

            txtResultado.setText(getResources().getText(R.string.abaixo_peso_1));
            txtResumo.setText(getResources().getText(R.string.desc_abaixo_peso_1));
        }

    }



    private boolean validar() {

        boolean validado = true;
        if (txtPeso.getText().toString().isEmpty()) {
            layoutTxtPeso.setErrorEnabled(true);
            layoutTxtPeso.setError("Por favor digite o Peso");
            validado = false;

        } else {

            layoutTxtPeso.setErrorEnabled(false);

        }

        if (txtAltura.getText().toString().isEmpty()) {
            layoutTxtAltura.setErrorEnabled(true);
            layoutTxtAltura.setError("Por favor digite a Altura");
            validado = false;

        } else {

            layoutTxtAltura.setErrorEnabled(false);

        }
        return validado;
    }

    private void limpar(){

    }

}



