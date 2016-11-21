package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import domain.servicesRest.serialization.JodaDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import domain.types.Type;
import domain.types.TypeOfScheduler;


public class Event extends Entity{

	private static final long serialVersionUID = 5114264723960999199L;
	public List<Type> types;
	@JsonSerialize(using= DateTimeSerializer.class)
    @JsonDeserialize(using= JodaDateTimeDeserializer.class)
	public DateTime date;
	public TypeOfScheduler scheduler;
	public String address;
	public int amount;
	public int limitOfPersons;
	public List<Event> suggestions;
	public String nameOfEvent;
	public String description;
	public String image;
	
	public Event(){}
	public Event(List<Type> types, DateTime date, TypeOfScheduler scheduler, String address, int amount, int limitOfPersons, String nameOfEvent, String description, String image){
		this.types = types;
		this.date = date;
		this.scheduler = scheduler;
		this.address = address;
		this.amount = amount;
		this.limitOfPersons = limitOfPersons;
		this.suggestions = new ArrayList<Event>();
		this.nameOfEvent = nameOfEvent;
		this.description = description;
		this.image = image;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void newSuggestion(Event event){
		this.suggestions.add(event);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int getAmount(){
		return this.amount;
	}
	
	public DateTime getDate(){
		return this.date;
	}
	
	public TypeOfScheduler getScheduler(){
		return this.scheduler;
	}

	public int getLimitOfPersons(){
		return this.limitOfPersons;
	}
	
	public void setSuggestions(ArrayList<Event> suggestions) {
		this.suggestions = suggestions;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setNameOfEvent(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
	}
}