package edu.eci.arsw.cache;


import org.javatuples.Triplet;
import edu.eci.arsw.model.Pais;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CoronavirusStatsCache {


    private final CopyOnWriteArrayList<Triplet<String,Timer,String>> listaPaises= new CopyOnWriteArrayList<>();

    ArrayList<Pais> paises= new ArrayList<>();

    public boolean getCountriesFirstTime(){
        if (paises.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public void getCountriesFromConnection(JSONArray countries){
        JSONObject pais;
        for (int i=0; i<countries.length();i++){
            boolean esta= false;
            pais= new JSONObject(countries.get(i).toString());
            for(int j=0;j< paises.size();j++){
                if(paises.get(j).getName().equals(pais.get("country").toString())){
                    paises.get(j).setMuertos(paises.get(j).getMuertos()+ Integer.parseInt(pais.get("deaths").toString()));
                    paises.get(j).setInfectados(paises.get(j).getInfectados()+ Integer.parseInt(pais.get("confirmed").toString()));
                    paises.get(j).setCurados(paises.get(j).getCurados()+ Integer.parseInt(pais.get("recovered").toString()));
                    esta=true;
                }
            }
            if(!esta){
                Pais newPais= new Pais(pais.get("country").toString(), Integer.parseInt(pais.get("deaths").toString()),
                        Integer.parseInt(pais.get("confirmed").toString()),Integer.parseInt(pais.get("recovered").toString()));
                paises.add(newPais);
            }

        }
        System.out.println(paises.get(0).getName());
        System.out.println(paises.get(0).getCurados());
        System.out.println(paises.get(1).getName());
        System.out.println(paises.get(1).getCurados());
    }

    public ArrayList<Pais> getCountries(){
        return paises;
    }

    public String  getCountryByName(String name,String data) throws CoronavirusStatsException {
        String objeto="";
        for (int i=0; i< listaPaises.size();i++){
            if(listaPaises.get(i).getValue0().equals(name)){
                objeto=listaPaises.get(i).getValue2();
                break;
            }
        }
        if (objeto.equals("")){
            tiempo(name,data);
        }

        return objeto;
    }

    /**
     *
     * @param name country's name
     * @return all the informatio of the given country if the country is not on cache
     * @throws CoronavirusStatsException if the given country doesn't exist
     */
    public String getCountryByName(String name) throws CoronavirusStatsException{
        String objeto=null;
        for (int i=0; i< listaPaises.size();i++){
            if(listaPaises.get(i).getValue0().equals(name)){
                objeto=listaPaises.get(i).getValue2();
                break;
            }
        }
        return objeto;
    }

    /**
     *
     * Remove the country from cache when the time has passed 5 minutes
     * @param name country's name
     * @param data all the information
     */
    public void tiempo(String name, String data){
        Timer timer = new Timer();
        final Triplet pais=new Triplet<>(name,timer,data);
        listaPaises.add(pais);
        TimerTask timerTask = new TimerTask() {
            public void run() {
                listaPaises.remove(pais);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 300000, 300000);
    }
}
