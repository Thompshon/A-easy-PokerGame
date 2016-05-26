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
	 * ����һ���˿���
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
	 * foreach ��ʾ�����õ��˿���
	 */
	public void pokersForEach(){
		
		System.out.print("�˿���Ϊ��[");
		for (Poker pk:pokers){
			System.out.print(pk.getSuit()+pk.getNum()+",");
		}
		System.out.println("]");
	}
	
	/**
	 * �����˿��Ƶ�˳��
	 */
	public void pokersShuffle(){
		//���˿�������
		Collections.shuffle(pokers);
		System.out.println("------------ϴ�ƽ�����-------------");
	}
	
	/**
	 * ����2�����
	 */
	public void createPlayers(){

		int playersNum=0;//��Ҹ�����־λ
		while(true){
			//���������������
			System.out.println("�������"+(playersNum+1)+"λ��ҵ�ID��������");
			System.out.println("����ID��");
			String id = console.next();
			boolean flag=true;//���ֱ�־λ��0Ϊ���֣���0Ϊ������
			for(int j=0;j<id.length();j++){
				if(!Character.isDigit(id.charAt(j))){	
					System.out.println("������������ID��");
					flag=false;
					break;					
				}
			}
			if(flag){
				playersNum++;
				System.out.println("����������");
				String name = console.next();
				players.add(new Player(Integer.valueOf(id),name));
				//�����ɹ���Ҹ�����־�˳�
				if(playersNum==2)
					break;
			}
		}
	}
	/**
	 * Iterator ��ʾ�����õ����
	 */
	public void playerIterator(){
		//����һ���������õ����������������
		Iterator<Player> it = players.iterator();	
		while (it.hasNext()){
			Player pr= it.next();
			System.out.println("----��ӭ��ң�"+pr.getName());
		}
	}
	
	/**
	 * �ӵ�һ�ſ�ʼ��ÿ��һ�ŵ�˳��ʼ����,ÿ�˷�����
	 */
	public void dealPokerToPlayers(){
		for (int i=0;i<players.size()*2;i++){
			//jΪ��Ϊ�ڼ�λ��ҷ���
			int j=i%players.size();
			Player player = players.get(j);
			System.out.println("----��ң�"+player.getName()+"����");
			player.pokers.add(pokers.get(i));
		}
		System.out.println("------------���ƽ���-------------");
	}
	
	/**
	 * ��ʼ��������Ϸ,�ȱȽϴ�С�㣬���������ͬ����Ƚϻ�ɫ������Ƭ>÷��>����>���ҵ�˳��Ƚ�
	 */
	public void startPlayGame(){
		//����������������е������
		List<Poker> maxPoker = new ArrayList<Poker>();
		//�Ƚ�������е����ƣ����ÿ��������е������
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
			System.out.println(player.getName()+"�������Ϊ��"+player.pokers.get(tempflag).getSuit()
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
		System.out.println("------��ң�"+players.get(flag).getName()+"��ʤ��-------");
		
		
		System.out.println("��Ҹ��Ե�����Ϊ��");
		
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
		System.out.println("------------�����˿��ơ�����--------------");
		Play pl= new Play();
		pl.creatPokers("��Ƭ",4);pl.creatPokers("÷��",3);
		pl.creatPokers("����",2);pl.creatPokers("����",1);
		

		System.out.println("------------�˿��ƴ����ɹ���-------------");
		pl.pokersForEach();
		

		System.out.println("------------��ʼϴ�ƣ�-------------");
		pl.pokersShuffle();
		pl.pokersForEach();
		
		
		System.out.println("------------������ң�-------------");
		pl.createPlayers();
		pl.playerIterator();
		
		System.out.println("------------��ʼ���ơ�����-------------");
		pl.dealPokerToPlayers();
		
		System.out.println("------------��ʼ��Ϸ������-------------");
		pl.startPlayGame();
	}

	

	
}
