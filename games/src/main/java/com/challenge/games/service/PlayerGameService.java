package com.challenge.games.service;

import com.challenge.games.dao.*;
import com.challenge.games.dto.PlayerDTO;
import com.challenge.games.dto.PlayerGameDTO;
import com.challenge.games.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerGameService {
    @Autowired
    private PlayerGameRepository playerGameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private LocationRepository locationRepository;

    public PlayerGameDTO linkPlayerToGame(PlayerGameDTO playerGameDTO) {

        Integer playerId = playerGameDTO.getPlayerId();
        Integer gameId = playerGameDTO.getGameId();
        String levelType = playerGameDTO.getLevelType();

        Player player = playerRepository.findById(playerId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        Game game = gameRepository.findById(gameId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        Level level = levelRepository.findByName(Level.LevelType.valueOf(levelType))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid level type"));

        boolean exists = playerGameRepository.existsByPlayerAndGameAndLevel(player, game, level);
        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player already linked to this game and level");
        }

        PlayerGame playerGame = new PlayerGame(player, game, level);

        playerGameRepository.save(playerGame);

        return new PlayerGameDTO(
                playerGame.getId(),
                player.getId(),
                player.getNickName(),
                player.getLocation().getName(),
                game.getId(),
                game.getName(),
                level.getName().name()
        );
    }

    public PlayerGameDTO updatePlayerLevelByGame(Integer playerId, Integer gameId, String levelType) {
        PlayerGame playerGame = playerGameRepository.findByPlayerIdAndGameId(playerId, gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlayerGame not found"));

        Level level = levelRepository.findByName(Level.LevelType.valueOf(levelType))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid level type"));

        playerGame.setLevel(level);
        playerGameRepository.save(playerGame);

        return playerGame.toDTO();
    }

    public List<PlayerDTO> searchPlayersByGameLevelAndLocation(Integer gameId, String levelType, String locationName) {
        Level level = levelRepository.findByName(Level.LevelType.valueOf(levelType))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid level type"));

        Location location = locationRepository.findByName(locationName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid location"));

        List<PlayerGame> playerGames = playerGameRepository.findByGameIdAndLevelNameAndPlayerLocation(gameId, level.getName(), location);

        return playerGames.stream()
                .map(playerGame -> playerGame.getPlayer().toDTO())
                .collect(Collectors.toList());
    }
}
