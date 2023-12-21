package com.View;

import com.Handler.MainViewHandler;
import com.paged_information.Demand;
import com.paged_information.TableDTO;
import com.service.StudentService;
import com.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("添加");
    JButton updataBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable = new MainViewTable();

    private int pageNow =1;//当前是第几页
    private int pageSize =10;//一页显示多少条记录
    MainViewHandler mainViewHandler;
    public MainView(){
        super("主界面");
        Container contentPane = getContentPane();

        mainViewHandler = new MainViewHandler(this);

        //放置北边的组件
        layoutNorth(contentPane);
        //设置中间的jtable
        layoutCenter(contentPane);
        //放置南边的组件
        layoutSouth(contentPane);

        //设置主界面打大小
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();//通过Toolkit工具类获得屏幕的分辨率
        setSize(screenSize.width,screenSize.height-50);



        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //中间布局
    private void layoutCenter(Container contentPane) {
        TableDTO dto = getTableDTO();

        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(dto.getData());
        //把jtable和model关联

        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO(){
        StudentService studentService = new StudentServiceImpl();
        Demand request = new Demand();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = studentService.retrieveStudents(request);
        return tableDTO;
    }
    //设置南边布局
    private void layoutSouth(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    private void showPreNext(int totalCount){
        if(pageNow==1){
            preBtn.setVisible(false);
        }else{
            preBtn.setVisible(true);
        }
        int pageCount = 0;//总共有多少页
        if(totalCount % pageSize ==0){
            pageCount = totalCount/pageSize;
        }else{
            pageCount = totalCount/pageSize+1;
        }
        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else{
            nextBtn.setVisible(true);
        }
    }

    //设置北边布局
    private void layoutNorth(Container contentPane) {
        //增加事件侦听

        addBtn.addActionListener(mainViewHandler);
        updataBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updataBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }
    public void reloadTable(){
        StudentService studentService = new StudentServiceImpl();
        Demand request = new Demand();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());

        TableDTO dto = getTableDTO();

        MainViewTableModel.updataModel(dto.getData());

        mainViewTable.renderRule();
        showPreNext(dto.getTotalCount());
    }
    public int [] getSelectedStudent(){
        int [] selectedRows = mainViewTable.getSelectedRows();
        int [] ids = new int[selectedRows.length];
        for(int i = 0 ;i< selectedRows.length;i++){
            int rowIndex = selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }
}
