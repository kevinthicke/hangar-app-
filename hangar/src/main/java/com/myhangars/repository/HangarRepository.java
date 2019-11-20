package com.myhangars.repository;

import com.myhangars.model.Hangar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HangarRepository extends JpaRepository<Hangar, Long> {

    /*
    @Query("Select hangar FROM Hangar hangar WHERE hangar.name LIKE :name AND hangar.state = true")
    public List<Hangar> findByNameWithTrueState(@Param("name") String name);
    */
    @Query("Select hangar FROM Hangar hangar WHERE hangar.state = true")
    public List<Hangar> findAllWithTrueStateNoPaginable();

    @Query("Select hangar FROM Hangar hangar WHERE hangar.name LIKE :name AND hangar.state = true")
    public List<Hangar> findByNameWithTrueState(@Param("name") String name);

    @Query("Select hangar from Hangar hangar WHERE hangar.name = ?1")
    public Optional<Hangar> findExactlyByNameWithTrueState(String name);

    @Query("Select hangar FROM Hangar hangar WHERE hangar.state = true")
    public Page< Hangar > findAllWithTrueState(Pageable pageable);

    @Query("Select hangar From Hangar hangar WHERE hangar.state = true AND hangar.id = ?1")
    public Optional<Hangar> findByIdWithTrueState(long id);
}
