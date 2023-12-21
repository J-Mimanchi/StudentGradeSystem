package com.View;

import com.Handler.AddStudentViewHandler;
import com.member_information.StudentDo;

import javax.swing.*;
import java.awt.*;

public class AddStudentView extends JDialog {


    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel nameLabel = new JLabel("姓名",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel noLabel = new JLabel("学号",JLabel.RIGHT);
    JTextField noTxt = new JTextField();
    JLabel homeLabel = new JLabel("家乡",JLabel.RIGHT);
    JTextField homeTxt = new JTextField();
    JLabel cnLabel = new JLabel("语文成绩",JLabel.RIGHT);
    JTextField cnTxt = new JTextField();
    JLabel mathLabel = new JLabel("数学",JLabel.RIGHT);
    JTextField mathTxt = new JTextField();
    JLabel enLabel = new JLabel("英语成绩",JLabel.RIGHT);
    JTextField enTxt = new JTextField();
    JButton addBtn = new JButton("添加");

    AddStudentViewHandler addStudentViewHandler;


    public AddStudentView(MainView mainView){
        super(mainView,"添加学生",true);

        addStudentViewHandler = new AddStudentViewHandler(this,mainView);
        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);
        noLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLabel);
        noTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(noTxt);
        homeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(homeLabel);
        homeTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(homeTxt);
        cnLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(cnLabel);
        cnTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(cnTxt);
        mathLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(mathLabel);
        mathTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(mathTxt);
        enLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(enLabel);
        enTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(enTxt);

        //事件侦听
        addBtn.addActionListener(addStudentViewHandler);
        jPanel.add(addBtn);


        Container contentPane = getContentPane();
        contentPane.add(jPanel);


        setSize(350,500);
        setLocationRelativeTo(null);
        //销毁当前窗体不是退出整个程序
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public StudentDo buildStudentDo(){
        StudentDo studentDo = new StudentDo();
        studentDo.setName(nameTxt.getText());
        studentDo.setNo(noTxt.getText());
        studentDo.setHomeTown(homeTxt.getText());
        studentDo.setCnScore(Double.valueOf(cnTxt.getText()));
        studentDo.setEnScore(Double.valueOf(enTxt.getText()));
        studentDo.setMathScore(Double.valueOf(mathTxt.getText()));


        return studentDo;
    }
}
