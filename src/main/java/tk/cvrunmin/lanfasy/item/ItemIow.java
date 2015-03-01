package tk.cvrunmin.lanfasy.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.entity.projectile.EntityFireArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityIrrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntitySafeIrrow;
import tk.cvrunmin.lanfasy.init.LFItems;
import tk.cvrunmin.lanfasy.util.LFConfig;

public class ItemIow extends LFItem{
    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};

    public ItemIow()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(768);
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setUnlocalizedName("iow");
        this.setRegisteredName("iow");
    }
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player,
			int useRemaining) {
		if ((player.getItemInUse() != null)
				&& (stack.getItem() == LFItems.iow)) {
			int j = stack.getMaxItemUseDuration() - player.getItemInUseCount();

	        if (j >= 18)
	        {
	            return new ModelResourceLocation("lanfasy:iow_pulling_2", "inventory");
	        }
	        else if (j > 13)
	        {
	        	return new ModelResourceLocation("lanfasy:iow_pulling_1", "inventory");
	        }
	        else if (j > 0)
	        {
	        	return new ModelResourceLocation("lanfasy:iow_pulling_0", "inventory");
	        }
		}
		return super.getModel(stack, player, useRemaining);
	}
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
    {
        int j = this.getMaxItemUseDuration(stack) - timeLeft;
        net.minecraftforge.event.entity.player.ArrowLooseEvent event = new net.minecraftforge.event.entity.player.ArrowLooseEvent(playerIn, stack, j);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return;
        j = event.charge;

        boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (flag || playerIn.inventory.hasItem(LFItems.fire_arrow))
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

//            EntityFireArrow entityarrow = new EntityFireArrow(worldIn, playerIn, f * 2.0F * 2f);
            EntitySafeIrrow entityarrow = LFConfig.safeMode ? new EntitySafeIrrow(worldIn, playerIn, f * 2.0F * 2f) : new EntityIrrow(worldIn, playerIn, f * 2.0F * 2f);

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
                playerIn.inventory.consumeInventoryItem(LFItems.fire_arrow);
            }

            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

            if (!worldIn.isRemote)
            {
                worldIn.spawnEntityInWorld(entityarrow);
            }
        }
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        return stack;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        net.minecraftforge.event.entity.player.ArrowNockEvent event = new net.minecraftforge.event.entity.player.ArrowNockEvent(playerIn, itemStackIn);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return event.result;

        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(LFItems.fire_arrow))
        {
            playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        }

        return itemStackIn;
    }

    public int getItemEnchantability()
    {
        return 1;
    }
}
