package com.challenge.games.dao;

import com.challenge.games.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerGameRepository extends JpaRepository<PlayerGame, Integer>
{
    @Query("SELECT pg FROM PlayerGame pg JOIN pg.player p JOIN pg.level l WHERE pg.game.id = :gameId AND l.name = :levelName AND p.location = :location")
    List<PlayerGame> findByGameIdAndLevelNameAndPlayerLocation(@Param("gameId") Integer gameId, @Param("levelName") Level.LevelType levelType, @Param("location") Location location);

    @Query("SELECT pg FROM PlayerGame pg WHERE pg.player.id = :playerId AND pg.game.id = :gameId")
    Optional<PlayerGame> findByPlayerIdAndGameId(@Param("playerId") Integer playerId, @Param("gameId") Integer gameId);

    @Query("SELECT COUNT(pg) > 0 FROM PlayerGame pg WHERE pg.player = :player AND pg.game = :game AND pg.level = :level")
    boolean existsByPlayerAndGameAndLevel(@Param("player") Player player, @Param("game") Game game, @Param("level") Level level);

}


