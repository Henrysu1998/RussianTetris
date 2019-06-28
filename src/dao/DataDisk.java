package dao;

import dto.Player;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class DataDisk implements Data{

    private  final String filePath;

    public DataDisk(HashMap<String,String> param){ this.filePath=param.get("path");
    }

    @Override
    public List<Player> loadData() {
        ObjectInputStream ois=null;
        List<Player> players=null;
        try {
            ois=new ObjectInputStream(new FileInputStream(filePath));
            players=(List<Player>)ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    @Override
    public void saveData(Player player) {
        //先取出数据
         List<Player> players=this.loadData();

        //TODO P)判断记录是否超过五条，如果超过五条，去掉分数低的

        //追加新记录
        players.add(player);
        //重新写入
        ObjectOutputStream oos = null;
        try {
            oos=new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
