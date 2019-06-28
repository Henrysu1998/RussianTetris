package ui;

import config.GameConfig;

import javax.swing.*;
import java.awt.*;;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Img {

    private Img(){}

    /**
     * 个人签名
     */
    public static Image SIGN=new ImageIcon("graphics/default/string/name.png").getImage();
    /**
     * 窗口图片
     */
    public static Image WINDOW=new ImageIcon("graphics/default/window/Window.png").getImage();
    /**
     * 数字图片 250 32
     */
    public static Image NUMBER=new ImageIcon("graphics/default/string/numbers.png").getImage();
    /**
     * 矩形值槽
     */
    public static Image RECT=new ImageIcon("graphics/default/window/exp.png").getImage();
    /**
     * 数据库窗口标题
     */
    public static Image DB=new ImageIcon("graphics/default/string/dataBase.png").getImage();
    /**
     * 本地记录窗口标题
     */
    public static Image DISK=new ImageIcon("graphics/default/string/disk.png").getImage();
    /**
     * 方块图片
     */
    public static final Image ACT=new ImageIcon("graphics/default/game/rect.png").getImage();
    /**
     * 等级标题图片
     */
    public static final Image LEVEL=new ImageIcon("graphics/default/string/level.png").getImage();

    /**
     * 分数标题图片
     */
    public static final Image POINT=new ImageIcon("graphics/default/string/score.png").getImage();
    /**
     * 消行标题图片
     */
    public static final Image RMLINE=new ImageIcon("graphics/default/string/rmline.png").getImage();
    /**
     * 阴影
     */
    public static final Image SHADOW=new ImageIcon("graphics/default/game/shadow.png").getImage();
    /**
     * 开始按钮图片
     */
    public static final ImageIcon BTN_START=new  ImageIcon("graphics/default/string/start.png");
    /**
     *设置按钮图片
     */
    public static final ImageIcon BTN_CONFIG=new  ImageIcon("graphics/default/string/config.png");

    /**
     * 暂停按钮
     */
    public static final Image PAUSE=new  ImageIcon("graphics/default/string/pause.png").getImage();
    /**
     * 下一个图片数组
     */
    public static Image[] NEXT_ACT;

    public  static List<Image> BG_LIST;

    static {
        //下一个方块图片
        NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
        for (int i = 0; i <NEXT_ACT.length ; i++) {
            NEXT_ACT[i]=new ImageIcon("graphics/default/game/"+i+".png").getImage();
        }

        //背景图片数组
        File dir=new File("graphics/default/background");
        File[] files=dir.listFiles();
        BG_LIST=new ArrayList<Image>();
        for (File file:files) {
            if(file.isDirectory()){
                continue;
            }
            BG_LIST.add(new ImageIcon(file.getPath()).getImage());
        }

    }
}
