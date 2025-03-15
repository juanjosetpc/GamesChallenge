package com.challenge.games.controller.rest;

import com.challenge.games.dto.PlayerDTO;
import com.challenge.games.dto.PlayerGameDTO;
import com.challenge.games.service.PlayerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.List;

@RestController
@RequestMapping("/api/player-game")
public class PlayerGameController {

    @Autowired
    protected PlayerGameService playerGameService;

    @PostMapping
    public ResponseEntity<PlayerGameDTO> linkPlayerToGame(@RequestBody @Valid PlayerGameDTO playerGame) {
        PlayerGameDTO playerGameDTO = playerGameService.linkPlayerToGame(playerGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerGameDTO);
    }

    @PutMapping("/{playerId}/{gameId}/level/{levelType}")
    public ResponseEntity<PlayerGameDTO> updatePlayerLevelByGame(@PathVariable @NotNull Integer playerId, @PathVariable @NotNull Integer gameId, @PathVariable @NotNull @Pattern(regexp = "INVENCIBLE|PRO|NOOB") String levelType) {
        PlayerGameDTO playerGameDTO = playerGameService.updatePlayerLevelByGame(playerId, gameId, levelType);
        return ResponseEntity.ok(playerGameDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlayerDTO>> searchPlayersByGameLevelAndLocation(@RequestParam Integer gameId, @RequestParam @Pattern(regexp = "INVENCIBLE|PRO|NOOB") String levelType, @RequestParam String location) {
        List<PlayerDTO> players = playerGameService.searchPlayersByGameLevelAndLocation(gameId, levelType, location);
        return ResponseEntity.ok(players);
    }
}
