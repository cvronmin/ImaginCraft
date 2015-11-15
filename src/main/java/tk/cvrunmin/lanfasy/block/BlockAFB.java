package tk.cvrunmin.lanfasy.block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.google.common.collect.Lists;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import tk.cvrunmin.fansy.api.block.FBlock;
import tk.cvrunmin.lanfasy.util.LogHelper;

public class BlockAFB extends FBlock{
	public static final PropertyBool GHOST = PropertyBool.create("ghost");
	public BlockAFB() {
		super(Material.rock);
		this.setRegisteredName("altifect_block");
		this.setUnlocalizedName("afblock");
		this.setHardness(7f);
		this.setHarvestLevel("pickaxe", 1,this.blockState.getBaseState().withProperty(GHOST, Boolean.valueOf(false)));
        this.setDefaultState(this.blockState.getBaseState().withProperty(GHOST, Boolean.valueOf(false)));
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {GHOST});
    }
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(GHOST, Boolean.valueOf((meta & 1) == 1));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(GHOST)).booleanValue() ? 1 : 0;
    }
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
    	if(!((Boolean)state.getValue(GHOST)).booleanValue()){
    	worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(GHOST, Boolean.valueOf(true)), 3);
    	}
    }
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
    	worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(GHOST, Boolean.valueOf(true)), 3);
    }
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
    {
    	if(!((Boolean)state.getValue(GHOST)).booleanValue()){
    	worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(GHOST, Boolean.valueOf(true)), 3);
    	}
    }
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
    	if(!((Boolean)state.getValue(GHOST)).booleanValue()){
    	worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(GHOST, Boolean.valueOf(true)), 3);
    	}
    }
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return !((Boolean)state.getValue(GHOST)).booleanValue() ? 1 : 0;
    }

}
