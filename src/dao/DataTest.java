package dao;

import dto.Player;

import java.util.ArrayList;
import java.util.List;

public class  DataTest implements Data {

    @Override
    public List<Player> loadData() {
        List<Player> players=new ArrayList<Player>();
        players.add(new Player("dd",100));
        players.add(new Player("dd",1000));
        players.add(new Player("dd",2000));
        players.add(new Player("dd",3000));
        players.add(new Player("dd",4000 ));
        return players;
    }

    @Override
    public void saveData(Player player) {
        System.out.println();
    }
}
