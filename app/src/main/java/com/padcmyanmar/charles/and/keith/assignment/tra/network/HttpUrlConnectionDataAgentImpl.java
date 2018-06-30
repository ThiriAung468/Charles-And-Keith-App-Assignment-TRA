package com.padcmyanmar.charles.and.keith.assignment.tra.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.ApiErrorEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.NewProductsConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionDataAgentImpl implements NewProductsDataAgent {

    private static HttpUrlConnectionDataAgentImpl objInstance;

    private HttpUrlConnectionDataAgentImpl() {


    }

    public static HttpUrlConnectionDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new HttpUrlConnectionDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadNewProductsList(final String accessToken, final int page) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {
                    url = new URL(NewProductsConstants.API_BASE + NewProductsConstants.GET_NEW_PRODUCTS);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(15 * 1000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(NewProductsConstants.PARAM_ACCESS_TOKEN,accessToken));
                    params.add(new BasicNameValuePair(NewProductsConstants.PARAM_PAGE,String.valueOf(page)));
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();
                    connection.connect();
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    String responseString = stringBuilder.toString();

                    return responseString;


                } catch (Exception e) {
                    Log.e("", e.getMessage());

                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }

                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetNewProductsResponse newProductsResponse = gson.fromJson(responseString , GetNewProductsResponse.class);
                Log.d("onPostExecute","Products List Size : "+newProductsResponse.getNewProducts().size());

                if(newProductsResponse.isResponseOK()){
                    SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(newProductsResponse.getNewProducts());
                    EventBus.getDefault().post(event);
                }else{
                    ApiErrorEvent event = new ApiErrorEvent(newProductsResponse.getMessage());
                    EventBus.getDefault().post(event);
                }

            }
        }.execute();
    }


    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }


}
