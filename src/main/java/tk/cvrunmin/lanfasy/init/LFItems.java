package tk.cvrunmin.lanfasy.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cvrunmin.lanfasy.item.ItemDremicIngot;
import tk.cvrunmin.lanfasy.item.ItemFireBow;
import tk.cvrunmin.lanfasy.item.ItemIow;
import tk.cvrunmin.lanfasy.item.ItemLFTrigger;
import tk.cvrunmin.lanfasy.item.LFItem;

public class LFItems {
	public static final ArmorMaterial drem = EnumHelper.addArmorMaterial("drem", "lanfasy:drem", 17, new int[] {5, 9, 8, 5}, 20);
	public static final LFItem dremic_ingot = new ItemDremicIngot();
	public static final LFItem trigger = new ItemLFTrigger();
	public static final LFItem fire_arrow = (LFItem) new LFItem().setRegisteredName("fire_arrow").setUnlocalizedName("fire_arrow").setCreativeTab(CreativeTabs.tabCombat);
	public static final LFItem fire_bow = new ItemFireBow();
	public static final LFItem iow = new ItemIow();
    public static final ItemArmor drem_helmet = (ItemArmor) (new ItemArmor(drem, 2, 0)).setUnlocalizedName("helmetDrem");
    public static final ItemArmor drem_chestplate = (ItemArmor) new ItemArmor(drem, 2, 1).setUnlocalizedName("chestplateDrem");
    public static final ItemArmor drem_leggings = (ItemArmor) new ItemArmor(drem, 2, 2).setUnlocalizedName("leggingsDrem");
    public static final ItemArmor drem_boots = (ItemArmor) new ItemArmor(drem, 2, 3).setUnlocalizedName("bootsDrem");
    public static void initItems(){
    	registerItem(dremic_ingot);
    	registerItem(trigger);
    	registerItem(fire_arrow);
    	registerItem(fire_bow);
    	registerItem(iow);
    	registerItem(drem_helmet, "drem_helmet");
    	registerItem(drem_chestplate, "drem_chestplate");
    	registerItem(drem_leggings, "drem_leggings");
    	registerItem(drem_boots, "drem_boots");
    }
	private static void registerItem(LFItem item)
	{
		GameRegistry.registerItem(item, item.getRegisteredName());
	}
	private static void registerItem(Item item, String registerName)
	{
		GameRegistry.registerItem(item, registerName);
	}
}
