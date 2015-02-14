package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LFBlockBreakable extends LFBlock {
    private boolean ignoreSimilarity;

    protected LFBlockBreakable(Material materialIn, boolean ignoreSimilarityIn)
    {
        super(materialIn);
        this.ignoreSimilarity = ignoreSimilarityIn;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        return !this.ignoreSimilarity && block == this ? false : super.shouldSideBeRendered(worldIn, pos, side);
    }
}
