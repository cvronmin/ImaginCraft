package tk.cvrunmin.lanfasy.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cvrunmin.lanfasy.item.ItemDremicIngot;
import tk.cvrunmin.lanfasy.item.ItemLFTrigger;
import tk.cvrunmin.lanfasy.item.LFItem;

public class LFItems {
	public static final LFItem dremic_ingot = new ItemDremicIngot();
	public static final LFItem trigger = new ItemLFTrigger();
    public static void initItems(){
    	registerItem(dremic_ingot);
    	registerItem(trigger);
    }
	private static void registerItem(LFItem item)
	{
		GameRegistry.registerItem(item, item.getRegisteredName());
	}
}
