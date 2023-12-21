package com.service.impl;

import com.member_information.AdminDo;
import com.service.AdminService;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminServiceImpl implements AdminService {


    @Override
    public boolean validataAdmin(AdminDo adminDo) {


        String sql = "select pwd from manger where user_name = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn=DBUtil.getConn();
            if(conn==null){
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,adminDo.getUserName());
            rs = ps.executeQuery();
            while (rs.next()){
                String pwd = rs.getString(1);
                if(adminDo.getPwd().equals(pwd)){
                    return true;
                }
                else{
                    return false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
            DBUtil.closeRs(rs);
        }
        return false;
    }
}
