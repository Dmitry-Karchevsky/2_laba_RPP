package com.example.rpp_sem4_lab2;

import org.json.JSONArray;

public class Holder {

    private static volatile Holder instance;
    private JSONArray data;

    public static Holder getInstance() {
        Holder localInstance = instance;

        if (localInstance == null) {
            instance = localInstance = new Holder();
        }

        return localInstance;
    }

    public void setData(JSONArray data){
        this.data = data;
    }

    public JSONArray getData(){
        return this.data;
    }

}
