package domain.builders;

import java.util.ArrayList;

import org.joda.time.DateTime;

import domain.Event;
import domain.Tour;
import domain.User;
import domain.types.Type;
import domain.types.TypeOfScheduler;
import domain.types.TypeOfTour;

public class TourBuilder {
	
	private TypeOfTour type;
	private DateTime date;
	private TypeOfScheduler scheduler;
	private int amount;
	private int limitAmount;
	private int friends;
	private ArrayList<Event> eventOptions1;
	private ArrayList<Event> eventOptions2;
	private Event event1;
	private Event event2;
	
	
	public TourBuilder(){
		this.type = TypeOfTour.GASOLERA;
		this.date = new DateTime();
		this.scheduler = TypeOfScheduler.MORNING;
		this.amount = 1000;
		this.limitAmount = 500;
		this.friends = 0;
		this.eventOptions1 = new ArrayList<Event>();
		this.eventOptions2 = new ArrayList<Event>();
		this.event1 = new Event(new ArrayList<Type>(), new DateTime(), TypeOfScheduler.MORNING, "no-address", 100, 2,"no-name","no-description","no-image");
		this.event2 = new Event(new ArrayList<Type>(), new DateTime(), TypeOfScheduler.MORNING, "no-address", 200, 4,"no-name","no-description","no-image");
	}		
    
	public static TourBuilder aTour(){
        return new TourBuilder();
    }
	
	 public Tour build(){
		 Tour tour =  new Tour(type, date, scheduler, limitAmount, friends);
		 tour.setAmount(amount);
		 tour.setEventOptions1(eventOptions1);
		 tour.setEventOptions1(eventOptions2);
		 tour.setEvent1(event1);
		 tour.setEvent2(event2);
	     return tour;
	 }
	 
	 public TourBuilder withType(TypeOfTour type){
		 this.type = type;
	     return this;
	 }
	 
	 public TourBuilder withEvent01(Event event){
		 this.event1 = event;
	     return this;
	 }
	 
	 public TourBuilder withEvent02(Event event){
		 this.event2 = event;
	     return this;
	 }
	 
	 public TourBuilder withAmount(int amount){
		 this.amount = amount;
	     return this;
	 }
	 
	 public TourBuilder withFriends(int friends){
		 this.friends = friends;
	     return this;
	 }
	 
	 public TourBuilder withDate(DateTime date){
		 this.date = date;
	     return this;
	 }
	 
	 public TourBuilder withScheduler(TypeOfScheduler scheduler){
		 this.scheduler = scheduler;
	     return this;
	 }
	 
	 public TourBuilder withEventOptions1(ArrayList<Event> eventOptions){
		 this.eventOptions1 = eventOptions;
	     return this;
	 }
	 
	 public TourBuilder withEventOptions2(ArrayList<Event> eventOptions){
		 this.eventOptions2 = eventOptions;
	     return this;
	 }
	 
	 public TourBuilder withLimitAmount(int limitAmount){
		 this.limitAmount = limitAmount;
	     return this;
	 }
}