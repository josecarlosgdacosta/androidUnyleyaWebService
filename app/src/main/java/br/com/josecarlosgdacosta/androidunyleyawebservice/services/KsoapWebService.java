package br.com.josecarlosgdacosta.androidunyleyawebservice.services;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import br.com.josecarlosgdacosta.androidunyleyawebservice.models.Params;

/**
 * Created by josecarlos on 26/04/17.
 */

public class KsoapWebService extends AsyncTask<Object, Object, SoapObject> {

    private static final String NAMESPACE = "http://tempuri.org/";

    private static final String SOAP_ACTION = "http://tempuri.org/CalcPreco";

    private static final String METHOD_NAME = "CalcPreco";

    private static final String URL = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?wsdl";

    Params p;

    public interface MyService {
        public void getMyServiceResult(SoapObject result);
    }

    MyService myService;

    public KsoapWebService(Params params, MyService ms) {
        this.p = params;
        this.myService = ms;
    }

    @Override
    protected SoapObject doInBackground(Object... params) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("nCdEmpresa", "");
        request.addProperty("sDsSenha", "");
        request.addProperty("nCdServico", p.getnCdServico().toString());
        request.addProperty("sCepOrigem", p.getsCepOrigem().toString());
        request.addProperty("sCepDestino", p.getsCepDestino().toString());
        request.addProperty("nVlPeso", p.getnVlPeso().toString());
        request.addProperty("nCdFormato", p.getnCdFormato().toString());
        request.addProperty("nVlComprimento", p.getnVlComprimento().toString());
        request.addProperty("nVlAltura", p.getnVlAltura().toString());
        request.addProperty("nVlLargura", p.getnVlLargura().toString());
        request.addProperty("nVlDiametro", p.getnVlDiametro().toString());
        request.addProperty("sCdMaoPropria", p.getsCdMaoPropria().toString());
        request.addProperty("nVlValorDeclarado", p.getnVlValorDeclarado().toString());
        request.addProperty("sCdAvisoRecebimento", p.getsCdAvisoRecebimento().toString());

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        SoapObject response = (SoapObject) envelope.bodyIn;

        SoapObject responseAnyType = (SoapObject) response.getProperty(0);
        SoapObject responseServicos = (SoapObject) responseAnyType.getProperty("Servicos");

        return responseServicos;
    }

    @Override
    protected void onPostExecute(SoapObject soapObject) {

        myService.getMyServiceResult(soapObject);

    }
}
