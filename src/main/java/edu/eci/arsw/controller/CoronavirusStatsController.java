package edu.eci.arsw.controller;


import edu.eci.arsw.cache.CoronavirusStatsException;
import edu.eci.arsw.services.CoronavirusStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/coronavirus")
public class CoronavirusStatsController {

    @Autowired
    CoronavirusStatsService coronavirus = null;

    /**
     *
     * @return all the cases of the given country as a HTTP response.
     */
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<?> GetCountrys() throws CoronavirusStatsException{
        try {
            return new ResponseEntity<>(coronavirus.getCountries(), HttpStatus.ACCEPTED);
        } catch (CoronavirusStatsException e) {
            Logger.getLogger(CoronavirusStatsController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( path ="/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> GetCountryByName(@PathVariable  String name) throws CoronavirusStatsException{
        try {
            return new ResponseEntity<>(coronavirus.getCountryByName(name), HttpStatus.ACCEPTED);
        } catch (CoronavirusStatsException e) {
            Logger.getLogger(CoronavirusStatsController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
