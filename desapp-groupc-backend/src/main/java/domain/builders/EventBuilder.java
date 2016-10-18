package domain.builders;

import java.util.ArrayList;
import java.util.Date;

import domain.Event;
import domain.types.Type;
import domain.types.TypeOfScheduler;

public class EventBuilder {
	
	private ArrayList<Type> types;
	private Date date;
	private TypeOfScheduler scheduler;
	private String address;
	private int amount;
	private int limitOfPersons;
	private ArrayList<Event> suggestions;
	
	
	public EventBuilder(){
		this.types = new ArrayList<Type>();
		this.date = new Date();
		this.scheduler = TypeOfScheduler.NIGHT;
		this.address = "no-address";
		this.amount = 100;
		this.limitOfPersons = 2;
		this.suggestions = new ArrayList<Event>();
    }
    
	public static EventBuilder aEvent(){
        return new EventBuilder();
    }
	
	 public Event build(){
		 Event event =  new Event(types, date, scheduler, address, amount, limitOfPersons);
		 event.setSuggestions(suggestions);
	     return event;
	 }
	 
	 public EventBuilder withTypes( ArrayList<Type> types){
		 this.types = types;
	     return this;
	 }
	 
	 public EventBuilder withDate(Date date){
		 this.date = date;
	     return this;
	 }
	 
	 public EventBuilder withScheduler(TypeOfScheduler scheduler){
		 this.scheduler = scheduler;
	     return this;
	 }
	 
	 public EventBuilder withAddress(String address){
		 this.address = address;
	     return this;
	 }
	 
	 public EventBuilder withAmount(int amount){
		 this.amount = amount;
	     return this;
	 }
	 
	 public EventBuilder withLimitOfPersons(int limitOfPersons){
		 this.limitOfPersons = limitOfPersons;
	     return this;
	 }
	 
	 public EventBuilder withSuggestions(ArrayList<Event> suggestions){
		 this.suggestions = suggestions;
	     return this;
	 }
}