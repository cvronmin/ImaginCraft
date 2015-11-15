package tk.cvrunmin.lanfasy.item.hec;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tk.cvrunmin.fansy.api.item.FItem;
import tk.cvrunmin.lanfasy.LFTabs;
import tk.cvrunmin.lanfasy.util.LogHelper;

public class ItemLCK extends FItem{
	Random rand = new Random();
	public ItemLCK(){
		this.setRegisteredName("lck");
		this.setUnlocalizedName("lck");
		this.setCreativeTab(LFTabs.tab2);
	}
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    	if(!worldIn.isRemote){
        int tri = rand.nextInt(10);
//		LogHelper.info("Andrew try " + tri);
    	if(tri < 1){
    	worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, entityIn.prevPosX + rand.nextDouble() * 10 * (rand.nextBoolean() ? -1 : 1), entityIn.prevPosY + rand.nextDouble() * 10 * (rand.nextBoolean() ? -1 : 1), entityIn.prevPosZ + rand.nextDouble() * 10 * (rand.nextBoolean() ? -1 : 1)));
    	}
    	}
    }
}
