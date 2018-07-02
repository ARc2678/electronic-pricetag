package test1;

/**
 * Created by Array on 2018/6/27.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


//繼承JFrame、實作java.awt.event.ActionListener

public class Listener extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    public int act = 0;
    private JLabel inputID,inputPrice,inputName,inputDomain;
    private JTextField idTF, price,name,domain;
    private JPanel Main,searchPanel, viewPanel;
    private Item item = new Item();

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
        search.addActionListener(this);


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
        modifyOp();//
    }

    public void deleteOp() throws UnirestException {//切换到删除商品的界面
        searchOp();//通过id查询需要删除的商品
        viewResult();//显示要被删除的商品信息
        JButton del = new JButton("删除");
        viewPanel.add(del);
        this.add(viewPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void searchOp(){//切换到查询商品的界面
        searchPanel = new JPanel();
        JButton myButton1 = new JButton("查询");
        inputID = new JLabel("请输入商品ID");
        idTF = new JTextField(10);
        myButton1.addActionListener(this);

        searchPanel.add(inputID);
        searchPanel.add(idTF);
        searchPanel.add(myButton1);
        this.add(searchPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
    public void viewResult() throws UnirestException{
        viewPanel = new JPanel();
        //item = 根据id搜到的；
        //显示搜到的信息

        httpreq req = new httpreq();
//        item.setId("6921819716088");
        String itemInfo =  req.doGet(item);

        JSONObject json = JSONObject.fromObject(itemInfo);//把get到的商品信息转换成一个JSon对象
        ObjectMapper objectMapper = new ObjectMapper();
        Item searchedItem = null;
        try {
            searchedItem = objectMapper.readValue(itemInfo, Item.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new GridLayout(4,2));

        JTextField viewID = new JTextField(searchedItem.getId());
        JTextField viewName = new JTextField(searchedItem.getName());
        JTextField viewPrice = new JTextField(searchedItem.getPrice());
        JTextField viewDomain = new JTextField(searchedItem.getDomain());

        viewPanel.add(viewID);
        viewPanel.add(viewName);
        viewPanel.add(viewPrice);
        viewPanel.add(viewDomain);

        this.add(viewPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        viewPanel.setVisible(true);
    }

    public void bindOp(){//切换到绑定标签的界面

    }

    public void modifyOp(){//切换到修改商品的界面
        JPanel Panel1 = new JPanel();
        JPanel Panel2 = new JPanel();
        JPanel Panel3 = new JPanel();

        JButton myButton1 = new JButton("确认修改");
        inputID= new JLabel("请输入商品ID");
        idTF = new JTextField(10);
        inputPrice = new JLabel("请输入商品价格");
        price = new JTextField(10);
        inputName = new JLabel("请输入商品名称");
        name = new JTextField(10);
        inputDomain = new JLabel("请输入商品domain");
        domain = new JTextField(10);

        myButton1.addActionListener(this);
        this.setLayout(new GridLayout(4,2));

        Panel1.add(inputID);
        Panel1.add(idTF);
        Panel1.add(inputName);
        Panel1.add(name);
        Panel2.add(inputPrice);
        Panel2.add(price);
        Panel2.add(inputDomain);
        Panel2.add(domain);
        Panel3.add(myButton1);
        this.add(Panel1);
        this.add(Panel2);
        this.add(Panel3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }


    public void actionPerformed (ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("添加商品")){
            httpreq post = new httpreq();
            try {
                post.doPost(item);
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
        }
//        if(cmd.equals("删除商品")){
//
//        }
//        if(cmd.equals("绑定标签")){
//
//        }


        if(cmd.equals("查询商品")){
            Main.setVisible(false);
            searchOp();  //从主面板切换到查询商品的界面
        }
        if(cmd.equals("查询")){//输入商品id，切换到显示商品信息的界面
            System.out.print("hh"+ idTF.getText());
            searchPanel.setVisible(false);
            item.setId(idTF.getText());
            System.out.print(idTF.getText()+"123");
                try {
                viewResult();
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
        }


        if(cmd.equals("修改商品")){
            Main.setVisible(false);
            modifyOp();//切换面板到修改商品的界面
        }
        if(cmd.equals("确认修改")) {  //提交修改商品的操作
            System.out.print(idTF.getText());
            item.setId(idTF.getText());
            item.setPrice(Integer.parseInt(price.getText()));
            item.setDomain(domain.getText());
            item.setName(name.getText());
            httpreq post = new httpreq();
            try {
                post.doPost(item);
            } catch (UnirestException e1) {
                e1.printStackTrace();
            }
            item = null;//清空数据
        }
    }

    public static void main(String[] args) {

        new Listener();

    }

}