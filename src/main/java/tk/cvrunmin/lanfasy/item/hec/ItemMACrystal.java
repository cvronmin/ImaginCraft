package tk.cvrunmin.lanfasy.item.hec;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.LFTabs;
import tk.cvrunmin.mcme.api.item.MEItem;

public class ItemMACrystal extends MEItem{
	public ItemMACrystal(){
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(LFTabs.tab2);
        this.setRegisteredName("macrystal");
	}
    public String getItemStackDisplayName(ItemStack stack)
    {
        if (stack.getMetadata() == 0)
        {
            return StatCollector.translateToLocal("item.macrystal.name.pure").trim();
        }
        else
        {
        	switch (stack.getMetadata()) {
			case 1:
	            return StatCollector.translateToLocal("item.macrystal.name.prayer").trim();
			case 2:
	            return StatCollector.translateToLocal("item.macrystal.name.txper").trim();
			case 3:
	            return StatCollector.translateToLocal("item.macrystal.name.shere").trim();
			case 4:
	            return StatCollector.translateToLocal("item.macrystal.name.ender").trim();
			case 5:
	            return StatCollector.translateToLocal("item.macrystal.name.power").trim();
			case 6:
	            return StatCollector.translateToLocal("item.macrystal.name.kart").trim();
			case 7:
	            return StatCollector.translateToLocal("item.macrystal.name.dround").trim();
			case 8:
	            return StatCollector.translateToLocal("item.macrystal.name.void").trim();
			case 21:
	            return StatCollector.translateToLocal("item.macrystal.name.O").trim();
			case 22:
	            return StatCollector.translateToLocal("item.macrystal.name.gsound").trim();
			default:
	            return StatCollector.translateToLocal("item.macrystal.name.pure").trim();
			}
        }
    }
    /**
     * allows items to add custom lines of information to the mouseover description
     *  
     * @param tooltip All lines to display in the Item's tooltip. This is a List of Strings.
     * @param advanced Whether the setting "Advanced tooltips" is enabled
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        if (stack.getMetadata() != 0)
        {
        	tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.macrystal.info"));
        }
        else {
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("item.macrystal.info.pure"));
		}
    }
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     *  
     * @param subItems The List of sub-items. This is a List of ItemStacks.
     */
    @SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        super.getSubItems(itemIn, tab, subItems);
        subItems.add(new ItemStack(itemIn, 1, 1));
        subItems.add(new ItemStack(itemIn, 1, 2));
        subItems.add(new ItemStack(itemIn, 1, 3));
        subItems.add(new ItemStack(itemIn, 1, 4));
        subItems.add(new ItemStack(itemIn, 1, 5));
        subItems.add(new ItemStack(itemIn, 1, 6));
        subItems.add(new ItemStack(itemIn, 1, 7));
        subItems.add(new ItemStack(itemIn, 1, 21));
        subItems.add(new ItemStack(itemIn, 1, 22));
    }
}
