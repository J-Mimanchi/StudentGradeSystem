package com.Handler;

import com.View.AddStudentView;
import com.View.MainView;
import com.member_information.StudentDo;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentViewHandler implements ActionListener {
    private AddStudentView addStudentView;
    private MainView mainView;
    public AddStudentViewHandler(AddStudentView addStudentView, MainView mainView){
        this.addStudentView = addStudentView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)){

            StudentService studentService = new StudentServiceImpl();
            StudentDo studentDo = addStudentView.buildStudentDo();
            boolean addResult = studentService.add(studentDo);
            if(addResult){
                //重新加载表格获取最新数据
                JOptionPane.showMessageDialog(addStudentView,"添加成功");
                mainView.reloadTable();
                addStudentView.dispose();

            }else{
                JOptionPane.showMessageDialog(addStudentView,"添加失败");
            }
        }
    }
}
