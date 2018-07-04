package test1;

/**
 * Created by Array on 2018/6/27.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener2 extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    public int act = 0;
    private JLabel inputID;
    private JTextField idSearch;
    private JPanel Main;
    private Item item = new Item();

    public Listener2() {
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


    public void searchOp() {//切换到修改商品的界面
        JPanel Panel1 = new JPanel();
        JPanel Panel3 = new JPanel();

        JButton myButton1 = new JButton("查询");
        inputID = new JLabel("请输入商品ID");
        idSearch = new JTextField(10);

        myButton1.addActionListener(this);
        this.setLayout(new GridLayout(1,1));

        Panel1.add(inputID);
        Panel1.add(idSearch);
        Panel3.add(myButton1);
        this.add(Panel1);
        this.add(Panel3);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    //        JSONObject json = JSONObject.fromObject(itemInfo);//把get到的商品信息转换成一个JSon对象


    //        JPanel Panel1 = new JPanel();
//        JPanel Panel2 = new JPanel();
//        JPanel Panel3 = new JPanel();
//
//        JButton myButton1 = new JButton("确认修改");
//        inputID= new JLabel("请输入商品ID");
//        idTF = new JTextField(10);
//        inputPrice = new JLabel("请输入商品价格");
//        price = new JTextField(10);
//        inputName = new JLabel("请输入商品名称");
//        name = new JTextField(10);
//        inputDomain = new JLabel("请输入商品domain");
//        domain = new JTextField(10);
//
//        myButton1.addActionListener(this);
//        this.setLayout(new GridLayout());
//
//        Panel1.add(inputID);
//        Panel1.add(idTF);
//        Panel1.add(inputName);
//        Panel1.add(name);
//        Panel2.add(inputPrice);
//        Panel2.add(price);
//        Panel2.add(inputDomain);
//        Panel2.add(domain);
//        Panel3.add(myButton1);
//        this.add(Panel1);
//        this.add(Panel2);
//        this.add(Panel3);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 600);
//        setVisible(true);

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("查询商品")) {
            Main.setVisible(false);
            searchOp();  //从主面板切换到查询商品的界面
        }

    }

    public static void main(String[] args) {
        new Listener2();
    }
}


//        secondlCommand = lastCommand;
//        lastCommand = cmd;
//
//        if(cmd.equals("上一步")){
//            if(secondlCommand.equals("添加商品")||secondlCommand.equals("删除商品")
//                    ||secondlCommand.equals("查询商品")||secondlCommand.equals("修改商品")
//                    ||secondlCommand.equals("绑定标签")){
////                this.setVisible(false);
//                this.removeAll();
//                this.repaint();
//                this.validate();
//                Main.setVisible(true);
//
//            }
//            //根据lastcommand判断回到哪个界面
//        }
//private String lastCommand = "查询商品", secondlCommand;