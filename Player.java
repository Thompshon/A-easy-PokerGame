package com.yuxuan.Poker;

import java.util.ArrayList;
import java.util.List;


public class Player {
	Player(){
		
	}
	Player(int id ,String name){
		this.name=name;
		this.id=id;
		this.pokers = new ArrayList<Poker>();
	}
	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	private int id;
	private String name;
	public List<Poker> pokers;
}
