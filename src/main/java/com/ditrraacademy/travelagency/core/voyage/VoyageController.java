package com.ditrraacademy.travelagency.core.voyage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoyageController {

    @Autowired
    VoyageServices voyageServices ;

    @GetMapping("/voyage/{id}")
    public ResponseEntity<?> getVoyageById (@PathVariable int  id ){
        return voyageServices.getVoyage(id);
    }

    @GetMapping("/voyages")
    public ResponseEntity<?> getAllVoyages ( ){
        return voyageServices.getAllVoyages ( );
    }

    @PostMapping("/voyage")
    public ResponseEntity <?> AddVoyage(@RequestBody Voyage voyage){
        return voyageServices.addVoyage(voyage);
    }

    @DeleteMapping("/voyage/{id}")
    public ResponseEntity <?> DeleteVoyage ( @PathVariable int id ){
        return voyageServices.deleteVoyage(id);
    }

    @PutMapping("/voyage/{id}")
    public ResponseEntity<?> UpdateVoyage (@PathVariable int id, @RequestBody Voyage voyage) {
        return voyageServices.updateVoyage (id , voyage);
    }

    @GetMapping("/voyages/byPrice")
    public ResponseEntity<?> getAllVoyagesByMaxPrice (@RequestParam double min, @RequestParam double max){
        return voyageServices.getAllVoyagesByMaxPrice(min, max);
    }

}
