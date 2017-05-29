package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

/**
 * Created by Loic.DESSAULES on 29.05.2017.
 */

public class HttpManager implements Net.HttpResponseListener{

    private Net.HttpRequest request;

    public HttpManager(){
    }

    public void getVocs(){
        //String url = "http://192.168.0.51/api/v1/vocs/1";
        String url = "http://172.17.102.188/api/v1/vocs/1";
        request = new Net.HttpRequest();
        request.setMethod(Net.HttpMethods.GET);
        request.setUrl(url);
        Gdx.net.sendHttpRequest(request, this);
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        // Return the output as String
        System.out.println(httpResponse.getResultAsString());
    }

    @Override
    public void failed(Throwable t) {
        System.out.println("ERROR : Impossible to get vocabularies ... ");
    }

    @Override
    public void cancelled() {

    }

}
