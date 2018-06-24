package huanbao;

public class apijifen {
	int[][] a;
	//购买商品扣积分功能
	public void jifen(int a){
		demo b =new demo();
		b.jifen =-a;
	}
	//添加商品
	public void addshop(int id,int jf){
		demo b =new demo();
		for(int a = 0;b.shop.length<a;a++){
			if(b.shop[a]==0){
				b.shop[a]=id;
				this.a[a][1]=b.shop[a];
				this.a[a][jf]=jf;
			}
		}
	}
	//删除商品
	public void removeshop(int id){
		demo b =new demo();
		for(int a = 0;b.shop.length<a;a++){
			if(b.shop[a]==id){
				b.shop[a]=0;
			}
		}
	}
	//购买商品
	public void buyshop(int id,int jf){
		demo b =new demo();
		for(int a = 0;b.shop.length<a;a++){
			if(b.shop[a]==id){
				int jifen = b.jifen-this.a[a][jf];
				if(jifen<0){
					System.err.println("ERRO:你的积分不够");
				}
				else{
					shopno();
				}
			}
		}
	}
	public void api(){
		
	}
	//购买商品之后操作
	public void shopno(){
	}
	//可以重写这个方法
}