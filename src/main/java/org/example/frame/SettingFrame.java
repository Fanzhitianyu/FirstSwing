package org.example.frame;

import org.example.Utils.AnyThingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static org.example.frame.MainFrame.container;

public class SettingFrame {
    //private static JFrame frame;
    public static JDialog jDialog;
    public static ImageIcon icon;
    public static Image image;
    public static JButton Setting;
    public static void SettingButton(JFrame frame){
        Setting=new JButton();
        image=new ImageIcon("src\\main\\resources\\icon\\setting.png").getImage();
        icon=new ImageIcon();
        icon.setImage(image.getScaledInstance(frame.getWidth()/15, frame.getWidth()/15,Image.SCALE_SMOOTH));
        Setting.setIcon(icon);
        Setting.setToolTipText("\u8bbe\u7f6e");
        Setting.setBounds(frame.getSize().width-frame.getSize().width/10,frame.getSize().height/20, frame.getWidth()/15, frame.getWidth()/15);
        container.add(Setting);
        setjDialog(frame);//设置界面
        Setting.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(true);
            }
        });
        //窗口变动，图形自适应

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SettingButtonChange(frame);

                super.componentResized(e);
            }
        });
    }


    public static void setjDialog(JFrame frame){
        jDialog=new JDialog(frame,"\u8bbe\u7f6e",true);
        jDialog.setSize(frame.getSize().width/2,frame.getSize().height/2);

        Container container1=jDialog.getContentPane();
        //此处添加设置
        container1.add(new JLabel("\u8bbe\u7f6e"));

        jDialog.setBounds(frame.getX()+frame.getSize().width/2-jDialog.getSize().width/2,frame.getY()+frame.getSize().height/2-jDialog.getSize().height/2, frame.getSize().width/2,frame.getSize().height/2);

    }

    public static void SettingButtonChange(Frame frame){
        icon.setImage(image.getScaledInstance(frame.getWidth()/15, frame.getWidth()/15,Image.SCALE_SMOOTH));
        Setting.setBounds(frame.getSize().width-frame.getSize().width/10,frame.getSize().height/20, frame.getWidth()/15, frame.getWidth()/15);
    }

}
