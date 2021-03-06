package test1;

/**
 * Created by Array on 2018/6/27.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Listener extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    public int act = 0;
    private JLabel inputID,inputPrice,inputDomain,inputName,inputDescription;
    private JTextField viewID, viewPrice,viewName,viewDescription;
    private JTextField ipID,ipPrice,ipName,ipDomain,ipDescription;
    private JTextField searchID;
    private JTextField ipEslCode;
    private JPanel Main,viewPanel = new JPanel(),viewPanel2 = new JPanel(),viewPanel3 = new JPanel();
    private JPanel addPanel = new JPanel(),addPanel2 = new JPanel(),addPanel3 = new JPanel();
    private JPanel bindPanel = new JPanel(), bindPanel2 = new JPanel();
    private Item item = new Item();
    private JPanel SearchPanel = new JPanel();

    public Listener(){
        setTitle("电子价签");
        Main = new JPanel();

        JButton add = new JButton("添加商品");
        add.addActionListener(this);
        JButton searchID = new JButton("查询商品");
        searchID.addActionListener(this);
        JButton bind = new JButton("绑定标签");
        bind.addActionListener(this);
        JButton searchMediaID = new JButton("查询标签");
        searchMediaID.addActionListener(this);

        Main.add(add);
        Main.add(searchID);
        Main.add(bind);
        this.add(Main);

        this.setLayout(new GridLayout(4,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void addOp(){//切换到添加商品的界面

        JButton myButton1 = new JButton("确认添加");
        JLabel inputID= new JLabel("请输入商品ID");
        ipID = new JTextField(10);
        JLabel inputPrice = new JLabel("请输入商品价格");
        ipPrice = new JTextField(10);
        JLabel inputName = new JLabel("请输入商品名称");
        ipName = new JTextField(10);
        JLabel inputDescription = new JLabel("请输入商品描述");
        ipDescription = new JTextField(10);

        myButton1.addActionListener(this);

        addPanel.add(inputID);
        addPanel.add(ipID);
        addPanel.add(inputName);
        addPanel.add(ipName);
        addPanel2.add(inputPrice);
        addPanel2.add(ipPrice);
        addPanel2.add(inputDescription);
        addPanel2.add(ipDescription);
        addPanel3.add(myButton1);
        this.add(addPanel);
        this.add(addPanel2);
        this.add(addPanel3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void deleteOp() {
    }

    public void searchOp(){//切换到查询商品的界面
        JButton myButton1 = new JButton("查询");
        inputID= new JLabel("请输入商品ID");
        searchID = new JTextField(10);
        myButton1.addActionListener(this);

        SearchPanel.add(inputID);
        SearchPanel.add(searchID);
        SearchPanel.add(myButton1);
        this.add(SearchPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void clear(){
        SearchPanel.removeAll();
        viewPanel.removeAll();
        viewPanel2.removeAll();
        viewPanel3.removeAll();
        addPanel.removeAll();
        addPanel2.removeAll();
        addPanel3.removeAll();
        bindPanel.removeAll();
        bindPanel2.removeAll();
        this.revalidate();
        this.repaint();
    }
    public void viewResult() throws UnirestException{
        httpreq req = new httpreq();
        item.setId(searchID.getText());
        String itemInfo =  req.doGet(item);

        //将返回的JSON字符串转换成一个Item对象
        ObjectMapper objectMapper = new ObjectMapper();
        Item searchedItem = null;
        try {
            searchedItem = objectMapper.readValue(itemInfo, Item.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel idInfo = new JLabel("商品ID");
        viewID = new JTextField(searchedItem.getId());
        viewID.setColumns(10);
        JLabel nameInfo = new JLabel("商品名称");
        viewName = new JTextField(searchedItem.getName());
        viewName.setColumns(10);
        JLabel priceInfo = new JLabel("商品价格");
        viewPrice = new JTextField(searchedItem.getPrice() + "");
        viewPrice.setColumns(10);
        JLabel descriptionInfo = new JLabel("商品描述");
        viewDescription = new JTextField(searchedItem.getDescription());
        viewDescription.setColumns(10);

        JButton modi = new JButton("修改");
        modi.addActionListener(this);
        JButton del = new JButton("删除");
        del.addActionListener(this);

        viewPanel.add(idInfo);
        viewPanel.add(viewID);
        viewPanel.add(nameInfo);
        viewPanel.add(viewName);
        viewPanel2.add(priceInfo);
        viewPanel2.add(viewPrice);
        viewPanel2.add(descriptionInfo);
        viewPanel2.add(viewDescription);
        viewPanel3.add(modi);
//        viewPanel3.add(del);

        this.add(viewPanel);
        this.add(viewPanel2);
        this.add(viewPanel3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void bindOp(){//切换到绑定标签的界面
        JLabel EslCode = new JLabel("请输入价签的ESL码");
        ipEslCode = new JTextField(10);
        JLabel ID = new JLabel("请输入商品ID");
        ipID = new JTextField(10);
        JButton bind = new JButton("绑定");
        bind.addActionListener(this);
        JButton unbind = new JButton("解绑");
        unbind.addActionListener(this);

        bindPanel.add(EslCode);
        bindPanel.add(ipEslCode);
        bindPanel.add(ID);
        bindPanel.add(ipID);
        bindPanel2.add(bind);
        bindPanel2.add(unbind);
        this.add(bindPanel);
        this.add(bindPanel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void doBind(){
        item.setId(ipID.getText());
        httpreq req = new httpreq();
        try {
            req.bind(item,ipEslCode.getText());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        if(req.getStatusCode() == 200){
            JOptionPane.showMessageDialog(this,"绑定成功");
        }
        else{
            JOptionPane.showMessageDialog(this,"Error! Status Code = " + req.getStatusCode());
        }
    }

    public void actionPerformed (ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("添加商品")||cmd.equals("查询商品")||cmd.equals("绑定标签")||cmd.equals("查询")) {
            clear();
        }
        if(cmd.equals("添加商品")){
            addOp();//
        }
        if(cmd.equals("确认添加")){
            item.setId(ipID.getText());
            item.setPrice(Double.parseDouble(ipPrice.getText()));
            item.setName(ipName.getText());
            item.setDescription(ipDescription.getText());
            httpreq post = new httpreq();
            try {
                post.doPost(item);
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
            if(post.getStatusCode() == 200){
                JOptionPane.showMessageDialog(this,"添加成功");
            }
            else{
                JOptionPane.showMessageDialog(this,"Error! Status Code = " + post.getStatusCode());
            }
        }

        if(cmd.equals("绑定标签")){//进入输入esl码和商品id的界面
            bindOp();
        }
        if(cmd.equals("绑定")){
            doBind();
        }

        if(cmd.equals("解绑")){
            httpreq req = new httpreq();
            try {
                req.unbind(ipEslCode.getText());
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
            if(req.getStatusCode() == 200){
                JOptionPane.showMessageDialog(this,"解绑成功");
            }
            else{
                JOptionPane.showMessageDialog(this,"Error! Status Code = " + req.getStatusCode());
            }
        }

        if(cmd.equals("查询商品")){
            searchOp();  //显示出查询的文本框
        }
        if(cmd.equals("查询")){//输入商品id，切换到显示商品信息的界面
            SearchPanel.removeAll();
            item.setId(searchID.getText());
                try {
                viewResult();
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
        }

        if(cmd.equals("修改")) {  //提交修改商品的操作
            item.setId(viewID.getText());
            item.setPrice(Double.parseDouble(viewPrice.getText()));
            item.setName(viewName.getText());
            item.setDescription(viewDescription.getText());
            httpreq post = new httpreq();
            try {
                post.doPost(item);
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
            if(post.getStatusCode() == 200){
                JOptionPane.showMessageDialog(this,"修改成功");
            }
            else{
                JOptionPane.showMessageDialog(this,"Error! Status Code = " + post.getStatusCode());
            }
        }
    }

    public static void main(String[] args) {
        new Listener();
    }

}