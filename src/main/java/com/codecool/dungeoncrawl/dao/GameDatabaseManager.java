package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class    GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameDao = new GameStateDaoJdbc(dataSource);
    }

    public PlayerModel savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
        return model;
    }

    public void saveGame(GameMap currentMap, PlayerModel player) {
        GameState game = new GameState(MapLoader.mapToString(currentMap), LocalDateTime.now().toString(), player);
        gameDao.add(game);
    }

    public GameState loadGame(int id) {
        return gameDao.get(id);
    }

    public Player loadPlayer(int id) {
        PlayerModel currentPlayer = gameDao.get(id).getPlayer();
        Player loadedPlayer = new Player(null);
        return loadedPlayer;
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Map<String, String> envs = System.getenv();
        String dbName = "dungeon";
        String user = envs.get("MY_PSQL_USER");
        String password = envs.get("MY_PSQL_PASSWORD");
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public List<String> getLoadNames(){
        return playerDao.getPlayerNames();
    }
}
