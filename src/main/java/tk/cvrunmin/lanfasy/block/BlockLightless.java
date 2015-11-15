package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.fansy.api.block.FBlock;
import tk.cvrunmin.lanfasy.entity.effect.EntityLightBolt;
import tk.cvrunmin.lanfasy.util.LFConfig;

public class BlockLightless extends FBlock {

	public BlockLightless() {
		super(Material.rock);
		this.setRegisteredName("lightless");
		this.setUnlocalizedName("lightless");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(600.0f);
		this.setResistance(600.0f);
	}
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
    	double d0 = pos.getX();
    	double d1 = pos.getY();
    	double d2 = pos.getZ();
    	if(!LFConfig.safeMode){
            worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, d0 + 1, d1, d2));
            worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, d0, d1, d2 + 1));
            worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, d0 - 1, d1, d2));
            worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, d0, d1, d2 - 1));	
    	}
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
    	double d0 = pos.getX();
    	double d1 = pos.getY();
    	double d2 = pos.getZ();
    	if(LFConfig.safeMode){
            worldIn.addWeatherEffect(new EntityLightBolt(worldIn, d0, d1, d2));
    	}
    	else
        worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, d0, d1, d2));
    }
    @Override
    public int getRenderType()
    {
        return 3;
    }
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
}
