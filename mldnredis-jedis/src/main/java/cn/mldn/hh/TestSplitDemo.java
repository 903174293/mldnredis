package cn.mldn.hh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/** 
* @author  作者 HH: 
* @date 创建时间：2017年10月31日 下午11:19:59 
*/
class MyMath {
	public static double round(double num,int scale) {
		return Math.round(num * Math.pow(10.0, scale)) / Math.pow(10.0, scale) ;
	} 
}

class SplitMoneyUtil {
	private Double money ;	// 保存属性的目的是为了进行验证
	private int amount ; // 存放数量，数量控制循环的次数
	private int currentAmount ; // 当前次数
	private double surplusMoney ; // 剩余资金
	private double currentMoney ;
	// 随机数字里面肯定使用nextInt()方法进行拆分，而这个方法支持的是int数据
	private Random rand = new Random() ;	// 准备随机数拆分
	private List<Double> allPackages = new ArrayList<Double>() ;	// 保存红包信息
	public SplitMoneyUtil(int amount,double money) {
		this.amount = amount ;
		this.money = money - (amount / 100.00) ;
		this.currentAmount = amount ;
		this.surplusMoney = money * 100 ;	// 剩余资金等于总资金
		if (this.currentAmount == 1) {	// 不要拆分了，做整体包
			this.allPackages.add(money) ;	// 一个包
		} else {
			this.handle();
		}
	}
	private void handle() {	// 处理红包拆分
		int count = (int)this.surplusMoney / this.amount;
		int key = count * 2;
		int rand = this.rand.nextInt(key) ;	// 通过已有的数据取出一个内容
		this.surplusMoney -= rand ;	// 从原的资金之中减少掉部分数据
		this.allPackages.add(rand/100.00) ;	// 保存到最终红包数据
		this.currentMoney += rand ;
		if (-- this.currentAmount > 1) {	// 还没有拆分到指定的个数
			this.handle(); // 继续拆分 
		} else {
			if (this.currentAmount == 1) {	// 余额都给了
				this.allPackages.add(((this.money * 100) - this.currentMoney)/100.00) ;
				return ;
			}
		}
	}
	public List<Double> getAllPackages() {
		List<Double> all = new ArrayList<>();
		Iterator<Double> it = this.allPackages.iterator();
		while(it.hasNext()){
			double s = it.next();
			all.add(MyMath.round(s + 0.01, 2));
		}
		return all ;
	}
}

public class TestSplitDemo {
	public static void main(String[] args) {
		for (int x = 0 ; x < 20 ; x ++) {
			SplitMoneyUtil smu = new SplitMoneyUtil(5,200.01) ;
			List<Double> result = smu.getAllPackages() ;
			System.out.println("【第"+x+"次检测】结果：" + sum(result) + (200.01 == sum(result)) + "，红包：" + result); // 获得拆包后的数据，这个数据存放到redis
			//拆分结果正确。计算精度不准确小概率丢失0.01
		}
	} 
	public static double sum(List<Double> all) {
		int s =  0;
		Iterator<Double> it = all.iterator();
		while(it.hasNext()){
			double ss = it.next();
			s += (int)MyMath.round(ss * 100, 2);
		}
		return s / 100.00 ;
	}
}

