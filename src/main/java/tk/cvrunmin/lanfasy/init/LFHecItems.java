package tk.cvrunmin.lanfasy.init;

import tk.cvrunmin.lanfasy.item.hec.ItemCHW;
import tk.cvrunmin.lanfasy.item.hec.ItemHim2D23;
import tk.cvrunmin.lanfasy.item.hec.ItemLCK;
import tk.cvrunmin.lanfasy.item.hec.ItemMACrystal;
import tk.cvrunmin.lanfasy.item.hec.ItemMACrystalShard;
import tk.cvrunmin.lanfasy.item.hec.ItemRyan;
import tk.cvrunmin.mcme.api.init.Init;

public class LFHecItems extends Init{
	public static final ItemHim2D23 him2d23 = new ItemHim2D23();
	public static final ItemRyan ryan = new ItemRyan();
	public static final ItemCHW CHW2a02 = new ItemCHW();
	public static final ItemLCK lck = new ItemLCK();
	public static final ItemMACrystal macrystal = new ItemMACrystal();
	public static final ItemMACrystalShard macrystalshard = new ItemMACrystalShard();
	public static void initItem(){
		registerItem(him2d23);
		registerItem(ryan);
		registerItem(CHW2a02);
		registerItem(lck);
		registerItem(macrystal);
		registerItem(macrystalshard);
	}
}
