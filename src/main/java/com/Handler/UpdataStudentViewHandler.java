package com.Handler;

import com.View.MainView;
import com.View.UpdateStudentView;
import com.member_information.StudentDo;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdataStudentViewHandler implements ActionListener {
    private UpdateStudentView updateStudentView;
    private MainView mainView;
    public UpdataStudentViewHandler(UpdateStudentView updateStudentView, MainView mainView){
        this.updateStudentView = updateStudentView;
        this.mainView = mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)){
            StudentService studentService = new StudentServiceImpl();
            StudentDo studentDo = updateStudentView.buildUpdataStudentDo() ;
            boolean updataResult = studentService.update(studentDo);
            if(updataResult){
                JOptionPane.showMessageDialog(updateStudentView,"修改成功");
                mainView.reloadTable();
                updateStudentView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateStudentView,"修改失败");
            }
        }
    }
}
