package tk.cvrunmin.lanfasy.item.hec;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import tk.cvrunmin.fansy.api.item.FItem;
import tk.cvrunmin.lanfasy.LFTabs;

public class ItemHim2D23 extends FItem{
	int time;
	public ItemHim2D23(){
		this.setRegisteredName("HIM2D23");
		this.setUnlocalizedName("him2d23");
		this.setCreativeTab(LFTabs.tab2);
	}
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
    	time = count;
    }
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        return stack;
    }
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
    {
        for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j)
        {
            WorldServer worldserver = MinecraftServer.getServer().worldServers[j];
            worldserver.setWorldTime(worldserver.getWorldTime() + (long)time);
        }
    }
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j)
        {
            WorldServer worldserver = MinecraftServer.getServer().worldServers[j];
            worldserver.setWorldTime(worldserver.getWorldTime() + 100L);
        }
        return true;
    }
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
}
