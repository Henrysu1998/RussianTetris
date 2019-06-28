package ui.window;


import config.FrameConfig;
import config.GameConfig;
import util.FrameUtil;

import javax.swing.*;
public class JFrameGame extends JFrame {

    public JFrameGame(JPanelGame panelGame) {
        //获得游戏配置
        FrameConfig FCFg=GameConfig.getFrameConfig();
        //设置标题
        this.setTitle(FCFg.getTitle());
        //设置默认关闭属性
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口大小
        this.setSize(FCFg.getWidth(),FCFg.getHeight());
        //不允许用户改变窗口大小
        this.setResizable(false);
        //居中
        FrameUtil.setFrameCenter(this);
        //设置默认Panel
        this.setContentPane(panelGame);
        //默认该窗口为显示
        this.setVisible(true);
    }
}
