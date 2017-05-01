package br.com.josecarlosgdacosta.androidunyleyawebservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{

    Spinner servico;
    String selectedServicoValue;

    EditText cep_origem;
    String cepOrigemValue;

    EditText cep_destino;
    String cepDestinoValue;

    EditText peso;
    String pesoValue;

    Spinner formato;
    String selectedFormato;

    EditText comprimento;
    String comprimentoValue;

    EditText altura;
    String alturaValue;

    EditText largura;
    String larguraValue;

    EditText diametro;
    String diametroValue;

    Spinner mao_propria;
    String selectedMaoPropria;

    EditText valor_declarado;
    String valorDeclaradoValue;

    Spinner aviso_recebimento;
    String selectedAvisoRecebimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        servico = (Spinner) findViewById(R.id.servico);
        mao_propria = (Spinner) findViewById(R.id.mao_propria);
        formato = (Spinner) findViewById(R.id.formato);
        aviso_recebimento = (Spinner) findViewById(R.id.aviso_recebimento);

        ArrayAdapter<CharSequence> servicoSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.servicos, android.R.layout.simple_spinner_item);
        servicoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servico.setAdapter(servicoSpinnerAdapter);
        servico.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> maoPropriaSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sim_nao, android.R.layout.simple_spinner_item);
        maoPropriaSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mao_propria.setAdapter(maoPropriaSpinnerAdapter);
        mao_propria.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> avisoRecebimentoSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sim_nao, android.R.layout.simple_spinner_item);
        avisoRecebimentoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aviso_recebimento.setAdapter(avisoRecebimentoSpinnerAdapter);
        aviso_recebimento.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> formatoSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.formatos, android.R.layout.simple_spinner_item);
        formatoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formato.setAdapter(formatoSpinnerAdapter);
        formato.setOnItemSelectedListener(this);

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFormValues();

                Intent intent = new Intent(v.getContext(), ResultActivity.class);
                intent.putExtra("nCdEmpresa", "");
                intent.putExtra("sDsSenha", "");
                intent.putExtra("nCdServico", selectedServicoValue);
                intent.putExtra("sCepOrigem", cepOrigemValue);
                intent.putExtra("sCepDestino", cepDestinoValue);
                intent.putExtra("nVlPeso", pesoValue);
                intent.putExtra("nCdFormato", selectedFormato);
                intent.putExtra("nVlComprimento", comprimentoValue);
                intent.putExtra("nVlAltura", alturaValue);
                intent.putExtra("nVlLargura", larguraValue);
                intent.putExtra("nVlDiametro", diametroValue);
                intent.putExtra("sCdMaoPropria", selectedMaoPropria);
                intent.putExtra("nVlValorDeclarado", valorDeclaradoValue);
                intent.putExtra("sCdAvisoRecebimento", selectedAvisoRecebimento);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.servico:
                String[] servicoValues = getResources().getStringArray(R.array.servicos_value);
                selectedServicoValue = servicoValues[position];
                break;

            case R.id.mao_propria:
                String[] maoPropriaValues = getResources().getStringArray(R.array.sim_nao_values);
                selectedMaoPropria = maoPropriaValues[position];
                break;

            case R.id.aviso_recebimento:
                String[] avisoRecebimentoValues = getResources().getStringArray(R.array.sim_nao_values);
                selectedAvisoRecebimento = avisoRecebimentoValues[position];
                break;

            case R.id.formato:
                String[] formatoValues = getResources().getStringArray(R.array.formatos_values);
                selectedFormato = formatoValues[position];
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getFormValues() {

        cep_origem = (EditText) findViewById(R.id.cep_origem);
        cep_destino = (EditText) findViewById(R.id.cep_destino);
        cep_destino = (EditText) findViewById(R.id.cep_destino);
        peso = (EditText) findViewById(R.id.peso);
        comprimento = (EditText) findViewById(R.id.comprimento);
        altura = (EditText) findViewById(R.id.altura);
        largura = (EditText) findViewById(R.id.largura);
        diametro = (EditText) findViewById(R.id.diametro);
        valor_declarado = (EditText) findViewById(R.id.valor_declarado);

        cepOrigemValue = cep_origem.getText().toString();
        cepDestinoValue = cep_destino.getText().toString();
        pesoValue = peso.getText().toString();
        comprimentoValue = comprimento.getText().toString();
        alturaValue = altura.getText().toString();
        larguraValue = largura.getText().toString();
        diametroValue = diametro.getText().toString();
        valorDeclaradoValue = valor_declarado.getText().toString();

    }
}