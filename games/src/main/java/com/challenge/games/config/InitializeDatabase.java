package com.challenge.games.config;

import com.challenge.games.dao.*;
import com.challenge.games.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeDatabase implements CommandLineRunner {

    private final LocationRepository locationRepo;
    private final GameRepository gameRepo;
    private final LevelRepository levelRepo;
    private final PlayerRepository playerRepo;
    private final PlayerGameRepository playerGameRepo;

    public InitializeDatabase(
    LocationRepository locationRepo,
    GameRepository gameRepo,
    LevelRepository levelRepo,
    PlayerRepository playerRepo,
    PlayerGameRepository playerGameRepo) {
        this.locationRepo = locationRepo;
        this.gameRepo = gameRepo;
        this.levelRepo = levelRepo;
        this.playerRepo = playerRepo;
        this.playerGameRepo = playerGameRepo;
    }

    @Override
    public void run(String... args) {
        Location spain = locationRepo.save(new Location("Spain"));
        Location usa = locationRepo.save(new Location("United States"));
        Location france = locationRepo.save(new Location("France"));
        Location germany = locationRepo.save(new Location("Germany"));
        Location brazil = locationRepo.save(new Location("Brazil"));

        Level pro = levelRepo.save(new Level(Level.LevelType.PRO));
        Level invincible = levelRepo.save(new Level(Level.LevelType.INVENCIBLE));
        Level noob = levelRepo.save(new Level(Level.LevelType.NOOB));

        Game blackJack = gameRepo.save(new Game("BlackJack"));
        Game poker = gameRepo.save(new Game("Poker"));

        Player player1 = playerRepo.save(new Player("GamerX", spain));
        Player player2 = playerRepo.save(new Player("PlayerY", usa));
        Player player3 = playerRepo.save(new Player("ProPlayer", france));
        Player player4 = playerRepo.save(new Player("NoobPlayer", germany));
        Player player5 = playerRepo.save(new Player("BrazilianGamer", brazil));
        Player player6 = playerRepo.save(new Player("GamerSpain2", spain));


        playerGameRepo.save(new PlayerGame(player1, blackJack, pro));
        playerGameRepo.save(new PlayerGame(player2, poker, noob));
        playerGameRepo.save(new PlayerGame(player3, poker, invincible));
        playerGameRepo.save(new PlayerGame(player4, blackJack, noob));
        playerGameRepo.save(new PlayerGame(player5, blackJack, pro));
        playerGameRepo.save(new PlayerGame(player6, blackJack, pro));
    }
}
