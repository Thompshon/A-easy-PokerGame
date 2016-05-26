package com.yuxuan.Poker;

public class Poker{

	private String num;
	private String suit;
	final public int numFlag;//点数标志位
	final public int suitFlag;//花色标志位

	
	Poker(String num,String suit,int numflag,int suitflag){
		this.num = num;
		this.suit = suit;
		this.numFlag = numflag;
		this.suitFlag =suitflag;
	}
	
//	Poker(){
//	
//	}
	public String getNum() {
		return num;
	}
	
	public String getSuit() {
		return suit;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}	
	
}
