package com.View;

import com.Handler.UpdataStudentViewHandler;
import com.member_information.StudentDo;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentView extends JDialog{
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLabel = new JLabel("学生编号",JLabel.RIGHT);
    JTextField idTxt = new JTextField();
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
    JButton updataBtn = new JButton("修改");

    UpdataStudentViewHandler updataStudentViewHandler;

    public UpdateStudentView(MainView mainView, int selectedStudentId){
        super(mainView,"修改学生",true);

        updataStudentViewHandler = new UpdataStudentViewHandler(this,mainView);

        //查询selecedStudentId对应的记录并回显
        StudentService studentService = new StudentServiceImpl();
        StudentDo selectedStu = studentService.getById(selectedStudentId);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectedStu.getId().toString());
        //设置id不可编辑
        idTxt.setEnabled(false);
        jPanel.add(idTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameTxt.setText(selectedStu.getName().trim());
        jPanel.add(nameTxt);

        noLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLabel);
        noTxt.setPreferredSize(new Dimension(200,30));
        noTxt.setText(selectedStu.getNo().trim());
        jPanel.add(noTxt);

        homeLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(homeLabel);
        homeTxt.setPreferredSize(new Dimension(200,30));
        homeTxt.setText(selectedStu.getHomeTown().trim());
        jPanel.add(homeTxt);

        cnLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(cnLabel);
        cnTxt.setPreferredSize(new Dimension(200,30));
        cnTxt.setText(String.valueOf(selectedStu.getCnScore()));
        jPanel.add(cnTxt);

        mathLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(mathLabel);
        mathTxt.setPreferredSize(new Dimension(200,30));
        mathTxt.setText(String.valueOf(selectedStu.getMathScore()));
        jPanel.add(mathTxt);

        enLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(enLabel);
        enTxt.setPreferredSize(new Dimension(200,30));
        enTxt.setText(String.valueOf(selectedStu.getEnScore()));
        jPanel.add(enTxt);


        updataBtn.addActionListener(updataStudentViewHandler);
        jPanel.add(updataBtn);


        Container contentPane = getContentPane();
        contentPane.add(jPanel);


        setSize(350,500);
        setLocationRelativeTo(null);
        //销毁当前窗体不是退出整个程序
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //获取修改后的学生对象
    public StudentDo buildUpdataStudentDo() {
        StudentDo studentDo = new StudentDo();
        studentDo.setId(Integer.valueOf(idTxt.getText()));
        studentDo.setName(nameTxt.getText());
        studentDo.setNo(noTxt.getText());
        studentDo.setHomeTown(homeTxt.getText());
        studentDo.setCnScore(Double.valueOf(cnTxt.getText()));
        studentDo.setEnScore(Double.valueOf(enTxt.getText()));
        studentDo.setMathScore(Double.valueOf(mathTxt.getText()));


        return studentDo;
    }
}
