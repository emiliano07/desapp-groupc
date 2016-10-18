package domain;

import domain.types.Type;

public class Profile extends Entity{
	
	private static final long serialVersionUID = -6473516919711717013L;
	public Type typeOfFilm;
	public Type typeOfMusic;
	public Type typeOfFood;
	public int limitAmount;
	
	public Profile () {}
	public Profile(Type typeOfFilm, Type typeOfMusic, Type typeOfFood, int limitAmount){
		this.typeOfFilm = typeOfFilm;
		this.typeOfMusic = typeOfMusic;
		this.typeOfFood = typeOfFood;
		this.limitAmount = limitAmount;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Type getTypeOfFilm() {
		return this.typeOfFilm;
	}

	public Type getTypeOfMusic() {
		return this.typeOfMusic;
	}

	public Type getTypeOfFood() {
		return this.typeOfFood;
	}
	
	public int getLimitAmount() {
		return this.limitAmount;
	}
}