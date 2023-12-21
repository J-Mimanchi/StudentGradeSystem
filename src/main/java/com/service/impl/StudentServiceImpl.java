package com.service.impl;

import com.member_information.StudentDo;
import com.paged_information.Demand;
import com.paged_information.TableDTO;
import com.service.StudentService;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {

    //查找
    @Override
    public TableDTO retrieveStudents(Demand request) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from student  ");
        if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
            sql.append(" where name like '%"+request.getSearchKey()+"%' ");//模糊查找
        }
        sql.append("order by id desc limit ").append(request.getStart()).append(",").append(request.getPageSize());
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();

        try{
            connection = DBUtil.getConn();
            ps = connection.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            //查询记录
            returnDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from student ");
            if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
                sql.append(" where name like '%"+request.getSearchKey()+"%' ");//模糊查找
            }
            ps = connection.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            while(rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(connection);
            DBUtil.closePs(ps);
            DBUtil.closeRs(rs);
        }
        return null;
    }

    //添加
    @Override
    public boolean add(StudentDo studentDo) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into student (name,no,home_town,cn_score,en_score,math_score) ");
        sql.append(" values(?,?,?,?,?,?)");

        Connection connection = null;
        PreparedStatement ps = null;


        try{
            connection = DBUtil.getConn();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1,studentDo.getName());
            ps.setString(2,studentDo.getNo());
            ps.setString(3,studentDo.getHomeTown());
            ps.setDouble(4,studentDo.getCnScore());
            ps.setDouble(5,studentDo.getEnScore());
            ps.setDouble(6,studentDo.getMathScore());
            return ps.executeUpdate()==1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(connection);
            DBUtil.closePs(ps);
        }
        return false;
    }

    @Override
    public StudentDo getById(int selectedStudentId) {
        StringBuilder sql = new StringBuilder("select * from student where id =?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StudentDo studentDo = new StudentDo();

        try{
            connection = DBUtil.getConn();
            ps = connection.prepareStatement(sql.toString());
            ps.setInt(1,selectedStudentId);
            rs = ps.executeQuery();

            while(rs.next()){
                //处理查出来的每一条记录
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String no = rs.getString("no");
                String homeTown = rs.getString("home_town");
                double cnScore = rs.getDouble("cn_score");
                double enScore = rs.getDouble("en_score");
                double mathScore = rs.getDouble("math_score");
                studentDo.setId(id);
                studentDo.setName(name);
                studentDo.setNo(no);
                studentDo.setHomeTown(homeTown);
                studentDo.setCnScore(cnScore);
                studentDo.setEnScore(enScore);
                studentDo.setMathScore(mathScore);

            }

            return studentDo;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(connection);
            DBUtil.closePs(ps);
            DBUtil.closeRs(rs);
        }
        return null;
    }

    //更新
    @Override
    public boolean update(StudentDo studentDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("update student set name = ?,no = ?,home_town = ?,cn_score = ?,en_score = ?,math_score = ?");
        sql.append("where id =?");
        Connection conn = null;
        PreparedStatement ps =null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,studentDo.getName());
            ps.setString(2,studentDo.getNo());
            ps.setString(3,studentDo.getHomeTown());
            ps.setDouble(4,studentDo.getCnScore());
            ps.setDouble(5,studentDo.getEnScore());
            ps.setDouble(6,studentDo.getMathScore());
            ps.setInt(7,studentDo.getId());
            return ps.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }
        return false;
    }

    //删除
    @Override
    public boolean delete(int[] selectedStudent) {
        StringBuilder sql = new StringBuilder();

        sql.append("delete from student  where id in (");
        int length = selectedStudent.length;
        for(int i = 0 ;i < length ; i++){
            if(i==(length-1)){
                sql.append("?");
            }else{
                sql.append("? ,");
            }
        }
        sql.append(")");

        Connection conn = null;
        PreparedStatement ps =null;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());

            for(int i = 0 ;i < length ; i++){
                ps.setInt(i+1,selectedStudent[i]);
            }

            return ps.executeUpdate()==length;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }
        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()){
            //处理查出来的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String homeTown = rs.getString("home_town");
            double cnScore = rs.getDouble("cn_score");
            double enScore = rs.getDouble("en_score");
            double mathScore = rs.getDouble("math_score");
            double totalScore = cnScore+enScore+mathScore;
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(no);
            oneRecord.addElement(homeTown);
            oneRecord.addElement(cnScore);
            oneRecord.addElement(enScore);
            oneRecord.addElement(mathScore);
            oneRecord.addElement(totalScore);
            data.addElement(oneRecord);
        }
        return data;
    }

}
