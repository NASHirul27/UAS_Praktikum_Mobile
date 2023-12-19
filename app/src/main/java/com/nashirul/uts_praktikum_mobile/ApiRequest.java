package com.nashirul.uts_praktikum_mobile;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiRequest {

    private static final String API_URL = "https://api.open5e.com/v1/spells/";
    private static final String CLASS_API_URL = "https://api.open5e.com/v1/classes/";


    public interface ApiListener {
        void onSuccessSpells(List<Spell> spells);
        void onError(VolleyError error);
    }


    public static void makeApiRequest(Context context, final ApiListener listener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Spell> spells = new ArrayList<>();
                            JSONArray spellsArray = response.getJSONArray("results");

                            for (int i = 0; i < spellsArray.length(); i++) {
                                JSONObject spellObject = spellsArray.getJSONObject(i);
                                String name = spellObject.getString("name");
                                String desc = spellObject.getString("desc");

                                Spell spell = new Spell(name, desc);
                                spells.add(spell);
                            }

                            listener.onSuccessSpells(spells);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError(new VolleyError("JSON parsing error"));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}

