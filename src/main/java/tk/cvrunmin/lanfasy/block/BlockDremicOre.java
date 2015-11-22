package tk.cvrunmin.lanfasy.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.init.LFItems;
import tk.cvrunmin.mcme.api.block.MEBlock;

public class BlockDremicOre extends MEBlock{

	public BlockDremicOre() {
        super(Material.rock);
		this.setUnlocalizedName("dremicOre");
		this.setRegisteredName("dremic_ore");
		this.setHardness(3.0F);
		this.setResistance(500.0F);
		this.setStepSound(soundTypePiston);
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return LFItems.dremic_ingot;
    }
    public int quantityDropped(Random random)
    {
        return  4 + random.nextInt(5);
    }
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int j = random.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }

            return this.quantityDropped(random) * (j + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
    {
        IBlockState state = world.getBlockState(pos);
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int j = 0;
                j = MathHelper.getRandomIntegerInRange(rand, 3, 7);
            return j;
        }
        return 0;
    }
    public int getDamageValue(World worldIn, BlockPos pos)
    {
        return 0;
    }
}
