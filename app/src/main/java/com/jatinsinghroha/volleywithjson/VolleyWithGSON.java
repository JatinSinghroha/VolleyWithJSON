package com.jatinsinghroha.volleywithjson;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.jatinsinghroha.volleywithjson.models.ListOfDistricts;
import com.jatinsinghroha.volleywithjson.models.ListOfStates;
import com.jatinsinghroha.volleywithjson.models.State;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VolleyWithGSON extends AppCompatActivity {

    RVListOfStatesAdapter mRVListOfStatesAdapter;
    RecyclerView statesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_with_gson);

        statesRV = findViewById(R.id.statesRV);

        fetchStates();
    }

    private void fetchStates() {

        RequestQueue requestQueue = Volley.newRequestQueue(VolleyWithGSON.this);

        String apiURL = "https://cdn-api.co-vin.in/api/v2/admin/location/states";

        JsonObjectRequest getStatesRequest = new JsonObjectRequest(
                Request.Method.GET, apiURL, null,
                response -> {
                    ListOfStates listOfStates =
                            new GsonBuilder().create().fromJson(response.toString(), ListOfStates.class);

                    Log.e("NoOfStates", listOfStates.getTtl() +"");

                    for (int i = 0; i < listOfStates.getStates().size(); i++) {

                        State state = listOfStates.getStates().get(i);

                        String districtAPIURL = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/"+state.getStateID();

                        int finalI = i;
                        JsonObjectRequest getDistrictsRequest = new JsonObjectRequest(
                                Request.Method.GET, districtAPIURL, null,
                                response1 -> {
                                    state.setDistrictList(new GsonBuilder().create().fromJson(response1.toString(), ListOfDistricts.class));

                                    if (finalI == listOfStates.getStates().size()-1) {
                                        mRVListOfStatesAdapter = new RVListOfStatesAdapter(listOfStates.getStates());

                                        statesRV.setAdapter(mRVListOfStatesAdapter);

                                        statesRV.setLayoutManager(new LinearLayoutManager(this,
                                                RecyclerView.VERTICAL, false));

                                    }
                                },
                                error -> {
                                    Log.e("ErrorInAPICall", error.getMessage());
                                }
                        );

                        requestQueue.add(getDistrictsRequest);

                    }



                },
                error -> {
                    Log.e("ErrorInAPICall", error.getMessage());
                }
        );

        requestQueue.add(getStatesRequest);
    }


    /**
     * 5 Parameters:
     * 1. GET
     * 2. URL
     * 3. null
     * 4. Response.Listener
     * 5. Response.ErrorListener
     */
}