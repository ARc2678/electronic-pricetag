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
    private JLabel inputID,inputPrice,inputDomain,inputName;
    private JTextField viewID, viewPrice,viewName,viewDomain;
    private JTextField ipID,ipPrice,ipName,ipDomain;
    private JTextField searchID;
    private JPanel Main,viewPanel,viewPanel2,addPanel,addPanel2,addPanel3;
    private Item item = new Item();
    private JPanel SearchPanel;

    public Listener(){
        setTitle("电子价签");
        Main = new JPanel();

        JButton add = new JButton("添加商品");
        add.addActionListener(this);
        JButton delete = new JButton("删除商品");
        delete.addActionListener(this);
        JButton modify = new JButton("修改商品");
        modify.addActionListener(this);
        JButton search = new JButton("查询商品");
        search.addActionListener(this);
        JButton bind = new JButton("绑定标签");
        bind.addActionListener(this);

        Main.add(add);
        Main.add(delete);
        Main.add(modify);
        Main.add(search);
        Main.add(bind);
        this.add(Main);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void addOp(){//切换到添加商品的界面
        addPanel = new JPanel();
        addPanel2 = new JPanel();
        addPanel3 = new JPanel();

        JButton myButton1 = new JButton("确认添加");
        inputID= new JLabel("请输入商品ID");
        ipID = new JTextField(10);
        inputPrice = new JLabel("请输入商品价格");
        ipPrice = new JTextField(10);
        inputName = new JLabel("请输入商品名称");
        ipName = new JTextField(10);
        inputDomain = new JLabel("请输入商品domain");
        ipDomain = new JTextField(10);

        myButton1.addActionListener(this);
        this.setLayout(new GridLayout());

        addPanel.add(inputID);
        addPanel.add(ipID);
        addPanel.add(inputName);
        addPanel.add(ipName);
        addPanel2.add(inputPrice);
        addPanel2.add(ipPrice);
        addPanel2.add(inputDomain);
        addPanel2.add(ipDomain);
        addPanel3.add(myButton1);
        this.add(addPanel);
        this.add(addPanel2);
        this.add(addPanel3);
        this.setLayout(new GridLayout(4,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void deleteOp() {//切换到删除商品的界面
        searchOp();//通过id查询需要删除的商品
        try {
            viewResult();//显示要被删除的商品信息
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JButton del = new JButton("删除");
        viewPanel.add(del);
        this.add(viewPanel);

        //删除操作

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void searchOp(){//切换到查询商品的界面
        SearchPanel = new JPanel();
        JButton myButton1 = new JButton("查询");
        inputID= new JLabel("请输入商品ID1");
        searchID = new JTextField(10);
        myButton1.addActionListener(this);

        SearchPanel.add(inputID);
        SearchPanel.add(searchID);
        SearchPanel.add(myButton1);
        this.add(SearchPanel);
        this.setLayout(new GridLayout(4,4));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
    public void viewResult() throws UnirestException{
        viewPanel = new JPanel();
        viewPanel2 = new JPanel();
        httpreq req = new httpreq();
        item.setId(searchID.getText());
        String itemInfo =  req.doGet(item);

        ObjectMapper objectMapper = new ObjectMapper();
        Item searchedItem = null;
        try {
            searchedItem = objectMapper.readValue(itemInfo, Item.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new GridLayout(4,2));

        JLabel idInfo = new JLabel("商品ID");
        viewID = new JTextField(searchedItem.getId());
        JLabel nameInfo = new JLabel("商品名称");
        viewName = new JTextField(searchedItem.getName());
        JLabel priceInfo = new JLabel("商品价格");
        viewPrice = new JTextField(searchedItem.getPrice() + "");
        JLabel domainInfo = new JLabel("商品domain");
        viewDomain = new JTextField(searchedItem.getDomain());

        viewPanel.add(idInfo);
        viewPanel.add(viewID);
        viewPanel.add(nameInfo);
        viewPanel.add(viewName);
        viewPanel2.add(priceInfo);
        viewPanel2.add(viewPrice);
        viewPanel2.add(domainInfo);
        viewPanel2.add(viewDomain);

        this.add(viewPanel);
        this.add(viewPanel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void bindOp(){//切换到绑定标签的界面

    }

    public void modifyOp(){//切换到修改商品的界面
        searchOp();
//        viewPanel3 = new JPanel();
//        JButton modi = new JButton("确认修改");
//        modi.addActionListener(this);
//        viewPanel3.add(modi);
//        this.add(viewPanel3);
//        viewPanel3.setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {
        String cmd = e.getActionCommand();

        if(cmd.equals("添加商品")){
            addOp();//
        }
        if(cmd.equals("确认添加")){
            item.setId(ipID.getText());
            item.setPrice(Integer.parseInt(ipPrice.getText()));
//            if(domain.getText()!= null) {
//                item.setDomain(domain.getText());
//            }
            item.setName(ipName.getText());
            //上面两个get方法，即使set了null值也会导致转json的时候无法忽略掉被赋null值的属性
            httpreq post = new httpreq();
            try {
                post.doPost(item);
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
            item = null;
        }
        if(cmd.equals("删除商品")){
            deleteOp();
        }
//        if(cmd.equals("绑定标签")){
//
//        }

        if(cmd.equals("查询商品")){
            searchOp();  //显示出查询的文本框
        }
        if(cmd.equals("查询")){//输入商品id，切换到显示商品信息的界面
            SearchPanel.setVisible(false);
            item.setId(searchID.getText());
                try {
                viewResult();
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
        }

        if(cmd.equals("修改商品")){
//            viewPanel3.setVisible(false);
            modifyOp();//切换面板到修改商品的界面
        }

        if(cmd.equals("确认修改")) {  //提交修改商品的操作
            item.setId(viewID.getText());
            item.setPrice(Integer.parseInt(viewPrice.getText()));
//            if(domain.getText()!= null) {
//                item.setDomain(domain.getText());
//            }
            item.setName(viewName.getText());
            //上面两个get方法，即使set了null值也会导致转json的时候无法忽略掉被赋null值的属性
            httpreq post = new httpreq();
            try {
                post.doPost(item);
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Listener();
    }

}