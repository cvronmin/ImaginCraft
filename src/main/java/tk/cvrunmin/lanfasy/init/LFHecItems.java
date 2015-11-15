package tk.cvrunmin.lanfasy.init;

import tk.cvrunmin.fansy.api.init.Init;
import tk.cvrunmin.lanfasy.item.hec.*;

public class LFHecItems extends Init{
	public static final ItemHim2D23 him2d23 = new ItemHim2D23();
	public static final ItemRyan ryan = new ItemRyan();
	public static final ItemCHW CHW2a02 = new ItemCHW();
	public static final ItemLCK lck = new ItemLCK();
	public static void initItem(){
		registerItem(him2d23);
		registerItem(ryan);
		registerItem(CHW2a02);
		registerItem(lck);
	}
}
