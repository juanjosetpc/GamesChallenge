package com.challenge.games.dao;

import com.challenge.games.entity.PlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerGameRepository extends JpaRepository<PlayerGame, Integer> {
}
