/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication39;
  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javaapplication39.ContinueUI; 
public class MainUI extends JFrame implements ActionListener {  

    //定义组件   
    JButton jb1,jb2,jb3=null;  
    JRadioButton jrb1,jrb2,jrb3,jrb4,jrb5,jrb6,jrb7,jrb8=null;  //年龄段
    JRadioButton jrb21,jrb22,jrb23,jrb24=null; //学历
    JRadioButton jrb31,jrb32,jrb33,jrb34=null; //个数
    JRadioButton jrb41,jrb42,jrb43=null; //结婚
  
    JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8=null;  
    JTextField jtf=null;  
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5,jlb6=null;  
    JPasswordField jpf=null;  
    ButtonGroup bg,bg2,bg3,bg4,bg5,bg6,bg7,bg8,bg9=null;  
    private ChangeListener listener;
    private JPanel sliderPanel,sliderPane2;
    private JTextField textField;
    JLabel Lweight;
    JLabel Lweight2;
    JLabel Lweight3;
    JComboBox weight,weight2,weight3;
    String name,Sedu,Smarry,Snum,Sweight,Sweight2,Sweight3;
    int edu,age,marry,fam,num;

        
    public static void main(String[] args) {  
          MainUI mUI=new MainUI();  
          
    }  
    
    public MainUI()  
    {  
        sliderPanel=new JPanel();

        sliderPanel.setLayout(new GridBagLayout());
        sliderPane2=new JPanel();
        sliderPane2.setLayout(new GridBagLayout());

        Lweight = new JLabel("权重：");
        Lweight.setBounds(135, 120, 40, 20);
        String[] Sweight = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight = new JComboBox(Sweight);
        weight.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight.setSelectedIndex(0);
        
        Lweight2 = new JLabel("权重：");
        Lweight2.setBounds(135, 120, 40, 20);
        String[] Sweight2 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight2 = new JComboBox(Sweight2);
        weight2.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight2.setSelectedIndex(0);
        
        Lweight3 = new JLabel("权重：");
        Lweight3.setBounds(135, 120, 40, 20);
        String[] Sweight3 = { "0","1", "2", "3","4","5","6", "7", "8","9","10"  };
        weight3 = new JComboBox(Sweight3);
        weight3.setBounds(185, 120, 80, 20);
        //weight.addItem("其他");
        //weight.insertItemAt("请选择", 0);
        weight3.setSelectedIndex(0);
        


        listener=new ChangeListener(){
        
        public void stateChanged(ChangeEvent event){

        JSlider source=(JSlider)event.getSource();
        
        textField.setText(""+source.getValue());}
        /* public void itemStateChanged(ItemEvent e){  
        if(e.getSource()==jb31){  
            
        }else if(e.getSource()==jb32){  
            
        }
        else if(e.getSource()==jb33){  
     
            
        }else if(e.getSource()==jb34){  
            
        }
         
        } */ 
    
        
        
        };
//创建组件  
        jb1=new JButton("下一页"); 
        jb1.setForeground(Color.BLUE);
        jb2=new JButton("重置"); 
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
              
        jrb31=new JRadioButton("0");
        jrb32=new JRadioButton("1");
        jrb33=new JRadioButton("2");
        jrb34=new JRadioButton("3");
        //jrb35=new JRadioButton("4");
       // jrb36=new JRadioButton("5");
        bg3=new ButtonGroup();  
        bg3.add(jrb31);  
        bg3.add(jrb32); 
        bg3.add(jrb33);  
        bg3.add(jrb34);
        jrb31.setSelected(true);  //初始页面默认选择权限为 学生
    //    bg3.add(jrb35);  
     //   bg3.add(jrb36); 
        
        jrb1=new JRadioButton("0-10");  
        jrb2=new JRadioButton("10-20");
        jrb3=new JRadioButton("20-30");
        jrb4=new JRadioButton("30-40");
        jrb5=new JRadioButton("40-50");
        jrb6=new JRadioButton("50-60");
        jrb7=new JRadioButton("60-70");
        jrb8=new JRadioButton("70-80");
        bg=new ButtonGroup();  
        bg.add(jrb1);  
        bg.add(jrb2); 
        bg.add(jrb3);  
        bg.add(jrb4);
        bg.add(jrb5);  
        bg.add(jrb6); 
        bg.add(jrb7);  
        bg.add(jrb8);
        jrb1.setSelected(true); 
        jrb21=new JRadioButton("高中及以下");  
        jrb22=new JRadioButton("大学及以下");
        jrb23=new JRadioButton("硕士");
        jrb24=new JRadioButton("博士");


        bg2=new ButtonGroup();  
        bg2.add(jrb21);  
        bg2.add(jrb22); 
        bg2.add(jrb23);  
        bg2.add(jrb24);

        jrb21.setSelected(true);  
        
        jrb41=new JRadioButton("未婚");
        jrb42=new JRadioButton("已婚");
        jrb43=new JRadioButton("离异");
        bg4=new ButtonGroup();  
        bg4.add(jrb41);  
        bg4.add(jrb42); 
        bg4.add(jrb43);  
        jrb41.setSelected(true);  
    
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();
        jp5=new JPanel();  
        jp6=new JPanel();  
        jp7=new JPanel();  
        jp8=new JPanel();

        jlb1=new JLabel("姓名：");  
        jlb2=new JLabel("教育情况：");  
        jlb3=new JLabel("子女个数："); 
        jlb4=new JLabel("子女年龄："); 
        jlb5=new JLabel("婚姻情况：");
        jlb6=new JLabel("亲疏程度：");
        jtf=new JTextField(10);  
        jpf=new JPasswordField(10);  
        //加入到JPanel中  
        jp1.add(jlb1);  
        jp1.add(jtf);  
        
        
        jp2.add(Lweight);
        jp2.add(weight);
        jp2.add(jlb2);      //学历
        jp2.add(jrb21);  
        jp2.add(jrb22);
        jp2.add(jrb23);  
        jp2.add(jrb24); 

     
        
        jp3.add(Lweight2);
        jp3.add(weight2);
        jp3.add(jlb5);      //婚姻
        jp3.add(jrb41);
        jp3.add(jrb42);
        jp3.add(jrb43);

        
        jp4.add(Lweight3);
        jp4.add(weight3);
        jp4.add(jlb3);      //子女个数
        jp4.add(jrb31);  
        jp4.add(jrb32);
        jp4.add(jrb33);  
        jp4.add(jrb34); 
      //  jp3.add(jrb35);  
      //  jp3.add(jrb36); 
        

        jp5.add(jb1);       //添加按钮
        jp5.add(jb2);  
        jp5.add(jb3);

        //加入JFrame中  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
        this.add(jp4);
        this.add(jp5);  
        this.add(jp6);  
        this.add(jp7);  
        this.add(jp8);  

        this.setLayout(new GridLayout(9,3));            //选择GridLayout布局管理器        
        this.setTitle("家族信托系统");          
        this.setSize(500,320);         
        this.setLocation(400, 200);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  

    }  
public void addSlider(JSlider s,String description){

s.addChangeListener(listener);

JPanel panel=new JPanel();

panel.add(s);

panel.add(new Label(description));

//设置组件垂直对齐

panel.setAlignmentX(Component.LEFT_ALIGNMENT);

GridBagConstraints gbc=new GridBagConstraints();

//GridBagConstraints 类指定使用 GridBagLayout 类布置的组件的约束。 

gbc.gridy=sliderPanel.getComponentCount();

gbc.anchor=GridBagConstraints.WEST;

sliderPanel.add(panel,gbc);

}

    public void actionPerformed(ActionEvent e) {            //事件判断

        if(e.getActionCommand()=="下一页")  
        {  
             //JOptionPane.showMessageDialog(null,"测试成功！","提示消息",JOptionPane.WARNING_MESSAGE);  
              
           // clear();         
         //    dispose();             
         
             name=jtf.getText();
             System.out.println(name);
            if(jrb21.isSelected())  
            {  edu=1;Sedu="高中及以下"; }else if(jrb22.isSelected())  
            {  edu=2; Sedu="大学及以下"; }else if(jrb23.isSelected())  
            {  edu=3; Sedu="硕士"; }else if(jrb24.isSelected())  
            {  edu=4;Sedu="博士";  }
            if(jrb41.isSelected())  
            {  marry=2; Smarry="未婚";}else if(jrb42.isSelected())  
            {  marry=4; Smarry="已婚"; }else if(jrb43.isSelected())  
            {  marry=2; Smarry="离异";}        
            if(jrb31.isSelected())  
            {  num=1; Snum="1";}else if(jrb32.isSelected())  
            {   num=2; Snum="2"; }else if(jrb33.isSelected())  
            {   num=3; Snum="3";}else if(jrb34.isSelected())  
            {   num=4; Snum="4";}  
            switch(weight.getSelectedIndex()){
                case 0:
                    Sweight="0";
                    break;
                case 1:
                    Sweight="1";
                    break;
                case 2:
                    Sweight="2";
                    break;
                case 3:
                    Sweight="3";
                    break;
                case 4:
                    Sweight="4";
                    break;
                case 5:
                    Sweight="5";
                    break;
                case 6:
                    Sweight="6";
                    break;
                case 7:
                    Sweight="7";
                    break;
                case 8:
                    Sweight="8";
                    break;
                case 9:
                    Sweight="9";
                    break;
                case 10:
                    Sweight="10";
                    break;      
            }
            switch(weight2.getSelectedIndex()){
                case 0:
                    Sweight2="0";
                    break;
                case 1:
                    Sweight2="1";
                    break;
                case 2:
                    Sweight2="2";
                    break;
                case 3:
                    Sweight2="3";
                    break;
                case 4:
                    Sweight2="4";
                    break;
                case 5:
                    Sweight2="5";
                    break;
                case 6:
                    Sweight2="6";
                    break;
                case 7:
                    Sweight2="7";
                    break;
                case 8:
                    Sweight2="8";
                    break;
                case 9:
                    Sweight2="9";
                    break;
                case 10:
                    Sweight2="10";
                    break;      
            }
            switch(weight3.getSelectedIndex()){
                case 0:
                    Sweight3="0";
                    break;
                case 1:
                    Sweight3="1";
                    break;
                case 2:
                    Sweight3="2";
                    break;
                case 3:
                    Sweight3="3";
                    break;
                case 4:
                    Sweight3="4";
                    break;
                case 5:
                    Sweight3="5";
                    break;
                case 6:
                    Sweight3="6";
                    break;
                case 7:
                    Sweight3="7";
                    break;
                case 8:
                    Sweight3="8";
                    break;
                case 9:
                    Sweight3="9";
                    break;
                case 10:
                    Sweight3="10";
                    break;      
            }
           
         
         //   dispose();
            ContinueUI CUI=new ContinueUI(name,Sedu,Smarry,Snum,Sweight,Sweight2,Sweight3,edu,marry);
              

        }else if(e.getActionCommand()=="重置")  
        {  
                  clear();  
        }else if(e.getActionCommand()=="退出")  
        {  
                  System.exit(0);
        }         

      
    
    }
    public  void clear()  
    {  
        jtf.setText("");  
        jpf.setText("");  
    }  
}

    


