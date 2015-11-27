package tk.cvrunmin.lanfasy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.init.LFItems;

public class LFTabs {
/*	public static CreativeTabs tab1 = new CreativeTabs("Emply") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem(){
                return LFItems.dremic_ingot;
        }
    };*/
	public static CreativeTabs tab2 = new CreativeTabs("hect") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem(){
                return LFItems.dremic_ingot;
        }
    };
}
