package dao;

import dto.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase implements Data {

    private final String dbUrl;

    private final String dbUser;

    private final String dbPwd;


    private  String LOAD_SQL = "SELECT user_name,point FROM user_point WHERE type_id=1 ORDER BY point DESC LIMIT 0,5;";

    private  String SAVE_SQL = "INSERT INTO user_point(user_name,point,type_id) VALUES(?,?,?)";

    public  DataBase(HashMap<String,String> param){
        this.dbUrl=param.get("dbUrl");
        this.dbUser=param.get("dbUser");
        this.dbPwd=param.get("dbPwd");
        try {
            Class.forName(param.get("driver"));
        } catch (ClassNotFoundException e) {
            System.out.println("数据库连接驱动未安装");
        }
    }

    @Override
    public List<Player> loadData() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Player> players = new ArrayList<Player>();

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            stmt = con.prepareStatement(LOAD_SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(new Player(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (con != null) con.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return players;
    }

    @Override
    public void saveData(Player player) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            stmt = con.prepareStatement(SAVE_SQL);
            stmt.setObject(1,player.getName());
            stmt.setObject(2,player.getPoint());
            stmt.setObject(3,1);

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (con != null) con.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}