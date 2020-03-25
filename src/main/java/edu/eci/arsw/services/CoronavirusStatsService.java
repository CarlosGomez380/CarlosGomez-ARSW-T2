package edu.eci.arsw.services;



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

    /**
     * check whether countries' information is on cache or it's necessary to get them from HttpConnectionService.
     * @return all the countries' information related with covid-19 as a ArrayList of Countries.
     */
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

    /**
     * check whether countries' information related with their regions is on cache or it's necessary to get them from HttpConnectionService
     * @param name of the coutry.
     * @return all the regions of the country related with covid-19 as a String.
     */
    public String getCountryByName(String name)  throws CoronavirusStatsException {
        if(coronavirus.getCountryByName(name)!= null){
            return coronavirus.getCountryByName(name);
        }else{
            coronavirus.getCountryByName(name,connection.getConnectionByCountry(name));
            return coronavirus.getCountryByName(name);
        }
    }

    /**
     * Get the information of the country on cache.
     * @param name of the coutry.
     * @return the information of the country related with covid-19 as a String.
     */
    public Pais getCountryInfo(String name)  throws CoronavirusStatsException {
        return coronavirus.getCountryInfo(name);

    }
}
