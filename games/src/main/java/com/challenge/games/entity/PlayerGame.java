package com.challenge.games.entity;

import com.challenge.games.dto.DTO;
import com.challenge.games.dto.PlayerGameDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "player_game", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"player_id", "game_id", "level_id"})
})public class PlayerGame implements DTO<PlayerGameDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;

    public PlayerGame() {}

    public PlayerGame(Player player, Game game, Level level) {
        this.player = player;
        this.game = game;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    public PlayerGameDTO toDTO() {
        return new PlayerGameDTO(
                this.id,
                this.player.getId(),
                this.player.getNickName(),
                this.player.getLocation() != null ? this.player.getLocation().getName() : null,
                this.game.getId(),
                this.game.getName(),
                this.level != null ? this.level.getName().name() : null
        );
    }
}
