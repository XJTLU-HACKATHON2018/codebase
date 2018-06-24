/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication39;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javaapplication39.MainUI; 

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.ItemEvent;
import java.util.Dictionary;
import java.util.Hashtable;
//import javaapplication26.StdUI;
//import javaapplication26.TerUI;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ContinueUI extends JFrame implements ActionListener {  

    //定义组件   
    JButton jb1,jb2,jb3=null;  
    JRadioButton jage1,jage2,jage3,jage4,jage5,jage6,jage7,jage8=null;  //年龄段
    JRadioButton jage21,jage22,jage23,jage24,jage25,jage26,jage27,jage28=null;  //年龄段
    JRadioButton jage31,jage32,jage33,jage34,jage35,jage36,jage37,jage38=null;  //年龄段

    JRadioButton jrb21,jrb22,jrb23,jrb24,jrb25,jrb26=null; //学历
    JButton jb31,jb32,jb33,jb34=null; //个数
    JRadioButton jrb41,jrb42,jrb43=null; //结婚
   // JRadioButton jrb61,jrb62,jrb63,jrb64,jrb65,jrb66=null;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9=null;  
    JComboBox weight4,weight5,weight6,weight7,weight8,weight9;
    JLabel Lweight4,Lweight5,Lweight6,Lweight7,Lweight8,Lweight9;
    JLabel jtf,jlb1,jlb2,jlb3,jlb4,jlb5,jlb6,jlb7,jlb31,jlb41,jlb51,jlb42,jlb43,jblank=null;  
    JPasswordField jpf=null;  
    ButtonGroup bg,bg2,bg3,bg4,bg5,bg6,bg7,bg8,bg9=null;  
    private ChangeListener listener;
    private JPanel sliderPanel;
    private JTextField textField,textField2,textField3,textField4,textField5,textField6;
    String name,Sweight,Sweight2,Sweight3,Sweight4,Sweight5,Sweight6,Sweight7,Sweight8,Sweight9,Snum,SnumTemp;
    int Iweight,Iweight2,Iweight3,Iweight4,Iweight5,Iweight6,Iweight7,Iweight8,Iweight9,Inum;
    int edu,age,age2,age3,marry,fam,fam2,fam3,fam4,fam5,fam6;
    private JProgressBar progressBar = new JProgressBar(0,100); //指定最小值，最大值  
    private JSlider slider = new JSlider(0,100); 
   
    private JProgressBar progressBar2 = new JProgressBar(0,100); //指定最小值，最大值  
    private JSlider slider2 = new JSlider(0,100); 
    private JProgressBar progressBar3 = new JProgressBar(0,100); //指定最小值，最大值  
    private JSlider slider3 = new JSlider(0,100); 
    private JProgressBar progressBar4 = new JProgressBar(0,100); //指定最小值，最大值  
    private JSlider slider4 = new JSlider(0,100); 
    private JProgressBar progressBar5 = new JProgressBar(0,100); //指定最小值，最大值  
    private JSlider slider5 = new JSlider(0,100); 
    private JProgressBar progressBar6 = new JProgressBar(0,100); //指定最小值，最大值  
    private JSlider slider6 = new JSlider(0,100); 
    
    
    
    
    
    
    
    
    public static void main(String[] args) {  
          ContinueUI CUI=new ContinueUI("","","","","","","",0,0);  
          
    }  
         
    public ContinueUI(String name2,String Sedu,String Smarry,String num,String weight,String weight2,String weight3,int Iedu,int Imarry)  
    {  
        slider.setValue(0);
        slider2.setValue(0);
        slider3.setValue(0);
        slider4.setValue(0);
        slider5.setValue(0);
        slider6.setValue(0);
        sliderPanel=new JPanel();
        Sweight=weight;//学历
        Sweight2=weight2;//婚姻
        Sweight3=weight3;//子女个数
        Snum=num;
        edu=Iedu;
        marry=Imarry;
        sliderPanel.setLayout(new GridBagLayout());
        Lweight4 = new JLabel("权重：");
        Lweight4.setBounds(135, 120, 40, 20);
        String[] Sweight4 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight4 = new JComboBox(Sweight4);
        weight4.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight4.setSelectedIndex(0);
        
        Lweight5 = new JLabel("权重2：");
        Lweight5.setBounds(135, 120, 40, 20);
        String[] Sweight5 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight5 = new JComboBox(Sweight5);
        weight5.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight5.setSelectedIndex(0);
        
        Lweight6 = new JLabel("权重3：");
        Lweight6.setBounds(135, 120, 40, 20);
        String[] Sweight6 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight6 = new JComboBox(Sweight6);
        weight6.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight6.setSelectedIndex(0);
        
        Lweight7 = new JLabel("权重：");
        Lweight7.setBounds(135, 120, 40, 20);
        String[] Sweight7 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight7 = new JComboBox(Sweight7);
        weight7.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight7.setSelectedIndex(0);
        
        Lweight8 = new JLabel("权重2：");
        Lweight8.setBounds(135, 120, 40, 20);
        String[] Sweight8 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight8 = new JComboBox(Sweight8);
        weight8.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight8.setSelectedIndex(0);
        
        Lweight9 = new JLabel("权重3：");
        Lweight9.setBounds(135, 120, 40, 20);
        String[] Sweight9 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight9 = new JComboBox(Sweight9);
        weight9.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight9.setSelectedIndex(0);

        listener=new ChangeListener(){

        public void stateChanged(ChangeEvent event){

        JSlider source=(JSlider)event.getSource();
        textField.setText(""+source.getValue());
        }
         
        };

//创建组件  
        jb1=new JButton("测试");  
        jb1.setForeground(Color.BLUE);
        jb2=new JButton("上一页");
        jb2.setForeground(Color.BLUE);
        jb3=new JButton("退出");
        jb3.setForeground(Color.BLUE);
        //设置监听  
        jb1.addActionListener(this);  
        jb2.addActionListener(this);  
        jb3.addActionListener(this);  
       // jrb31.addActionListener(this);  
        //jrb32.addActionListener(this);  
        //jrb33.addActionListener(this);  
        //jrb34.addActionListener(this);  
        //jrb35.addActionListener(this);  
        //jrb36.addActionListener(this);  
              
        jb31=new JButton("0");
        jb32=new JButton("1");
        jb33=new JButton("2");
        jb34=new JButton("3");
        //jrb35=new JRadioButton("4");
       // jrb36=new JRadioButton("5");
        bg3=new ButtonGroup();  
        bg3.add(jb31);  
        bg3.add(jb32); 
        bg3.add(jb33);  
        bg3.add(jb34);
    //    bg3.add(jrb35);  
     //   bg3.add(jrb36); 
        
        jage1=new JRadioButton("0-20");  
        jage2=new JRadioButton("20-35");
        jage3=new JRadioButton("35-50");
        jage4=new JRadioButton("50+");

        bg=new ButtonGroup();  
        bg.add(jage1);  
        bg.add(jage2); 
        bg.add(jage3);  
        bg.add(jage4);

        jage1.setSelected(true);  //初始页面默认选择权限为 学生
        jage21=new JRadioButton("0-20");  
        jage22=new JRadioButton("20-35");
        jage23=new JRadioButton("35-50");
        jage24=new JRadioButton("50+");
        bg=new ButtonGroup();  
        bg.add(jage21);  
        bg.add(jage22); 
        bg.add(jage23);  
        bg.add(jage24);

        jage21.setSelected(true);  //初始页面默认选择权限为 学生
        jage31=new JRadioButton("0-20");  
        jage32=new JRadioButton("20-35");
        jage33=new JRadioButton("35-50");
        jage34=new JRadioButton("50+");
        bg=new ButtonGroup();  
        bg.add(jage31);  
        bg.add(jage32); 
        bg.add(jage33);  
        bg.add(jage34);

        jage31.setSelected(true);  //初始页面默认选择权限为 学生


        
        jrb41=new JRadioButton("未婚");
        jrb42=new JRadioButton("已婚");
        jrb43=new JRadioButton("离异");
        bg4=new ButtonGroup();  
        bg4.add(jrb41);  
        bg4.add(jrb42); 
        bg4.add(jrb43);  
        jrb41.setSelected(true);  //初始页面默认选择权限为 学生
    
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();
        jp5=new JPanel();  
        jp6=new JPanel();  
        jp7=new JPanel();  
        jp8=new JPanel();
        jp9=new JPanel();

        jlb1=new JLabel("姓名：");  
        jlb2=new JLabel("教育情况：");  
        jlb3=new JLabel("子女个数："); 
        jlb4=new JLabel("子女年龄："); 
        jlb42=new JLabel("子女年龄2："); 
        jlb43=new JLabel("子女年龄3："); 
        jlb5=new JLabel("婚姻情况：");
        jlb6=new JLabel("亲疏程度：");
        jlb7=new JLabel("值:                      ");
        jblank=new JLabel("                          ");
        Inum=Integer.parseInt( Snum );
        Inum=Inum-1;
        SnumTemp=String.valueOf( Inum);
        jtf=new JLabel(name2+"        "); 
        jlb31=new JLabel(SnumTemp+"        ");
        jlb41=new JLabel(Sedu+"        ");
        jlb51=new JLabel(Smarry+"        ");
        jpf=new JPasswordField(10);  
        //加入到JPanel中  
        jp1.add(jlb1);  
        jp1.add(jtf);  
        
        jp1.add(jlb2);      //学历
        jp1.add(jlb41);  
        
               
        jp2.add(jlb5);      //婚姻
        jp2.add(jlb51);
        
        jp2.add(jlb3);      //子女个数
        jp2.add(jlb31); 
      //  jp3.add(jrb35);  
      //  jp3.add(jrb36); 
        switch(Snum){
            case "1":
            Inum=0;
            jp3.add(jb1);       //添加按钮
            jp3.add(jb2);
            jp3.add(jb3);  
            
            break;
            case "2":
            Inum=1;
            jp3.add(Lweight4);
            jp3.add(weight4);
            jp3.add(jlb4);      //年龄段
            jp3.add(jage1);
            jp3.add(jage2);
            jp3.add(jage3);
            jp3.add(jage4);


            jp4.add(Lweight7);
            jp4.add(weight7);
            jp4.add(jlb6);      //亲疏4
        
            
        slider.setMajorTickSpacing(20);

        slider.setMinorTickSpacing(2);

        slider.setPaintTicks(true);

        slider.setPaintLabels(true);

        Dictionary labelTable=new Hashtable<>();

        labelTable.put(0, new JLabel("疏远"));


        labelTable.put(100, new JLabel("亲密"));

        slider.setLabelTable(labelTable);

        jp4.add(slider);
        
        progressBar.setValue(0); 
        progressBar.setStringPainted(true);//设置进度条将显示信息字符串  
        jp4.add(progressBar);
          slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent c)  
    {  
        progressBar.setValue(slider.getValue());  
    }  
        });
        //addSlider(slider,"");
        /*
        textField=new JTextField(4);

        jp4.add(sliderPanel);

        
         slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField.setText("子女1: " + slider.getValue());
            }
        });
         jp4.add(textField);
         pack();
               */ 
            jp5.add(jb1);       //添加按钮
            jp5.add(jb2);
            jp5.add(jb3);  
         
        break;
        
            case "3":
            Inum=2;
            jp3.add(Lweight4);
            jp3.add(weight4);
            jp3.add(jlb4);      //年龄段
            jp3.add(jage1);
            jp3.add(jage2);
            jp3.add(jage3);
            jp3.add(jage4);

            
            jp4.add(Lweight5);
            jp4.add(weight5);
            jp4.add(jlb42);      //年龄段
            jp4.add(jage21);
            jp4.add(jage22);
            jp4.add(jage23);
            jp4.add(jage24);
 
            
            jp5.add(jlb6);      //亲疏4
            jp5.add(Lweight7);
            jp5.add(weight7);
        slider2.setMajorTickSpacing(20);

        slider2.setMinorTickSpacing(2);

        slider2.setPaintTicks(true);

        slider2.setPaintLabels(true);

        Dictionary labelTable2=new Hashtable<>();

        labelTable2.put(0, new JLabel("疏远"));


        labelTable2.put(100, new JLabel("亲密"));

        slider2.setLabelTable(labelTable2);

        jp5.add(slider2);
        
        progressBar2.setValue(0); 
        progressBar2.setStringPainted(true);//设置进度条将显示信息字符串  
        jp5.add(progressBar2);
          slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent c)  
    {  
        progressBar2.setValue(slider2.getValue());  
    }  
        });
        jp6.add(Lweight8);
            jp6.add(weight8);
               slider3.setMajorTickSpacing(20);

        slider3.setMinorTickSpacing(2);

        slider3.setPaintTicks(true);

        slider3.setPaintLabels(true);

        Dictionary labelTable3=new Hashtable<>();

        labelTable3.put(0, new JLabel("疏远"));


        labelTable3.put(100, new JLabel("亲密"));

        slider3.setLabelTable(labelTable3);

        jp6.add(slider3);
        
        progressBar3.setValue(0); 
        progressBar3.setStringPainted(true);//设置进度条将显示信息字符串  
        jp6.add(progressBar3);
          slider3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent c)  
    {  
        progressBar3.setValue(slider3.getValue());  
    }  
        });
            

            jp7.add(jb1);       //添加按钮
            jp7.add(jb2);
            jp7.add(jb3);  
         
        break;
        
        case"4":
            Inum=3;
            jp3.add(Lweight4);
            jp3.add(weight4);
            jp3.add(jlb4);      //年龄段
            jp3.add(jage1);
            jp3.add(jage2);
            jp3.add(jage3);
            jp3.add(jage4);

            
            jp4.add(Lweight5);
            jp4.add(weight5);
            jp4.add(jlb42);      //年龄段
            jp4.add(jage21);
            jp4.add(jage22);
            jp4.add(jage23);
            jp4.add(jage24);
  
           
            jp5.add(Lweight6);
            jp5.add(weight6);           
            jp5.add(jlb43);      //年龄段
            jp5.add(jage31);
            jp5.add(jage32);
            jp5.add(jage33);
            jp5.add(jage34);

            
            
            
            
            
            jp6.add(jlb6);      //亲疏4
            jp6.add(Lweight7);
            jp6.add(weight7);
            
        slider4.setMajorTickSpacing(20);

        slider4.setMinorTickSpacing(2);

        slider4.setPaintTicks(true);

        slider4.setPaintLabels(true);

        Dictionary labelTable4=new Hashtable<>();

        labelTable4.put(0, new JLabel("疏远"));


        labelTable4.put(100, new JLabel("亲密"));

        slider4.setLabelTable(labelTable4);

        jp6.add(slider4);
        
        progressBar4.setValue(0); 
        progressBar4.setStringPainted(true);//设置进度条将显示信息字符串  
        jp6.add(progressBar4);
          slider4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent c)  
    {  
        progressBar4.setValue(slider4.getValue());  
    }  
        });
            jp7.add(Lweight8);
            jp7.add(weight8);
            
        slider5.setMajorTickSpacing(20);

        slider5.setMinorTickSpacing(2);

        slider5.setPaintTicks(true);

        slider5.setPaintLabels(true);

        Dictionary labelTable5=new Hashtable<>();

        labelTable5.put(0, new JLabel("疏远"));


        labelTable5.put(100, new JLabel("亲密"));

        slider5.setLabelTable(labelTable5);

        jp7.add(slider5);
        
        progressBar5.setValue(0); 
        progressBar5.setStringPainted(true);//设置进度条将显示信息字符串  
        jp7.add(progressBar5);
          slider5.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent c)  
    {  
        progressBar5.setValue(slider5.getValue());  
    }  
        });
            jp8.add(Lweight9);
            jp8.add(weight9);
        
        slider6.setMajorTickSpacing(20);

        slider6.setMinorTickSpacing(2);

        slider6.setPaintTicks(true);

        slider6.setPaintLabels(true);

        Dictionary labelTable6=new Hashtable<>();

        labelTable6.put(0, new JLabel("疏远"));


        labelTable6.put(100, new JLabel("亲密"));

        slider6.setLabelTable(labelTable6);

        jp8.add(slider6);
        
        progressBar6.setValue(0); 
        progressBar6.setStringPainted(true);//设置进度条将显示信息字符串  
        jp8.add(progressBar6);
          slider6.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent c)  
    {  
        progressBar6.setValue(slider6.getValue());  
    }  
        });

            jp9.add(jb1);       //添加按钮
            jp9.add(jb2);
            jp9.add(jb3);  
            
            break;
        default:
            break;
        
        }
 
        //加入JFrame中  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
        this.add(jp4);
        this.add(jp5);  
        this.add(jp6);  
        this.add(jp7);  
        this.add(jp8);  
        this.add(jp9);  

        this.setLayout(new GridLayout(10,1));            //选择GridLayout布局管理器        
        this.setTitle("家族信托系统");          
        this.setSize(1000,750);         
        //this.setLocation(400, 000);           
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  

    }  

    public void actionPerformed(ActionEvent e) {            //事件判断

        if(e.getActionCommand()=="测试")  
        {  
             //JOptionPane.showMessageDialog(null,"测试成功！","提示消息",JOptionPane.WARNING_MESSAGE);  
              
           // clear();         
         //    dispose();             
         
             name=jtf.getText();
             System.out.println(name);
             if(jage1.isSelected())  
            {  age=1; }else if(jage2.isSelected())  
            {  age=2; }else if(jage3.isSelected())  
            {  age=3; }else if(jage4.isSelected())  
            {  age=4; }else{age=0;}
             if(jage21.isSelected())  
            {  age2=1; }else if(jage22.isSelected())  
            {  age2=2; }else if(jage23.isSelected())  
            {  age2=3; }else if(jage24.isSelected())  
            {  age2=4; }else{age2=0;}
             if(jage31.isSelected())  
            {  age3=1; }else if(jage32.isSelected())  
            {  age3=2; }else if(jage33.isSelected())  
            {  age3=3; }else if(jage34.isSelected())  
            {  age3=4; }else{age3=0;}
            
              switch(weight4.getSelectedIndex()){
                case 0:
                    Sweight4="0";
                    break;
                case 1:
                    Sweight4="1";
                    break;
                case 2:
                    Sweight4="2";
                    break;
                case 3:
                    Sweight4="3";
                    break;
                case 4:
                    Sweight4="4";
                    break;
                case 5:
                    Sweight4="5";
                    break;
                case 6:
                    Sweight4="6";
                    break;
                case 7:
                    Sweight4="7";
                    break;
                case 8:
                    Sweight4="8";
                    break;
                case 9:
                    Sweight4="9";
                    break;
                case 10:
                    Sweight4="10";
                    break;      
            }
            switch(weight5.getSelectedIndex()){
                case 0:
                    Sweight5="0";
                    break;
                case 1:
                    Sweight5="1";
                    break;
                case 2:
                    Sweight5="2";
                    break;
                case 3:
                    Sweight5="3";
                    break;
                case 4:
                    Sweight5="4";
                    break;
                case 5:
                    Sweight5="5";
                    break;
                case 6:
                    Sweight5="6";
                    break;
                case 7:
                    Sweight5="7";
                    break;
                case 8:
                    Sweight5="8";
                    break;
                case 9:
                    Sweight5="9";
                    break;
                case 10:
                    Sweight5="10";
                    break;      
            }
            switch(weight6.getSelectedIndex()){
                case 0:
                    Sweight6="0";
                    break;
                case 1:
                    Sweight6="1";
                    break;
                case 2:
                    Sweight6="2";
                    break;
                case 3:
                    Sweight6="3";
                    break;
                case 4:
                    Sweight6="4";
                    break;
                case 5:
                    Sweight6="5";
                    break;
                case 6:
                    Sweight6="6";
                    break;
                case 7:
                    Sweight6="7";
                    break;
                case 8:
                    Sweight6="8";
                    break;
                case 9:
                    Sweight6="9";
                    break;
                case 10:
                    Sweight6="10";
                    break;      
            }
            switch(weight7.getSelectedIndex()){
                case 0:
                    Sweight7="0";
                    break;
                case 1:
                    Sweight7="1";
                    break;
                case 2:
                    Sweight7="2";
                    break;
                case 3:
                    Sweight7="3";
                    break;
                case 4:
                    Sweight7="4";
                    break;
                case 5:
                    Sweight7="5";
                    break;
                case 6:
                    Sweight7="6";
                    break;
                case 7:
                    Sweight7="7";
                    break;
                case 8:
                    Sweight7="8";
                    break;
                case 9:
                    Sweight7="9";
                    break;
                case 10:
                    Sweight7="10";
                    break;      
            }
            switch(weight8.getSelectedIndex()){
                case 0:
                    Sweight8="0";
                    break;
                case 1:
                    Sweight8="1";
                    break;
                case 2:
                    Sweight8="2";
                    break;
                case 3:
                    Sweight8="3";
                    break;
                case 4:
                    Sweight8="4";
                    break;
                case 5:
                    Sweight8="5";
                    break;
                case 6:
                    Sweight8="6";
                    break;
                case 7:
                    Sweight8="7";
                    break;
                case 8:
                    Sweight8="8";
                    break;
                case 9:
                    Sweight8="9";
                    break;
                case 10:
                    Sweight8="10";
                    break;      
            }
            switch(weight9.getSelectedIndex()){
                case 0:
                    Sweight9="0";
                    break;
                case 1:
                    Sweight9="1";
                    break;
                case 2:
                    Sweight9="2";
                    break;
                case 3:
                    Sweight9="3";
                    break;
                case 4:
                    Sweight9="4";
                    break;
                case 5:
                    Sweight9="5";
                    break;
                case 6:
                    Sweight9="6";
                    break;
                case 7:
                    Sweight9="7";
                    break;
                case 8:
                    Sweight9="8";
                    break;
                case 9:
                    Sweight9="9";
                    break;
                case 10:
                    Sweight9="10";
                    break;      
            }
            
            
            fam = slider.getValue();
            if(fam==0){fam=0;}
            else if(fam<25){fam=1;}
            else if(fam<50){fam=2;}
            else if(fam<75){fam=3;}
            else{fam=4;}
            fam2 = slider2.getValue();    
            if(fam==0){fam=0;}
            else if(fam2<25){fam2=1;}
            else if(fam2<50){fam2=2;}
            else if(fam2<75){fam2=3;}
            else{fam2=4;}
            fam3 = slider3.getValue();
            if(fam==0){fam=0;}
            else if(fam3<25){fam3=1;}
            else if(fam3<50){fam3=2;}
            else if(fam3<75){fam3=3;}
            else{fam3=4;}
            fam4 = slider4.getValue();  
            if(fam==0){fam=0;}
            else if(fam4<25){fam4=1;}
            else if(fam4<50){fam4=2;}
            else if(fam4<75){fam4=3;}
            else{fam4=4;}
            fam5 = slider5.getValue();      
            if(fam==0){fam=0;}
            else if(fam5<25){fam5=1;}
            else if(fam5<50){fam5=2;}
            else if(fam5<75){fam5=3;}
            else{fam5=4;}
            fam6 = slider6.getValue();     
            if(fam==0){fam=0;}
            else if(fam6<25){fam6=1;}
            else if(fam6<50){fam6=2;}
            else if(fam6<75){fam6=3;}
            else{fam6=4;}
            
            System.out.println("fam： "+fam);
            System.out.println("fam2： "+fam2);
            System.out.println("fam3： "+fam3);
            System.out.println("fam4： "+fam4);
            System.out.println("fam5： "+fam5);
            System.out.println("fam6： "+fam6);
            Iweight=Integer.parseInt( Sweight ); 
            Iweight2=Integer.parseInt( Sweight2 ); 
            Iweight3=Integer.parseInt( Sweight3 ); 
            Iweight4=Integer.parseInt( Sweight4 ); 
            Iweight5=Integer.parseInt( Sweight5 ); 
            Iweight6=Integer.parseInt( Sweight6 ); 
            Iweight7=Integer.parseInt( Sweight7); 
            Iweight8=Integer.parseInt( Sweight8 ); 
            Iweight9=Integer.parseInt( Sweight9 ); 
            switch(Snum){
                case "1":
                    TestUI test=new TestUI(name,edu,0,0,0,marry,0,0,0,0,0,0,0,Iweight,Iweight2,Iweight3,0,0,0,0,0,0);//学历/婚姻/子女个数
                    break;
                case "2":                                       
                    TestUI test1=new TestUI(name,edu,age,0,0,marry,fam,0,0,0,0,0,1,Iweight,Iweight2,Iweight3,Iweight4,0,0,Iweight7,0,0);  //创建一个新界面  
                    break;
                case "3":
                    TestUI test2=new TestUI(name,edu,age,age2,0,marry,0,fam2,fam3,0,0,0,2,Iweight,Iweight2,Iweight3,Iweight4,Iweight5,0,Iweight7,Iweight8,0);  //创建一个新界面  
                    break;
                case "4":
                    TestUI test3=new TestUI(name,edu,age,age2,age3,marry,0,0,0,fam4,fam5,fam6,Inum,Iweight,Iweight2,Iweight3,Iweight4,Iweight5,Iweight6,Iweight7,Iweight8,Iweight9);  //创建一个新界面  
                    break;
            }       
                    
                    
           
            // test.a=aa;
             
            // test.repaint();
          //    aa=jtf.getText(); 
            //  testlogin(); 
              
              

        }else if(e.getActionCommand()=="上一页"){
            dispose();
            MainUI mUI=new MainUI();
                  
        }
        else if(e.getActionCommand()=="退出")  
        {  
                  System.exit(0);
        }         

      
    
    }

}

    