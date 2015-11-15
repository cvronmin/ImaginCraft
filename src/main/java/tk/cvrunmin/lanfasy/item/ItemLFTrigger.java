package tk.cvrunmin.lanfasy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tk.cvrunmin.fansy.api.item.FItem;
import tk.cvrunmin.lanfasy.init.LFBlocks;

public class ItemLFTrigger extends FItem{
	public ItemLFTrigger(){
		  super();
		  this.maxStackSize = 1;
		  setMaxDamage(64);
		  setCreativeTab(CreativeTabs.tabTools);
		  setUnlocalizedName("lfTrigger");
		  setRegisteredName("lf_trigger");
	}
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
	        pos = pos.offset(side);

	        if (!playerIn.canPlayerEdit(pos, side, stack))
	        {
	            return false;
	        }
	        else
	        {
	            if (worldIn.isAirBlock(pos))
	            {
	                worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
	                worldIn.setBlockState(pos, LFBlocks.lffire.getDefaultState());
	            }
/*		           EntityPlayerMP thePlayer = PlayerUtil.getPlayerMPfromPlayer(playerIn);
		           if (thePlayer.timeUntilPortal > 0)
		           {
		                   thePlayer.timeUntilPortal = 10;
		           }
		           else if (thePlayer.dimension != 1010)
		           {
		                   thePlayer.timeUntilPortal = 10;
		                   thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 1010, new TeleporterGenatic(thePlayer.mcServer.worldServerForDimension(1010)));
		           }
		           else {
		                   thePlayer.timeUntilPortal = 10;
		                   thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterGenatic(thePlayer.mcServer.worldServerForDimension(0)));
		           }*/
	            stack.damageItem(1, playerIn);
	            return true;
	        }
	  }
}
