package org.example.frame;

import com.alibaba.excel.util.ListUtils;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import org.example.Controller.ExcelController;
import org.example.Data.EnglishData;
import org.example.Main;
import org.example.Utils.AnyThingUtils;

import javax.crypto.spec.PSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFrame {
    public static JFrame frame;
    public static Container container;
    private static final JLabel Title=new JLabel("English");
    private static final JTextField InputEnglish=new JTextField(50);
    public static void load(){
        ExcelController.readExcel();

        FlatLightLaf.setup();//主题设置+
        FlatLightFlatIJTheme.setup();
        try {
            UIManager.setLookAndFeel( new FlatDarkFlatIJTheme());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //主题设置-
        frame = new JFrame();//创建屏幕
        frame.setBounds(AnyThingUtils.screenSize.width/2-640/2,AnyThingUtils.screenSize.height/2-480/2,640,480);//设置大小
        frame.setSize(640,480);//设置size大小
        container = frame.getContentPane();//获取容器
        frame.setTitle("English");//设置标题
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭
        frame.setIconImage(new ImageIcon("src\\main\\resources\\icon\\icon.png").getImage());

        frame.setContentPane(container);

        mainframe(frame);

        CloseWindowEvent(frame);
    }

    private static void CloseWindowEvent(JFrame frame){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                super.windowClosing(e);
            }
        });
    }

    private static void mainframe(JFrame frame){
        frame.setLayout(null);

        SettingFrame.SettingButton(frame);//设置按钮/设置界面
        mainTitle(frame);
        InputEnglish(frame);

        frame.setVisible(true);//显示

    }

    //Title Start
    private static void mainTitle(JFrame frame){
        Title.setFont(new Font("宋体",Font.BOLD,frame.getSize().width/13));
        Title.setBounds(frame.getSize().width/2-frame.getSize().width/6,frame.getSize().height/20,frame.getSize().width/3,frame.getSize().height/9);
        Title.setForeground(new Color(240,239,227));
        container.add(Title);
        TitleChange(frame);
    }

    private static void TitleChange(JFrame frame){
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Title.setBounds(frame.getSize().width/2-frame.getSize().width/6,frame.getSize().height/20-frame.getSize().height/18,frame.getSize().width/3,frame.getSize().height/5);
                Title.setFont(new Font("宋体",Font.BOLD,frame.getSize().width/15));
                super.componentResized(e);
            }
        });
    }
    //Title End

    private static void InputEnglish(JFrame frame){
        InputEnglish.setBackground(new Color(43, 45, 48));
        InputEnglish.setForeground(new Color(43, 45, 48));
        InputEnglish.setBounds(frame.getBounds().width/2,frame.getBounds().height/2,frame.getBounds().width/2-frame.getBounds().width/20,frame.getBounds().height/10);;
        InputEnglish.setForeground(new Color(240,239,227));
        InputEnglish.setCaretColor(new Color(187, 176, 53));
        container.add(InputEnglish);
        InputEnglishChange(frame);
    }
    private static void InputEnglishChange(Frame frame){
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                InputEnglish.setBounds(frame.getBounds().width/2,frame.getBounds().height/2,frame.getBounds().width/2-frame.getBounds().width/20,frame.getBounds().height/10);;
                InputEnglish.setFont(new Font("宋体",Font.BOLD,InputEnglish.getSize().height));
                super.componentResized(e);
            }
        });

        InputEnglish.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar()==KeyEvent.VK_ENTER)
                {
                    List<EnglishData> delList= ListUtils.newArrayList();
                    List<EnglishData> addList= ListUtils.newArrayList();
                    //System.out.println(InputEnglish.getText()+"\n");
                    if (EnglishData.getdataList()!=null){
                        for (EnglishData data:EnglishData.getdataList()){
                           if (data.getEnglish()!=null&&data.getEnglish().equals(InputEnglish.getText())){
                               delList.add(data);
                               addList.add(new EnglishData(data.getEnglish(),data.getTimes()+1));
                               //System.out.println(data.getEnglish()+"-"+data.getTimes());
                           }
                        }
                        if (addList.isEmpty()){
                            if (InputEnglish.getText()!=null) {
                                addList.add(new EnglishData(InputEnglish.getText(), 1));
                            }
                        }
                        //data.Times();
                    }
                    else {
                        if (InputEnglish.getText()!=null) {
                            addList.add(new EnglishData(InputEnglish.getText(), 1));
                        }
                    }
                    for (EnglishData data:addList){
                        System.out.println(data.getEnglish()+data.getTimes());
                    }

                    InputEnglish.setText("");
                    EnglishData.getdataList().removeAll(delList);
                    EnglishData.getdataList().addAll(addList);
                    addList.clear();
                    delList.clear();
                    ExcelController.writeExcel();
                }
            }
        });
    }
}
