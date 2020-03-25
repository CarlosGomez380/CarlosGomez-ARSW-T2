package edu.eci.arsw.services;


import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.eci.arsw.cache.CoronavirusStatsCache;
import edu.eci.arsw.cache.CoronavirusStatsException;
import edu.eci.arsw.model.Pais;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CoronavirusStatsService {

    @Autowired
    CoronavirusStatsCache coronavirus = null;

    @Autowired
    HttpConnectionService connection=null;


    public ArrayList<Pais> getCountries() throws CoronavirusStatsException {

        if(coronavirus.getCountriesFirstTime()){
            String data=connection.getConnection();
            JSONObject newData= new JSONObject(data);
            JSONObject covid= new JSONObject(newData.get("data").toString());
            JSONArray countries= new JSONArray((covid).get("covid19Stats").toString());
            JSONObject pais= new JSONObject(countries.get(0).toString());
            coronavirus.getCountriesFromConnection(countries);

        }

        return coronavirus.getCountries();

    }


    public String getCountryByName(String name)  throws CoronavirusStatsException {
        if(coronavirus.getCountryByName(name)!= null){
            System.out.println("chao");
            return coronavirus.getCountryByName(name);
        }else{
            System.out.println("Hola");
            coronavirus.getCountryByName(name,connection.getConnectionByCountry(name));
            return coronavirus.getCountryByName(name);
        }
    }
}
