package com.View;

import com.Handler.LoginHandler;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {


    JLabel nameLabel = new JLabel("学生成绩管理系统",JLabel.CENTER);
    //创建流式 创建面板
    // start
    FlowLayout flowLayout = new FlowLayout();
    JPanel Jp1 = new JPanel(flowLayout);
    JPanel Jp2 = new JPanel(flowLayout);
    JPanel Jp3 = new JPanel(flowLayout);
    //end
    JLabel userNameLabel = new JLabel("用户名：");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码：");
    JPasswordField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");


    LoginHandler loginHandler=null;
    public LoginView(){
        super("登录系统");
        loginHandler = new LoginHandler(this);


        Container contentPane = getContentPane();//获取JFrame的面板

        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));

        Font centerFont = new Font("楷体",Font.PLAIN,20);//创建字体类对象  应用字体
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(150,30));//Dimension类封装一个构件的高度和宽度
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(150,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        //把组件添加进面板
        Jp1.add(userNameLabel);
        Jp1.add(userTxt);
        Jp2.add(pwdLabel);
        Jp2.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        Jp3.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        Jp3.add(resetBtn);
        GridLayout gridLayout = new GridLayout(3,1);
        JPanel view = new JPanel(gridLayout);
        view.add(Jp1);
        view.add(Jp2);
        view.add(Jp3);

        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(view,BorderLayout.CENTER);



        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new LoginView();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

}
