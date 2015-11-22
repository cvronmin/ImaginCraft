package tk.cvrunmin.lanfasy.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.entity.projectile.EntityInoArrow;
import tk.cvrunmin.lanfasy.init.LFItems;
import tk.cvrunmin.lanfasy.util.LogHelper;
import tk.cvrunmin.mcme.api.item.MEItem;

public class ItemInoBow extends MEItem{
    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    public int bowState;
    public boolean canTransform;
    public ItemInoBow()
    {
       this(-1);
    }
    public ItemInoBow(int state){
    	if(state < 0){
    	this.bowState = 0;
    	this.canTransform = true;
    	}
    	else{
    		this.bowState = state;
    		this.canTransform = false;
    	}
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setRegisteredName("ino_bow");
        this.setUnlocalizedName("inobow");
    }
    public String getUnlocalizedName(){
    	return canTransform ? "item.inobow.transform" :( bowState == 0 ? "item.inobow.ignite" : (bowState == 1 ? "item.inobow.chain" : "item.inobow"));
    }
    public String getUnlocalizedName(ItemStack stack){
    	return canTransform ? "item.inobow.transform" :( bowState == 0 ? "item.inobow.ignite" : (bowState == 1 ? "item.inobow.chain" : "item.inobow"));
    }
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player,
			int useRemaining) {
		if ((player.getItemInUse() != null)
				&& ((stack.getItem() == LFItems.ino_bow) || (stack.getItem() == LFItems.ino_bow_ignite) || (stack.getItem() == LFItems.ino_bow_chain))) {
			int j = stack.getMaxItemUseDuration() - player.getItemInUseCount();
            if(this.bowState == 0){
	        if (j >= 18)
	        {
	            return new ModelResourceLocation("lanfasy:ino_bow_0_pulling_2", "inventory");
	        }
	        else if (j > 13)
	        {
	        	return new ModelResourceLocation("lanfasy:ino_bow_0_pulling_1", "inventory");
	        }
	        else if (j > 0)
	        {
	        	return new ModelResourceLocation("lanfasy:ino_bow_0_pulling_0", "inventory");
	        }
            }
            else if(this.bowState == 1){
    	        if (j >= 18)
    	        {
    	            return new ModelResourceLocation("lanfasy:ino_bow_1_pulling_2", "inventory");
    	        }
    	        else if (j > 13)
    	        {
    	        	return new ModelResourceLocation("lanfasy:ino_bow_1_pulling_1", "inventory");
    	        }
    	        else if (j > 0)
    	        {
    	        	return new ModelResourceLocation("lanfasy:ino_bow_1_pulling_0", "inventory");
    	        }
            }
		}
		else{
			switch(bowState){
			case 0:
	            return new ModelResourceLocation("lanfasy:ino_bow_0", "inventory");
			case 1:
	            return new ModelResourceLocation("lanfasy:ino_bow_1", "inventory");
			}
		}
		return super.getModel(stack, player, useRemaining);
	}
    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     *  
     * @param timeLeft The amount of ticks left before the using would have been complete
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
    {
        int j = this.getMaxItemUseDuration(stack) - timeLeft;
        net.minecraftforge.event.entity.player.ArrowLooseEvent event = new net.minecraftforge.event.entity.player.ArrowLooseEvent(playerIn, stack, j);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return;
        j = event.charge;

        boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (flag || playerIn.inventory.hasItem(LFItems.ino_arrow))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }
            EntityInoArrow entityarrow = null;
            switch(this.bowState){
            case 0:
            	entityarrow = new EntityInoArrow.Fire(worldIn, playerIn, f * 2.0F);
            	break;
            case 1:
            	entityarrow = new EntityInoArrow.TP(worldIn, playerIn, f * 2.0F);
            	break;
            default:
                entityarrow = new EntityInoArrow.Normal(worldIn, playerIn, f * 2.0F);
                break;
            }
        	LogHelper.debug("Player:" + playerIn.getCommandSenderName());
            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
            {
                entityarrow.setFire(100);
            }

            stack.damageItem(1, playerIn);
            worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                playerIn.inventory.consumeInventoryItem(LFItems.ino_arrow);
            }

            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

            if (!worldIn.isRemote)
            {
                worldIn.spawnEntityInWorld(entityarrow);
            }
        }
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        return stack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        net.minecraftforge.event.entity.player.ArrowNockEvent event = new net.minecraftforge.event.entity.player.ArrowNockEvent(playerIn, itemStackIn);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return event.result;

        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(LFItems.ino_arrow))
        {
            playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        }

        return itemStackIn;
    }
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
    	if(!entityLiving.worldObj.isRemote && canTransform){
    	this.bowState = this.bowState + 1 & 1;
    	LogHelper.info(entityLiving.getCommandSenderName()+"swinging");
    	LogHelper.info("State:" + this.bowState);
    	return true;
    	}
        return false;
    }
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
    
    public String getItemStackDisplayName(ItemStack stack)
    {
    	if(canTransform){
        return ("" + StatCollector.translateToLocal(this.getUnlocalizedName(stack) + ".name")).trim() + "(Type:"+bowState+")";
    	}
    	else return StatCollector.translateToLocal(this.getUnlocalizedName(stack) + ".name").trim();
    }

}
