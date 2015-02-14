package tk.cvrunmin.lanfasy.item;

import java.util.Random;

import net.minecraft.item.Item;

public class LFItem extends Item {
	private String registeredName;
	private Random rand = new Random();

	public String getRegisteredName() {
		String name = null;
		if(registeredName != null){
		 name = registeredName;
		}
		else{
			name = "lanfasy_item_" + Integer.toString(rand.nextInt(256));
		}
		return name;
	}

	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}
}
