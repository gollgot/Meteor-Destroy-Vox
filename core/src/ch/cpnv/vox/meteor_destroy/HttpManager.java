package ch.cpnv.vox.meteor_destroy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ch.cpnv.vox.meteor_destroy.Model.Vocabulary;

/**
 * This class is used to communicate with the API and create the vocabulary
 */

public class HttpManager implements Net.HttpResponseListener{

    private Net.HttpRequest mRequest;
    private Vocabulary mVocabulary = null;
    private boolean mFinish = false;
    private boolean mError = false;
    private String mErrorMessage;

    /**
     * Empty constructor
     */
    public HttpManager(){
    }

    /**
     * Prepare and send the HTTP Get Request
     */
    public void getVocs(){
        //String url = "http://192.168.0.51/api/v1/vocs/1";
        //String url = "http://172.17.102.188/api/v1/vocs/1";
        String url = "http://avia-sion.ch/api-test/json.php";
        mRequest = new Net.HttpRequest();
        mRequest.setMethod(Net.HttpMethods.GET);
        mRequest.setTimeOut(1500);
        mRequest.setUrl(url);
        Gdx.net.sendHttpRequest(mRequest, this);
    }

    /**
     * Success method called when we send the request and all works
     * @param httpResponse The httpResponse received from the request
     */
    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        buildVocabulary(httpResponse.getResultAsString());
    }

    /**
     * Failed method called when we send the request and an error appear
     * @param t
     *
     */
    @Override
    public void failed(Throwable t) {
        mFinish = true;
        mError = true;
        mErrorMessage = "Erreur :\nImpossible de récupérer\nle vocabulaire,\nveuillez vérifier\nvotre connexion internet";
    }

    /**
     * Method to cancell the request
     */
    @Override
    public void cancelled() {
        mFinish = true;
    }

    /**
     * Create the vocabulary, we used Gson library, it will map the json results in a vocabulary object
     * @param json The raw json in String
     */
    private void buildVocabulary(String json) {
        // I use the Gson library to map the json into a Vocabulary object
        Type listType = new TypeToken<Vocabulary>(){}.getType();
        mVocabulary = new Gson().fromJson(json, listType);
        mFinish = true;
    }

    /**
     * Return true or false if the request finished or not
     * @return The request finished or not
     */
    public boolean isFinish() {
        return mFinish;
    }

    /**
     *
     * @return true or false if we had an error
     */
    public boolean hasError() {
        return mError;
    }

    /**
     * Get the Vocabulary created
     * @return the Vocabulary
     */
    public Vocabulary getVocabulary() {
        return mVocabulary;
    }

    /**
     * get the error message
     * @return the error message
     */
    public String getErrorMessage() {
        return mErrorMessage;
    }
}
