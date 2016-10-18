package domain.builders;

import domain.Profile;
import domain.types.Type;

public class ProfileBuilder {
	
	private Type typeOfFilm;
	private Type typeOfMusic;
	private Type typeOfFood;
	private int limitAmount;
	
	public ProfileBuilder(){
		this.typeOfFilm = Type.NO_FILM;
		this.typeOfMusic = Type.NO_MUSIC;
		this.typeOfFood = Type.NO_FOOD;
		this.limitAmount = 0;
    }
    
	public static ProfileBuilder aProfile(){
        return new ProfileBuilder();
    }
	
	 public Profile build(){
		 Profile profile =  new Profile(typeOfFilm, typeOfMusic, typeOfFood, limitAmount);
	     return profile;
	 }
	 
	 public ProfileBuilder withTypeOfFilm(Type typeOfFilm){
		 this.typeOfFilm = typeOfFilm;
	     return this;
	 }
	 
	 public ProfileBuilder withTypeOfMusic(Type typeOfMusic){
		 this.typeOfMusic = typeOfMusic;
	     return this;
	 }
	 
	 public ProfileBuilder withTypeOfFood(Type typeOfFood){
		 this.typeOfFood = typeOfFood;
	     return this;
	 }
	 
	 public ProfileBuilder withLimitAmount(int limitAmount){
		 this.limitAmount = limitAmount;
	     return this;
	 }
}