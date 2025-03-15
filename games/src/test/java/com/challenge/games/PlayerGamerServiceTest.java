package com.challenge.games;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.challenge.games.dao.*;
import com.challenge.games.dto.PlayerDTO;
import com.challenge.games.dto.PlayerGameDTO;
import com.challenge.games.entity.*;
import com.challenge.games.service.PlayerGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PlayerGameServiceTest {

    @Mock
    private PlayerGameRepository playerGameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private PlayerGameService playerGameService;

    private Player player;
    private Game game;
    private Level level;
    private Location location;
    private PlayerGame playerGame;
    private PlayerGameDTO playerGameDTO;

    @BeforeEach
    void setUp() {
        player = new Player("GamerX", new Location("Spain"));
        game = new Game("BlackJack");
        level = new Level(Level.LevelType.PRO);
        location = new Location("Spain");
        playerGame = new PlayerGame(player, game, level);

        playerGameDTO = new PlayerGameDTO(null, 1, "GamerX", "Spain", 1, "BlackJack", "PRO");
    }

    @Test
    void shouldLinkPlayerToGameSuccessfully() {
        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));
        when(levelRepository.findByName(Level.LevelType.PRO)).thenReturn(Optional.of(level));
        when(playerGameRepository.existsByPlayerAndGameAndLevel(player, game, level)).thenReturn(false);

        PlayerGameDTO result = playerGameService.linkPlayerToGame(playerGameDTO);

        assertNotNull(result);
        assertEquals("GamerX", result.getPlayerNickName());
        assertEquals("BlackJack", result.getGameName());
        assertEquals("PRO", result.getLevelType());

        verify(playerGameRepository).save(any(PlayerGame.class));
    }

    @Test
    void shouldThrowExceptionIfPlayerAlreadyLinked() {
        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));
        when(levelRepository.findByName(Level.LevelType.PRO)).thenReturn(Optional.of(level));
        when(playerGameRepository.existsByPlayerAndGameAndLevel(player, game, level)).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> playerGameService.linkPlayerToGame(playerGameDTO));

        assertEquals("400 BAD_REQUEST \"Player already linked to this game and level\"", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfPlayerNotFound() {
        when(playerRepository.findById(1)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> playerGameService.linkPlayerToGame(playerGameDTO));

        assertEquals("404 NOT_FOUND \"Player not found\"", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfGameNotFound() {
        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(gameRepository.findById(1)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> playerGameService.linkPlayerToGame(playerGameDTO));

        assertEquals("404 NOT_FOUND \"Game not found\"", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfLevelNotFound() {
        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(gameRepository.findById(1)).thenReturn(Optional.of(game));
        when(levelRepository.findByName(Level.LevelType.PRO)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> playerGameService.linkPlayerToGame(playerGameDTO));

        assertEquals("400 BAD_REQUEST \"Invalid level type\"", exception.getMessage());
    }

    @Test
    void shouldUpdatePlayerLevelByGameSuccessfully() {
        when(playerGameRepository.findByPlayerIdAndGameId(1, 1)).thenReturn(Optional.of(playerGame));
        when(levelRepository.findByName(Level.LevelType.INVENCIBLE)).thenReturn(Optional.of(new Level(Level.LevelType.INVENCIBLE)));

        PlayerGameDTO result = playerGameService.updatePlayerLevelByGame(1, 1, "INVENCIBLE");

        assertNotNull(result);
        assertEquals("INVENCIBLE", result.getLevelType());
        verify(playerGameRepository).save(playerGame);
    }

    @Test
    void shouldThrowExceptionIfPlayerGameNotFoundOnUpdate() {
        when(playerGameRepository.findByPlayerIdAndGameId(1, 1)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> playerGameService.updatePlayerLevelByGame(1, 1, "PRO"));

        assertEquals("404 NOT_FOUND \"PlayerGame not found\"", exception.getMessage());
    }

    @Test
    void shouldSearchPlayersByGameLevelAndLocationSuccessfully() {
        when(levelRepository.findByName(Level.LevelType.PRO)).thenReturn(Optional.of(level));
        when(locationRepository.findByName("Spain")).thenReturn(Optional.of(location));
        when(playerGameRepository.findByGameIdAndLevelNameAndPlayerLocation(1, level.getName(), location))
                .thenReturn(List.of(playerGame));

        List<PlayerDTO> players = playerGameService.searchPlayersByGameLevelAndLocation(1, "PRO", "Spain");

        assertNotNull(players);
        assertEquals(1, players.size());
        assertEquals("GamerX", players.get(0).getNickName());
    }

    @Test
    void shouldThrowExceptionIfLocationIsInvalidOnSearch() {
        when(levelRepository.findByName(Level.LevelType.PRO)).thenReturn(Optional.of(level));
        when(locationRepository.findByName("InvalidLocation")).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> playerGameService.searchPlayersByGameLevelAndLocation(1, "PRO", "InvalidLocation"));

        assertEquals("400 BAD_REQUEST \"Invalid location\"", exception.getMessage());
    }
}
