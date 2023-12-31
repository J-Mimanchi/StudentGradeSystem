package com.View;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String> colums = new Vector<>();
    //设置行的属性
    static {
        colums.addElement("编号");
        colums.addElement("姓名");
        colums.addElement("学号");
        colums.addElement("家乡");
        colums.addElement("语文");
        colums.addElement("数学");
        colums.addElement("英语");
        colums.addElement("总分");
    }
    //将行的属性添加
    private MainViewTableModel(){
        super(null,colums);
    }
    private static MainViewTableModel mainViewTableModel = new MainViewTableModel();

    //显示行的属性
    public static MainViewTableModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,colums);
        return  mainViewTableModel;
    }
    public static void updataModel (Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,colums);
    }
    public static Vector<String>getColums(){
        return colums;
    }
    public boolean isCellEditable(int row,int colum){
        return false;
    }
}
