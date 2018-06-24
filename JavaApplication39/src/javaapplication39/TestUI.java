/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication39;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.JButton;
import javaapplication39.MainUI; 
/**
 *
 * @author 20308
 */
    public class TestUI extends JFrame implements ActionListener  
{   
         //定义组件  
        JButton jbt1,jbt2=null;  
        JPanel jpt1,jpt2,jpt3,jpt4,jpt5,jpt6,jpt7,jpt8,jpt9=null;  
        JLabel jlbt1,jlbt2,jlbt3,jlbt4,jlbt5,jlbt6,jlbt7,jlbt8,jlbt9,jlbt10,jlbt11,jlbt12,jlbt13,jlbt14,jlbt15,jlbt16,jlbt17,jlbt18,jlbt19,jlbt20,jlbt21,jlbt22,jlbt23,jlbt24,jlbt25,jlbt26,jlbt27,jlbt28,jlbt29,jlbt30=null;  
        JLabel jlbt31,jlbt32,jlbt33,jlbt34,jlbt35,jlbt36,jlbt37,jlbt38,jlbt39,jlbt40,jlbt41,jlbt42,jlbt43,jlbt44,jlbt45,jlbt46,jlbt47,jlbt48,jlbt49,jlbt50,jlbt51,jlbt52,jlbt53,jlbt54,jlbt55,jlbt56,jlbt57,jlbt58,jlbt59,jlbt60,jlbt61,jlbt62,jlbt63,jlbt64=null; 
       
        public static void main(String[] args) {            
          TestUI  test=new TestUI("",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);  
        
        }    
        public  TestUI(String name,int edu,int age,int age2, int age3,int marry,int fam,int fam2,int fam3,int fam4,int fam5,int fam6,int Inum,int Iweight,int Iweight2,int Iweight3,int Iweight4,int Iweight5,int Iweight6,int Iweight7,int Iweight8,int Iweight9)
        {  
            String Sedu = String.valueOf(edu);
            String Sage = String.valueOf(age);
            String Sage2 = String.valueOf(age2);
            String Sage3 = String.valueOf(age3);
            String Smarry = String.valueOf(marry);
            String Sfam = String.valueOf(fam);
            String Sfam2 = String.valueOf(fam2);
            String Sfam3 = String.valueOf(fam3);
            String Sfam4 = String.valueOf(fam4);
            String Sfam5 = String.valueOf(fam5);
            String Sfam6 = String.valueOf(fam6);
            String Snum = String.valueOf(Inum);
            String Sweight = String.valueOf(Iweight);
             String Sweight2 = String.valueOf(Iweight2);
             String Sweight3 = String.valueOf(Iweight3);
             String Sweight4 = String.valueOf(Iweight4);
             String Sweight5 = String.valueOf(Iweight5);
             String Sweight6 = String.valueOf(Iweight6);
             String Sweight7 = String.valueOf(Iweight7);
             String Sweight8 = String.valueOf(Iweight8);
             String Sweight9 = String.valueOf(Iweight9);
              
            
          //  a=MainUI.aa;//创建组件  
            jbt1=new JButton("返回");
            jbt1.setForeground(Color.BLUE);
            jbt2=new JButton("退出程序");  
            jbt2.setForeground(Color.BLUE);

            jpt1=new JPanel();  
            jpt2=new JPanel();  
            jpt3=new JPanel();
            jpt4=new JPanel();  
            jpt5=new JPanel();  
            jpt6=new JPanel(); 
            jpt7=new JPanel();  
            jpt8=new JPanel(); 
            jpt9=new JPanel();

           jlbt1=new JLabel("姓名：");  
          // jlb11=new JLabel(new MainUI().aa);
           jlbt31=new JLabel(name);
            jlbt2=new JLabel("受教育情况：");  
            jlbt32=new JLabel(Sedu);
            jlbt3=new JLabel("权重");
            jlbt33=new JLabel(Sweight);
            jlbt4=new JLabel("婚姻情况："); 
            jlbt34=new JLabel(Smarry);
            jlbt5=new JLabel("权重");
            jlbt35=new JLabel(Sweight2);
          //  jlb3.setForeground(Color.red);
            jlbt6=new JLabel("子女人数：");  
            jlbt36=new JLabel(Snum);
           jlbt7=new JLabel("权重");
           jlbt37=new JLabel(Sweight3);
            jlbt8=new JLabel("子女1年龄：");
           jlbt38=new JLabel(Sage);
           jlbt9=new JLabel("权重");
           jlbt39=new JLabel(Sweight4);
           jlbt10=new JLabel("子女2年龄：");
           jlbt40=new JLabel(Sage2);
           jlbt11=new JLabel("权重");
           jlbt41=new JLabel(Sweight5);
           jlbt12=new JLabel("子女3年龄：");
           jlbt42=new JLabel(Sage3);
           jlbt13=new JLabel("权重");
           jlbt43=new JLabel(Sweight6);
           jlbt14=new JLabel("子女1亲疏");
           jlbt44=new JLabel(Sfam);
           jlbt15=new JLabel("权重");
           jlbt45=new JLabel(Sweight7);
           jlbt16=new JLabel("子女1亲疏");
           jlbt46=new JLabel(Sfam2);
           jlbt17=new JLabel("权重");
           jlbt47=new JLabel(Sweight7);
           jlbt18=new JLabel("子女2亲疏");
           jlbt48=new JLabel(Sfam3);
           jlbt19=new JLabel("权重");
           jlbt49=new JLabel(Sweight8);
           jlbt20=new JLabel("子女1亲疏");
           jlbt50=new JLabel(Sfam4);
           jlbt21=new JLabel("权重");
           jlbt51=new JLabel(Sweight7);
           jlbt22=new JLabel("子女2亲疏");
           jlbt52=new JLabel(Sfam5);
           jlbt23=new JLabel("权重");
           jlbt53=new JLabel(Sweight8);
           jlbt24=new JLabel("子女3亲疏");
           jlbt54=new JLabel(Sfam6);
           jlbt25=new JLabel("权重");
           jlbt55=new JLabel(Sweight9);
           
           
           switch(Inum){
               case 0:
                        jlbt26=new JLabel("您没有子女，无法进行分配");
                        jpt1.add(jlbt1);
                        jpt1.add(jlbt31);
                        jpt1.add(jlbt2);
                        jpt1.add(jlbt32);
                        jpt1.add(jlbt3);
                        jpt1.add(jlbt33);
                        jpt2.add(jlbt4);
                        jpt2.add(jlbt34);
                        jpt2.add(jlbt5);
                        jpt2.add(jlbt35);
                        jpt2.add(jlbt6);
                        jpt2.add(jlbt36);
                        jpt2.add(jlbt7);
                        jpt2.add(jlbt37);
                       
                        jpt3.add(jlbt26);
                       
                        jpt5.add(jbt1);
                        jpt5.add(jbt2);
                        break;
               case 1:
                        int resultM1=Iweight*edu+Iweight2*marry+Iweight3*Inum+Iweight4*age+Iweight7*fam;
                        String SresultM1 = String.valueOf(resultM1);
                        jlbt26=new JLabel("测试结果: 子女1： ");
                        jlbt56=new JLabel(SresultM1);
                        double resultSum=Iweight*edu+Iweight2*marry+Iweight3*Inum+Iweight4*age+Iweight7*fam;
                        String SresultSum = String.valueOf(resultSum);
                        jlbt27=new JLabel("求和： ");
                        jlbt57=new JLabel(SresultSum);
                        
                        double resultF1temp,resultF1temp2,resultF1temp3,resultF1temp4,resultF1temp5,resultF1temp6,resultF1,resultF2,resultF3;
                        resultF1temp=100/(double)resultSum;
                        resultF1temp2=(double)(Math.round(resultF1temp*100))/100;
                        resultF1=resultF1temp2*resultM1;
                        String SresultF1 = String.valueOf(resultF1);
                        jlbt28=new JLabel("总分：子女1： ");
                        jlbt58=new JLabel(SresultF1);
                        
                        jpt1.add(jlbt1);
                        jpt1.add(jlbt31);
                        jpt1.add(jlbt2);
                        jpt1.add(jlbt32);
                        jpt1.add(jlbt3);
                        jpt1.add(jlbt33);
                        jpt2.add(jlbt4);
                        jpt2.add(jlbt34);
                        jpt2.add(jlbt5);
                        jpt2.add(jlbt35);
                        jpt2.add(jlbt6);
                        jpt2.add(jlbt36);
                        jpt2.add(jlbt7);
                        jpt2.add(jlbt37);
                        jpt3.add(jlbt8);
                        jpt3.add(jlbt38);
                        jpt3.add(jlbt9);
                        jpt3.add(jlbt39);                   
                        jpt3.add(jlbt14);
                        jpt3.add(jlbt44);
                        jpt3.add(jlbt15);
                        jpt3.add(jlbt45);
                       
                        jpt4.add(jlbt26);
                        jpt4.add(jlbt56);
                       
                        jpt5.add(jlbt27);
                        jpt5.add(jlbt57);
                        
                        jpt6.add(jlbt28);
                        jpt6.add(jlbt58);
                        
                        
                        jpt7.add(jbt1);
                        jpt7.add(jbt2);
                   break;
               case 2:
                        resultM1=Iweight*edu+Iweight2*marry+Iweight3*Inum+Iweight4*age+Iweight7*fam2;
                        SresultM1 = String.valueOf(resultM1);
                        jlbt26=new JLabel("测试结果: 子女1： ");
                        jlbt56=new JLabel(SresultM1);
                
                        int resultM2=Iweight*edu+Iweight2*marry+Iweight3*Inum+Iweight5*age2+Iweight8*fam3;
                        String SresultM2 = String.valueOf(resultM2);
                        jlbt27=new JLabel("子女2： ");
                        jlbt57=new JLabel(SresultM2);
                        resultSum=resultM1+resultM2;
                        SresultSum = String.valueOf(resultSum);
                        jlbt28=new JLabel("求和： ");
                        jlbt58=new JLabel(SresultSum);
                        
                        resultF1temp2=100/resultSum;
                        resultF1temp3=(double)(Math.round(resultF1temp2*100))/100;
                        resultF1=resultF1temp3*resultM1;
                        SresultF1 = String.valueOf(resultF1);
                        jlbt29=new JLabel("总分：子女1： ");
                        jlbt59=new JLabel(SresultF1);
                         resultF2=resultF1temp3*resultM2;
                        String SresultF2 = String.valueOf(resultF2);
                        jlbt30=new JLabel("总分：子女2： ");
                        jlbt60=new JLabel(SresultF2);
                        jpt1.add(jlbt1);
                        jpt1.add(jlbt31);
                        jpt1.add(jlbt2);
                        jpt1.add(jlbt32);
                        jpt1.add(jlbt3);
                        jpt1.add(jlbt33);
                        jpt2.add(jlbt4);
                        jpt2.add(jlbt34);
                        jpt2.add(jlbt5);
                        jpt2.add(jlbt35);
                        jpt2.add(jlbt6);
                        jpt2.add(jlbt36);
                        jpt2.add(jlbt7);
                        jpt2.add(jlbt37);
                        jpt3.add(jlbt8);
                        jpt3.add(jlbt38);
                        jpt3.add(jlbt9);
                        jpt3.add(jlbt39);
                        jpt3.add(jlbt10);
                        jpt3.add(jlbt40);
                        jpt3.add(jlbt11);
                        jpt3.add(jlbt41);                      
                        jpt4.add(jlbt16);
                        jpt4.add(jlbt46);
                        jpt4.add(jlbt17);
                        jpt4.add(jlbt47);
                        jpt4.add(jlbt18);
                        jpt4.add(jlbt48);
                        jpt4.add(jlbt19);
                        jpt4.add(jlbt49);
                       
                        jpt5.add(jlbt26);
                        jpt5.add(jlbt56);
                        
                        jpt5.add(jlbt27);
                        jpt5.add(jlbt57);
                        
                        jpt6.add(jlbt28);
                        jpt6.add(jlbt58);
                        
                        jpt7.add(jlbt29);
                        jpt7.add(jlbt59);
                        
                        jpt7.add(jlbt30);
                        jpt7.add(jlbt60);
                        
                        
                       
                        jpt8.add(jbt1);
                        jpt8.add(jbt2);
                        
                        
                        
                   break;
               case 3:
                        resultM1=Iweight*edu+Iweight2*marry+Iweight3*Inum+Iweight4*age+Iweight7*fam4;
                        SresultM1 = String.valueOf(resultM1);
                        jlbt26=new JLabel("测试结果: 子女1： ");
                        jlbt56=new JLabel(SresultM1);
                
                        resultM2=Iweight*edu+Iweight2*marry+Iweight3*Inum+Iweight5*age2+Iweight8*fam5;
                        SresultM2 = String.valueOf(resultM2);
                        jlbt27=new JLabel("子女2： ");
                        jlbt57=new JLabel(SresultM2);
                        
                        int resultM3=Iweight*edu+Iweight2*marry+Iweight3*Inum+Iweight6*age3+Iweight9*fam6;
                        String SresultM3 = String.valueOf(resultM3);
                        jlbt28=new JLabel("子女3： ");
                        jlbt58=new JLabel(SresultM3);
                        
                        
                        resultSum=resultM1+resultM2+resultM3;
                        SresultSum = String.valueOf(resultSum);
                        jlbt29=new JLabel("求和： ");
                        jlbt59=new JLabel(SresultSum);
                        
                        resultF1temp4=100/resultSum;
                        resultF1temp5=(double)(Math.round(resultF1temp4*100))/100;
                        resultF1temp6=(double)(Math.round(resultF1temp5*100))/100;
                        resultF1=resultF1temp6*resultM1;
                        SresultF1 = String.valueOf(resultF1);
                        jlbt30=new JLabel("总分：子女1： ");
                        jlbt60=new JLabel(SresultF1);
                         resultF2=resultF1temp6*resultM2;
                        SresultF2 = String.valueOf(resultF2);
                        jlbt61=new JLabel("总分：子女2： ");
                        jlbt62=new JLabel(SresultF2);
                         resultF3=resultF1temp6*resultM3;
                        String SresultF3 = String.valueOf(resultF3);
                        jlbt63=new JLabel("总分：子女3： ");
                        jlbt64=new JLabel(SresultF3);
                        
                        jpt1.add(jlbt1);
                        jpt1.add(jlbt31);
                        jpt1.add(jlbt2);
                        jpt1.add(jlbt32);
                        jpt1.add(jlbt3);
                        jpt1.add(jlbt33);
                        jpt2.add(jlbt4);
                        jpt2.add(jlbt34);
                        jpt2.add(jlbt5);
                        jpt2.add(jlbt35);
                        jpt2.add(jlbt6);
                        jpt2.add(jlbt36);
                        jpt2.add(jlbt7);
                        jpt2.add(jlbt37);
                        jpt3.add(jlbt8);
                        jpt3.add(jlbt38);
                        jpt3.add(jlbt9);
                        jpt3.add(jlbt39);
                        jpt3.add(jlbt10);
                        jpt3.add(jlbt40);
                        jpt3.add(jlbt11);
                        jpt3.add(jlbt41);
                        jpt3.add(jlbt12);
                        jpt3.add(jlbt42);
                        jpt3.add(jlbt13);
                        jpt3.add(jlbt43);
                        jpt4.add(jlbt20);
                        jpt4.add(jlbt50);
                        jpt4.add(jlbt21);
                        jpt4.add(jlbt51);
                        jpt4.add(jlbt22);
                        jpt4.add(jlbt52);
                        jpt4.add(jlbt23);
                        jpt4.add(jlbt53);
                        jpt4.add(jlbt24);
                        jpt4.add(jlbt54);
                        jpt4.add(jlbt25);
                        jpt4.add(jlbt55);
                        
                        jpt5.add(jlbt26);
                        jpt5.add(jlbt56);
                        
                        jpt5.add(jlbt27);
                        jpt5.add(jlbt57);
                        
                        jpt5.add(jlbt28);
                        jpt5.add(jlbt58);
                        
                        jpt6.add(jlbt29);
                        jpt6.add(jlbt59);
                        
                        jpt7.add(jlbt30);
                        jpt7.add(jlbt60);
                        
                        jpt7.add(jlbt61);
                        jpt7.add(jlbt62);
                        
                        jpt7.add(jlbt63);
                        jpt7.add(jlbt64);
                       
                        jpt8.add(jbt1);
                        jpt8.add(jbt2);
                        
                   break;
             
           
           
           
           }
           
            this.add(jpt1);  
            this.add(jpt2);  
            this.add(jpt3);   
            this.add(jpt4);  
            this.add(jpt5);  
            this.add(jpt6); 
            this.add(jpt7); 
            this.add(jpt8); 
            this.add(jpt9); 
            
                   
            this.setLayout(new GridLayout(9,3)); 
            this.setTitle("测试结果（各项属性打分）");  
            this.setSize(500,400);  
            this.setLocation(200, 200);       
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            this.setVisible(true);            
            jbt1.addActionListener(this);
            jbt2.addActionListener(this);
}  
        public void actionPerformed(ActionEvent e) {  
             if(e.getSource() == jbt1){              
                  dispose();  
                  MainUI ui=new MainUI();
                }else if(e.getSource() == jbt2){
                  dispose();   
                }

        }  
} 
    
    

