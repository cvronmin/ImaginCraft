package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.Lanfasy;

public class BlockDIMChanger extends LFBlockContainer{

	public BlockDIMChanger(Material materialIn) {
		super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setUnlocalizedName("dim_change");
        this.setRegisteredName("dim_changer");
	}
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            playerIn.openGui(Lanfasy.instance, 1002, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
    }
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return null;
	}
}
