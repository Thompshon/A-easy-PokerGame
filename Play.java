package com.yuxuan.Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class Play {

	public List<Poker> pokers;
	private Scanner console;
	public List<Player> players;
	Play(){
		pokers= new ArrayList<Poker>();
		//copyPokers = new ArrayList<Poker>();
		console = new Scanner(System.in);
		players = new ArrayList<Player>();
	}
	/**
	 * 创建一副扑克牌
	 */
	public void creatPokers(String suit,int suitflag){
		
		for(int j=2;j<11;j++){
			pokers.add(new Poker(j+"",suit,j,suitflag));
		}
		pokers.add(new Poker("J",suit,11,suitflag));
		pokers.add(new Poker("Q",suit,12,suitflag));
		pokers.add(new Poker("K",suit,13,suitflag));
		pokers.add(new Poker("A",suit,14,suitflag));
	}
	
	/**
	 * foreach 显示创建好的扑克牌
	 */
	public void pokersForEach(){
		
		System.out.print("扑克牌为：[");
		for (Poker pk:pokers){
			System.out.print(pk.getSuit()+pk.getNum()+",");
		}
		System.out.println("]");
	}
	
	/**
	 * 打乱扑克牌的顺序
	 */
	public void pokersShuffle(){
		//是扑克牌乱序
		Collections.shuffle(pokers);
		System.out.println("------------洗牌结束！-------------");
	}
	
	/**
	 * 创建2个玩家
	 */
	public void createPlayers(){

		int playersNum=0;//玩家个数标志位
		while(true){
			//提醒输入玩家名称
			System.out.println("请输入第"+(playersNum+1)+"位玩家的ID和姓名：");
			System.out.println("输入ID：");
			String id = console.next();
			boolean flag=true;//数字标志位，0为数字，非0为非数字
			for(int j=0;j<id.length();j++){
				if(!Character.isDigit(id.charAt(j))){	
					System.out.println("请输入整数型ID：");
					flag=false;
					break;					
				}
			}
			if(flag){
				playersNum++;
				System.out.println("输入姓名：");
				String name = console.next();
				players.add(new Player(Integer.valueOf(id),name));
				//创建成功玩家个数标志退出
				if(playersNum==2)
					break;
			}
		}
	}
	/**
	 * Iterator 显示创建好的玩家
	 */
	public void playerIterator(){
		//创建一个迭代器用迭代器方法访问玩家
		Iterator<Player> it = players.iterator();	
		while (it.hasNext()){
			Player pr= it.next();
			System.out.println("----欢迎玩家："+pr.getName());
		}
	}
	
	/**
	 * 从第一张开始按每人一张的顺序开始发牌,每人发两张
	 */
	public void dealPokerToPlayers(){
		for (int i=0;i<players.size()*2;i++){
			//j为该为第几位玩家发牌
			int j=i%players.size();
			Player player = players.get(j);
			System.out.println("----玩家："+player.getName()+"拿牌");
			player.pokers.add(pokers.get(i));
		}
		System.out.println("------------发牌结束-------------");
	}
	
	/**
	 * 开始按规则游戏,先比较大小点，如果点数相同，则比较花色，按方片>梅花>红桃>黑桃的顺序比较
	 */
	public void startPlayGame(){
		//保存所有玩家手牌中的最大牌
		List<Poker> maxPoker = new ArrayList<Poker>();
		//比较玩家手中的手牌，获得每个玩家手中的最大牌
		for (int i=0;i<players.size();i++) {
			Player player = players.get(i);
			int tempflag=0;
			for (int j=0;j<player.pokers.size()-1;j++) {
				
				if(player.pokers.get(j+1).numFlag>player.pokers.get(j).numFlag){
					tempflag=j+1;
				}else if(player.pokers.get(j+1).numFlag==player.pokers.get(j).numFlag){
					if(player.pokers.get(j+1).suitFlag>player.pokers.get(j).suitFlag){
						tempflag=j+1;
					}
				}
			}
			maxPoker.add(player.pokers.get(tempflag));
			System.out.println(player.getName()+"最大手牌为："+player.pokers.get(tempflag).getSuit()
					+player.pokers.get(tempflag).getNum());
		}
		
		
		Poker temp = maxPoker.get(0);
		int flag=0;
		for (int i=0;i<maxPoker.size();i++) {
			if(maxPoker.get(i).numFlag>temp.numFlag){
				flag=i;
			}else if(maxPoker.get(i).numFlag==temp.numFlag){
				if(maxPoker.get(i).suitFlag>temp.suitFlag){
					flag=i;
				}
			}
		}
		System.out.println("------玩家："+players.get(flag).getName()+"获胜！-------");
		
		
		System.out.println("玩家各自的手牌为：");
		
		for (Player player : players){
			System.out.print(player.getName()+":"+"[");
			for (Poker poker : player.pokers) {
				System.out.print(poker.getSuit()+poker.getNum()+",");
			}		
			System.out.println("]");
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("------------创建扑克牌。。。--------------");
		Play pl= new Play();
		pl.creatPokers("方片",4);pl.creatPokers("梅花",3);
		pl.creatPokers("红桃",2);pl.creatPokers("黑桃",1);
		

		System.out.println("------------扑克牌创建成功！-------------");
		pl.pokersForEach();
		

		System.out.println("------------开始洗牌！-------------");
		pl.pokersShuffle();
		pl.pokersForEach();
		
		
		System.out.println("------------创建玩家！-------------");
		pl.createPlayers();
		pl.playerIterator();
		
		System.out.println("------------开始发牌。。。-------------");
		pl.dealPokerToPlayers();
		
		System.out.println("------------开始游戏。。。-------------");
		pl.startPlayGame();
	}

	

	
}
