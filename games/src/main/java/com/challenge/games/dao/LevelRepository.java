package com.challenge.games.dao;

import com.challenge.games.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Query("SELECT l FROM Level l WHERE l.name = :levelType")
    Optional<Level> findByName(@Param("levelType") Level.LevelType levelType);
}
