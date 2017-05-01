package br.com.josecarlosgdacosta.androidunyleyawebservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

import br.com.josecarlosgdacosta.androidunyleyawebservice.models.Params;
import br.com.josecarlosgdacosta.androidunyleyawebservice.services.KsoapWebService;

public class ResultActivity extends AppCompatActivity implements KsoapWebService.MyService{

    TextView tResultado;
    TextView tVlrSemAdicionais;
    TextView tVlrMaoPropria;
    TextView tVlrAviso;
    TextView tVlrTotal;
    TextView msg;
    Button btnRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.tResultado = (TextView) findViewById(R.id.resultado);
        this.tVlrSemAdicionais = (TextView) findViewById(R.id.tVlrSemAdicionais);
        this.tVlrMaoPropria = (TextView) findViewById(R.id.tVlrMaoPropria);
        this.tVlrAviso = (TextView) findViewById(R.id.tVlrAviso);
        this.tVlrTotal = (TextView) findViewById(R.id.tVlrTotal);
        this.msg = (TextView) findViewById(R.id.msg);
        this.btnRetornar = (Button) findViewById(R.id.btnRetornar);

        Intent intent = getIntent();

        Params params = new Params();
        params.setnCdEmpresa(intent.getStringExtra("nCdEmpresa"));
        params.setsDsSenha(intent.getStringExtra("sDsSenha"));
        params.setnCdServico(intent.getStringExtra("nCdServico"));
        params.setsCepOrigem(intent.getStringExtra("sCepOrigem"));
        params.setsCepDestino(intent.getStringExtra("sCepDestino"));
        params.setnVlPeso(intent.getStringExtra("nVlPeso"));
        params.setnCdFormato(intent.getStringExtra("nCdFormato"));
        params.setnVlComprimento(intent.getStringExtra("nVlComprimento"));
        params.setnVlAltura(intent.getStringExtra("nVlAltura"));
        params.setnVlLargura(intent.getStringExtra("nVlLargura"));
        params.setnVlDiametro(intent.getStringExtra("nVlDiametro"));
        params.setsCdMaoPropria(intent.getStringExtra("sCdMaoPropria"));
        params.setnVlValorDeclarado(intent.getStringExtra("nVlValorDeclarado"));
        params.setsCdAvisoRecebimento(intent.getStringExtra("sCdAvisoRecebimento"));

        new KsoapWebService(params, this).execute();

    }

    @Override
    public void getMyServiceResult(SoapObject result) {

        SoapObject r = (SoapObject) result.getProperty("cServico");

        String codErro = r.getProperty("Erro").toString();
        if (codErro.matches("^-?\\d+$")) {
            this.msg.setText(r.getProperty("MsgErro").toString());
        } else {
            this.tVlrSemAdicionais.setText("Valor sem adicionais: R$" + r.getProperty("ValorSemAdicionais").toString());
            this.tVlrMaoPropria.setText("Taxa por entrega em mão própria: R$" + r.getProperty("ValorMaoPropria").toString());
            this.tVlrAviso.setText("Taxa por aviso de recebimento: R$" + r.getProperty("ValorAvisoRecebimento").toString());
            this.tVlrTotal.setText("Valor total: R$" + r.getProperty("Valor").toString());
            this.msg.setVisibility(View.GONE);
        }

        this.btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}
