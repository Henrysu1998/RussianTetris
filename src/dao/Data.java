package dao;


import dto.Player;

import java.util.List;

/**
 * 数据持久层接口
 */
public interface Data {

    /**
     * 存储数据
     * @return
     */
    public List<Player> loadData();

    /**
     * 存储数据
     * @param player
     */
    public void saveData(Player player);


}
