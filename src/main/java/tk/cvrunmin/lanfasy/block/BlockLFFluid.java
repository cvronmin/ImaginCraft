package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import tk.cvrunmin.lanfasy.init.LFBlocks;

public class BlockLFFluid{
//extends BlockFluidClassic{
/*	private String registeredName;
	public BlockLFFluid(Fluid fluid, Material material) {
		super(fluid, material);
        setCreativeTab(CreativeTabs.tabMisc);
	}
    @Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        world.scheduleUpdate(pos, this, tickRate);
		checkFluid(world, pos);
	}
    @Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
        world.scheduleUpdate(pos, this, tickRate);
		checkFluid(world, pos);
	}
    private void checkFluid(World world, BlockPos pos){
        if (world.getBlockState(pos).getBlock() == LFBlocks.lavastill)
        {
        	boolean flag = false;
            if (flag || world.getBlockState(pos.add(0, 0, -1)).getBlock() == LFBlocks.waterstill)
            {
                flag = true;
            }

            if (flag || world.getBlockState(pos.add(0, 0, 1)).getBlock() == LFBlocks.waterstill)
            {
                flag = true;
            }

            if (flag || world.getBlockState(pos.add(-1, 0, 0)).getBlock() == LFBlocks.waterstill)
            {
                flag = true;
            }

            if (flag || world.getBlockState(pos.add(1, 0, 0)).getBlock() == LFBlocks.waterstill)
            {
                flag = true;
            }

            if (flag || world.getBlockState(pos.add(0, 1, 0)).getBlock() == LFBlocks.waterstill)
            {
                flag = true;
            }
            if (flag)
            {
                int l = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));

                if (l == 0)
                {
                    world.setBlockState(pos, Blocks.obsidian.getDefaultState());
                }
                else if (l <= 4)
                {
                    world.setBlockState(pos, Blocks.cobblestone.getDefaultState());
                }
                }
        }
    }
	public String getRegisteredName() {
		return registeredName;
	}
	public BlockLFFluid setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
		return this;
	}*/
}
