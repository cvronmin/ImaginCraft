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

public class ItemMACrystalShard extends MEItem{
	public ItemMACrystalShard(){
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(LFTabs.tab2);
        this.setRegisteredName("shard_macrystal");
	}
    public String getItemStackDisplayName(ItemStack stack)
    {
    	String s = "";
        if (stack.getMetadata() == 0)
        {
            s = StatCollector.translateToLocal("item.macrystal.name.pure").trim();
        }
        else
        {
        	switch (stack.getMetadata()) {
			case 1:
	            s = StatCollector.translateToLocal("item.macrystal.name.prayer").trim();
	            break;
			case 2:
	            s = StatCollector.translateToLocal("item.macrystal.name.txper").trim();
	            break;
			case 3:
	            s = StatCollector.translateToLocal("item.macrystal.name.shere").trim();
	            break;
			case 4:
	            s =  StatCollector.translateToLocal("item.macrystal.name.ender").trim();
	            break;
			case 5:
	            s = StatCollector.translateToLocal("item.macrystal.name.power").trim();
	            break;
			case 6:
	            s = StatCollector.translateToLocal("item.macrystal.name.kart").trim();
	            break;
			case 7:
	            s = StatCollector.translateToLocal("item.macrystal.name.dround").trim();
	            break;
			case 21:
	            s = StatCollector.translateToLocal("item.macrystal.name.O").trim();
	            break;
			case 22:
	            s = StatCollector.translateToLocal("item.macrystal.name.gsound").trim();
	            break;
			default:
	            s = StatCollector.translateToLocal("item.macrystal.name.pure").trim();
	            break;
			}
        }
        return s + StatCollector.translateToLocal("item.macrystal.shard.name").trim();
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
