package com.example.hp.motivationalquotes.data;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.hp.motivationalquotes.controller.AppController;
import com.example.hp.motivationalquotes.model.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuoteData {

    ArrayList<Quote> quoteArrayList = new ArrayList<Quote>();

    public void getQuotes(){
        String url = "https://gist.github.com/b1nary/ea8fff806095bcedacce";

        // creation of JSOn array request

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject quoteObject = response.getJSONObject(i);
                        Quote qoute = new Quote();
                        qoute.setText(quoteObject.getString("text"));
                        qoute.setFrom(quoteObject.getString("from"));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }

}
