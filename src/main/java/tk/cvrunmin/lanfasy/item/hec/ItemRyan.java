package tk.cvrunmin.lanfasy.item.hec;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tk.cvrunmin.fansy.api.item.FItem;
import tk.cvrunmin.lanfasy.LFTabs;

public class ItemRyan extends FItem {
	public ItemRyan(){
		this.setRegisteredName("ino");
		this.setUnlocalizedName("ryan");
		this.setCreativeTab(LFTabs.tab2);
	}
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	playerIn.heal(1f);
		return true;
    }
}
