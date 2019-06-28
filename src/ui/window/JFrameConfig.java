package ui.window;

import control.GameControl;
import util.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JFrameConfig extends JFrame {


    private JButton btnOk = new JButton("确定");

    private JButton btnCancel = new JButton("取消");

    private JButton btnUse = new JButton("应用");

    private TextCtrl[] keyText = new TextCtrl[8];

    private JLabel errorMsg=new JLabel();

    private GameControl gameControl;

    private final static String[] METHOD_NAMES = {
            "KeyRight", "KeyUp", "KeyLeft", "KeyDown",
            "KeyFunLeft", "KeyFunUp", "KeyFunRight", "KeyFunDown"
    };

    private final static String PATH = "data/control.dat";

    private final static Image IMG_PSP = new ImageIcon("graphics/default/psp.jpg").getImage();




    public JFrameConfig(GameControl gameControl) {
        //获得游戏控制器悐
        this.gameControl=gameControl;
        //标题
        this.setTitle("设置");
        //设置布局管理器为 “边界布局”
        this.setLayout(new BorderLayout());
        //初始化按键输入框
        this.initkeyTexts();
        //添加主面板
        this.add(createMainPanel(), BorderLayout.CENTER);
        //添加按钮面板
        this.add(this.createButtonPanel(), BorderLayout.SOUTH);
        //设置不能改变大小
        this.setResizable(false);
        //设置窗口大小
        this.setSize(710, 408);
        //居中
        FrameUtil.setFrameCenter(this);
    }

    /**
     * 初始化按键输入框
     */
    private void initkeyTexts() {
        int x = 0;
        int y = 10;
        int w = 80;
        int h = 20;

        for (int i = 0; i < 4; i++) {
            keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
            y += 60;

        }

        x = 620;
        y = 15;
        for (int i = 4; i < 8; i++) {
            keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
            y += 60;

        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            HashMap<Integer, String> cfgSet = (HashMap<Integer, String>) ois.readObject();
            ois.close();
            Set<Map.Entry<Integer, String>> entrySet = cfgSet.entrySet();
            for (Map.Entry<Integer, String> e : entrySet
                    ) {

                for (TextCtrl tc : keyText
                        ) {
                    if (tc.getMethodName().equals(e.getValue())) {
                        tc.setKeyCode(e.getKey());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建按钮面板
     *
     * @return
     */
    private JPanel createButtonPanel() {
        //创建按钮面板，流式布局
        JPanel Jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //给确定按钮增加事件监听
        this.btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(writeConfig()) {
                    setVisible(false);
                    gameControl.setOver();
                }

            }
        });
        this.errorMsg.setForeground(Color.RED);
        Jp.add(this.errorMsg);
        Jp.add(this.btnOk);

        //给取消按钮增加事件监听
        this.btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameControl.setOver();
            }
        });
        Jp.add(this.btnCancel);

        //给应用按钮增加事件监听
        this.btnUse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeConfig();
            }
        });
        Jp.add(this.btnUse);

        return Jp;
    }

    /**
     * 创建主面板（选项卡面板）
     *
     * @return
     */
    private JTabbedPane createMainPanel() {
        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("控制设置", this.createControlPanel());
        jtp.addTab("皮肤设置", new JLabel("皮肤"));
        return jtp;
    }


    /**
     * 玩家控制设置面板
     *
     * @return
     */
    private JPanel createControlPanel() {
        JPanel jp = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                g.drawImage(IMG_PSP, 0, 0, null);
            }

        };
        //设置布局管理器
        jp.setLayout(null);
        for (int i = 0; i < keyText.length; i++) {
            jp.add(keyText[i]);
        }

        return jp;

    }

    /**
     * 写入游戏配置
     */
    private boolean writeConfig() {
        HashMap<Integer, String> keySet = new HashMap<Integer, String>();
        for (int i = 0; i < keyText.length; i++) {
            int keyCode=this.keyText[i].getKeyCode();
            if(keyCode==0){
                this.errorMsg.setText("错误按键");
                return false;
            }
            keySet.put(keyCode, this.keyText[i].getMethodName());
        }
        if(keySet.size()!=8){
            this.errorMsg.setText("重复按键");
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(keySet);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.errorMsg.setText(e.getMessage());
            return false;
        }
        this.errorMsg.setText(null);
        return true;

    }



}