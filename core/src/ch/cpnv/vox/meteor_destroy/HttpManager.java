package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ch.cpnv.vox.meteor_destroy.Model.Vocabulary;

/**
 * Created by Loic.DESSAULES on 29.05.2017.
 */

public class HttpManager implements Net.HttpResponseListener{

    private Net.HttpRequest request;
    private Vocabulary mVocabulary = null;
    private boolean finish = false;
    private boolean error = false;
    private String errorMessage;

    public HttpManager(){
    }

    public void getVocs(){
        //String url = "http://192.168.0.51/api/v1/vocs/1";
        String url = "http://172.17.102.188/api/v1/vocs/1";
        request = new Net.HttpRequest();
        request.setMethod(Net.HttpMethods.GET);
        request.setTimeOut(1500);
        request.setUrl(url);
        Gdx.net.sendHttpRequest(request, this);
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        buildVocabulary(httpResponse.getResultAsString());
    }

    @Override
    public void failed(Throwable t) {
        finish = true;
        error = true;
        errorMessage = "Erreur :\nImpossible de récupérer\nle vocabulaire,\nveuillez vérifier\nvotre connexion internet";
    }

    @Override
    public void cancelled() {
        finish = true;
    }

    private void buildVocabulary(String json) {
        // I use the Gson library to map the json into a Vocabulary object
        Type listType = new TypeToken<Vocabulary>(){}.getType();
        mVocabulary = new Gson().fromJson(json, listType);
        finish = true;
    }

    public boolean isFinish() {
        return finish;
    }

    public Vocabulary getVocabulary() {
        return mVocabulary;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
