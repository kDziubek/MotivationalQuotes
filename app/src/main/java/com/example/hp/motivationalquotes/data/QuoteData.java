package com.example.hp.motivationalquotes.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.hp.motivationalquotes.QuoteListAsycnRespone;
import com.example.hp.motivationalquotes.controller.AppController;
import com.example.hp.motivationalquotes.model.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class QuoteData {

    private ArrayList<Quote> quoteArrayList = new ArrayList<>();


    public void getQuotes(final QuoteListAsycnRespone callBack) {
        String url = "https://raw.githubusercontent.com/pdichone/UIUX-Android-Course/master/Quotes.json%20";


        //initialize the jsonArrayRequest !object! from JsonArrayRequest class and snd set Listener
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //iterate through response JsonArray
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //initialize new JSONObject --quoteObject-- of JSONObject class and set it to array response
                        JSONObject quoteObject = response.getJSONObject(i);

                        //initialize new object --quote-- of Quote(Own model class) class
                        Quote quote = new Quote();
                        quote.setQuote(quoteObject.getString("quote"));
                        quote.setAuthor(quoteObject.getString("name"));

                        Log.d("STUFFF::", quoteObject.getString("name"));

                        quoteArrayList.add(quote);
                        shuffle();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                if (null != callBack) {
                    //only when method getQuotes is finished it will run
                    callBack.processFinished(quoteArrayList);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    public void shuffle(){
        Collections.shuffle(quoteArrayList);
    }
}

