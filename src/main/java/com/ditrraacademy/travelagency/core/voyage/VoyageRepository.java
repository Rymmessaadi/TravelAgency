package com.ditrraacademy.travelagency.core.voyage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {

    List<Voyage> findAllByPrixIsLessThanEqual(double maxPrice);

    List<Voyage> findAllByPrixIsBetweenAndNbPlacesIsNot(double min, double max, int nbPlaces);

    @Query(value = "select * from voyage v where v.nb_places != ?1", nativeQuery = true)
    List<Voyage> findAllByQuery(@Param("nb") int nb);
}
