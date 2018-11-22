package com.aws.data;

public class CurrentIntent {

	private String name ;
	private Slots slots ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Slots getSlots() {
		return slots;
	}
	public void setSlots(Slots slots) {
		this.slots = slots;
	}
	@Override
	public String toString() {
		return "currentIntent [name=" + name + ", slots=" + slots + "]";
	}
	
	
}
