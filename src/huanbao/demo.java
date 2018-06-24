package huanbao;

import java.util.Date;

public class demo {
	double a;
	double dfjiage;
	double sfjiage;
	String[] name;//用户家庭
	int jifen=0;
	double dfpj;
	double sfpj;
	int[] shop;
	
	// 平均用水量
	public int geta(){
		int b=30;
		double a=b;//B= 每月用水量
		return (int) (b/a);
	}
	// 平均用电量
	public int getb(){
		int b=30;
		double a=b;//B= 每月用电量
		return (int) (b/a);
	}
	//总用电量
	public double getc(){
		int b=30;
		double a=getb();
		return  b*a;
	}
	//每总用水量
	public double getd(){
		int b=30;
		double a=geta();
		return  b*a;
	}
	//设置电费价格
	public void setdfjiage(double jiage) {
		this.dfjiage = jiage;
		
	}
	//设置水费价格
	public void setsfjiage(double jiage){
		this.sfjiage = jiage;
	}
	//设置四季不同价格计算
	public void siji(String siji){
		switch (siji) {
		case "春":
			setdfjiage(1);
			setsfjiage(1);
			break;
		case "夏":
			setdfjiage(2);
			setsfjiage(2);
			break;
		case "秋":
			setdfjiage(3);
			setsfjiage(3);
			break;
		case "冬":
			setdfjiage(4);
			setsfjiage(4);
			break;
		default:
			System.err.println("ERRO:未获取到季节");
			break;
		}
	}
	//计算每月电费综合
	public double gete() {
		double a =getc()*this.dfjiage;
		return a;
		
	}
	//计算每月水费综合
	public double getf() {
		double a =getc()*this.sfjiage;
		return a;
	}
	//判断用户家庭如果为真积分+1
	public void setjifen(String name){
		for (int i = 0; i < this.name.length; i++) {
			if(this.name[i].equals(name)){
				this.jifen=+1;
			}
		}
	}
	//计算增加积分公式
	public void jsjifen(String name){
		//电量计算小于dfpj这个值积分+1
		if (getc()<this.dfpj) {
			setjifen(name);
		}
		//水费计算小于sfpj这个值积分+1
		if (getd()<this.sfpj) {
			setjifen(name);
		}
	}
	//判断是否高于平均水量
	public boolean pjsf(){
		double a=0;//平均水量由于无法调用接口利用a代替
		if(a<geta()){
			System.out.println("低于平均水量");
			return true;
		}
		else {
			return false;
		}
	}
	//判断是否高于平均电总
	public boolean pjdf(){
		double a=0;//平均水量由于无法调用接口利用a代替
		if(a<getb()){
			System.out.println("低于平均电费");
			return true;
		}
		else {
			return false;
		}
	}
	//时间显示
	public String date(){
	       // 初始化 Date 对象
	       Date date = new Date();
	        
	       // 使用 toString() 函数显示日期时间
	       return "当前时间"+date.toString();
	}
	public double getqndf(){
		double a = 0;//去年电费平均值
		return a;
	}
	public double getqnsf(){
		double a = 0;//去年水费平均值
		return a;
	}
	public boolean getpjz(boolean a,int b){
		if (a&&b==1) {
			System.out.println("您已超过去年全国水费平均值");
			return true;
		}
		if (a&&b==0) {
			System.out.println("您已超过去年全国平均水费");
			return true;
		}
		return false;
	}
	//超出上月平均预警
	public void yujing(int b){
		double a = 0;//a由于调用不到接口使用a代替
		if(a>geta()&&b==0){
			System.out.println("本月使用水量已超过上月平均值");
		}
		if (a>getb()&&b==1) {
			System.out.println("本月使用电费已超过上月平均值");
		}
	}
	public double jidu(int b){
		double a = 0;//季度电费由于调用不到接口使用a代替
		double c = 0;
		if(b==0){
			c = a;
		}
		if (b==1) {
		 c = a;
		}
		return c;
	}
	//测试方法
    public static void main(String[] args) {
		demo a  = new demo();
		a.siji("春");
		a.date();
		System.out.println(a.sfjiage+"|"+a.gete()+"|"+a.getf()+"|"+a.pjsf()+"|"+a.pjdf());
	}
}

