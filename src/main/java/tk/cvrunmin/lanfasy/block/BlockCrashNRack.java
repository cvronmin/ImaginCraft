package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import tk.cvrunmin.fansy.api.block.FBlock;

public class BlockCrashNRack extends FBlock{
    public BlockCrashNRack()
    {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("crashNrack");
        this.setRegisteredName("crash_netherrack");
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.netherrackColor;
    }
}
