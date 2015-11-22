package tk.cvrunmin.lanfasy.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import tk.cvrunmin.lanfasy.item.ItemDremicIngot;
import tk.cvrunmin.lanfasy.item.ItemFireBow;
import tk.cvrunmin.lanfasy.item.ItemInoBow;
import tk.cvrunmin.lanfasy.item.ItemIow;
import tk.cvrunmin.lanfasy.item.ItemLFTrigger;
import tk.cvrunmin.mcme.api.MEHelper;
import tk.cvrunmin.mcme.api.init.Init;
import tk.cvrunmin.mcme.api.item.MEItem;
import tk.cvrunmin.mcme.api.item.MEItemArmor;

public class LFItems extends Init{
	public static final ArmorMaterial drem = MEHelper.newMaterial("drem", "lanfasy:drem", 17, 5, 9, 8, 5, 20);
	public static final MEItem dremic_ingot = new ItemDremicIngot();
	public static final MEItem trigger = new ItemLFTrigger();
	public static final MEItem fire_arrow = (MEItem) new MEItem().setRegisteredName("fire_arrow").setUnlocalizedName("fire_arrow").setCreativeTab(CreativeTabs.tabCombat);
	public static final MEItem fire_bow = new ItemFireBow();
	public static final MEItem iow = new ItemIow();
	public static final ItemInoBow ino_bow = new ItemInoBow();
	public static final ItemInoBow ino_bow_ignite = new ItemInoBow(0);
	public static final ItemInoBow ino_bow_chain = new ItemInoBow(1);
    public static final MEItemArmor drem_helmet = (MEItemArmor) (new MEItemArmor(drem, 2, 0)).setRegisteredName("drem_helmet").setUnlocalizedName("helmetDrem");
    public static final MEItemArmor drem_chestplate = (MEItemArmor) new MEItemArmor(drem, 2, 1).setRegisteredName("drem_chestplate").setUnlocalizedName("chestplateDrem");
    public static final MEItemArmor drem_leggings = (MEItemArmor) new MEItemArmor(drem, 2, 2).setRegisteredName("drem_leggings").setUnlocalizedName("leggingsDrem");
    public static final MEItemArmor drem_boots = (MEItemArmor) new MEItemArmor(drem, 2, 3).setRegisteredName("drem_boots").setUnlocalizedName("bootsDrem");
	public static final MEItem ino_arrow = (MEItem) new MEItem().setRegisteredName("ino_arrow").setUnlocalizedName("ino_arrow").setCreativeTab(CreativeTabs.tabCombat);
    public static void initItems(){
    	registerItem(dremic_ingot);
    	registerItem(trigger);
    	registerItem(fire_arrow);
    	registerItem(ino_arrow);
    	//
    	registerItem(fire_bow);
    	registerItem(iow);
    	registerItem(ino_bow);
    	registerItem(ino_bow_ignite, "ino_bow_0");
    	registerItem(ino_bow_chain, "ino_bow_1");
    	//
    	registerItem(drem_helmet);
    	registerItem(drem_chestplate);
    	registerItem(drem_leggings);
    	registerItem(drem_boots);
    	LFHecItems.initItem();
    }
}
