package com.Handler;

import com.View.LoginView;
import com.View.MainView;
import com.member_information.AdminDo;
import com.service.impl.AdminServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//登录事件
public class LoginHandler implements ActionListener {

    private LoginView loginView = null;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("重置".equals(text)) {
            loginView.getUserTxt().setText("");
            loginView.getPwdField().setText("");
        } else if ("登录".equals(text)) {
            login();
        }

    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        String pwd = new String(loginView.getPwdField().getPassword());
        System.out.println(user + "\n" + pwd);

        if((user==null||"".equals(user.trim()))||"".equals(pwd.trim())){
            JOptionPane.showMessageDialog(loginView, "用户名及密码不能为空");
            return;
        }
        //查询数据库
        AdminServiceImpl adminService = new AdminServiceImpl();
        AdminDo adminDo = new AdminDo();
        adminDo.setUserName(user);
        adminDo.setPwd(pwd);
        boolean flag = adminService.validataAdmin(adminDo);
        if (flag) {
            //跳转到主界面并销毁登陆界面
            new MainView();
            loginView.dispose();
        } else {
            JOptionPane.showMessageDialog(loginView, "用户名或密码错误");//消息对话框
        }
    }
}
