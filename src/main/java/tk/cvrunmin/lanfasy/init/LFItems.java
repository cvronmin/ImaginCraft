package tk.cvrunmin.lanfasy.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cvrunmin.lanfasy.item.ItemDremicIngot;
import tk.cvrunmin.lanfasy.item.ItemFireBow;
import tk.cvrunmin.lanfasy.item.ItemIow;
import tk.cvrunmin.lanfasy.item.ItemLFTrigger;
import tk.cvrunmin.lanfasy.item.LFItem;

public class LFItems {
	public static final LFItem dremic_ingot = new ItemDremicIngot();
	public static final LFItem trigger = new ItemLFTrigger();
	public static final LFItem fire_arrow = (LFItem) new LFItem().setRegisteredName("fire_arrow").setUnlocalizedName("fire_arrow").setCreativeTab(CreativeTabs.tabCombat);
	public static final LFItem fire_bow = new ItemFireBow();
	public static final LFItem iow = new ItemIow();
    public static void initItems(){
    	registerItem(dremic_ingot);
    	registerItem(trigger);
    	registerItem(fire_arrow);
    	registerItem(fire_bow);
    	registerItem(iow);
    }
	private static void registerItem(LFItem item)
	{
		GameRegistry.registerItem(item, item.getRegisteredName());
	}
}
