package tk.cvrunmin.lanfasy.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.FMLRelaunchLog;

import org.apache.logging.log4j.Level;

import tk.cvrunmin.lanfasy.init.LFItems;
import tk.cvrunmin.lanfasy.util.DamageSourceLF;

public class BlockDamaged extends LFBlock {
	private Random rand = new Random();
	public BlockDamaged() {
		super(Material.ground);
		this.setRegisteredName("damaged");
		this.setUnlocalizedName("damaged");
	}
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	if(!entityIn.isDead){
    	if(!(entityIn instanceof EntityPlayerMP)){
    	    entityIn.attackEntityFrom(DamageSourceLF.enterDamagedArea, rand.nextInt());
    	    if(!(entityIn instanceof EntityPlayer)){
    		FMLRelaunchLog.log("Lanfasy", Level.INFO, "Damaged Block detected and hurted an entity");
    	}
    	    return;
    	}
    	EntityPlayer player = (EntityPlayer)entityIn;
    	for(ItemStack stack : player.inventory.armorInventory){
    		if(stack != null && stack.getItem() == LFItems.drem_boots){
    			return;
    		}
    		else{
        	    entityIn.attackEntityFrom(DamageSourceLF.enterDamagedArea, rand.nextInt());
        		FMLRelaunchLog.log("Lanfasy", Level.INFO, "Damaged Block detected and hurted a player");
    		}
    	}
    	}
    }
    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean isFullCube()
    {
        return false;
    }
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }
}
