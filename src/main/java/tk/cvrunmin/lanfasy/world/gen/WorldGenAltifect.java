package tk.cvrunmin.lanfasy.world.gen;

import java.util.Random;

import net.minecraft.block.BlockLadder;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import tk.cvrunmin.lanfasy.entity.item.EntityGreenstal;
import tk.cvrunmin.lanfasy.entity.item.EntityRedstal;
import tk.cvrunmin.lanfasy.init.LFBlocks;

public class WorldGenAltifect extends WorldGenerator{

	@Override
	public boolean generate(World worldIn, Random rand,
			BlockPos pos) {		

		for(int x = -16; x <= 16; ++x){
			for(int z = -16;z <= 16;++z){
				worldIn.setBlockState(pos.add(x, 0, z), LFBlocks.afb.getDefaultState(), 3);
			}
		}
		worldIn.setBlockState(pos.add(-16, 0, -16), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 16, 0, -16), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 16, 0,  16), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add(-16, 0,  16), Blocks.sea_lantern.getDefaultState(), 3);
		if(!worldIn.isRemote){
	        EntityGreenstal greenstal1f1 = new EntityGreenstal(worldIn);
	        greenstal1f1.setLocationAndAngles((pos.getX() - 15d), (double)(pos.getY() + 2), (pos.getZ() - 15d), 45.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal1f1);
	        EntityGreenstal greenstal1f2 = new EntityGreenstal(worldIn);
	        greenstal1f2.setLocationAndAngles((pos.getX() - 15d), (double)(pos.getY() + 2), (pos.getZ() + 16d), 45.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal1f2);
	        EntityGreenstal greenstal1f3 = new EntityGreenstal(worldIn);
	        greenstal1f3.setLocationAndAngles((pos.getX() + 16d), (double)(pos.getY() + 2), (pos.getZ() - 15d), 45.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal1f3);
	        EntityGreenstal greenstal1f4 = new EntityGreenstal(worldIn);
	        greenstal1f4.setLocationAndAngles((pos.getX() + 16d), (double)(pos.getY() + 2), (pos.getZ() + 16d), 45.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal1f4);
		}
		for(int x = -10;x <= 10; ++x){
			for(int z = -10; z <= 10; ++z){
			worldIn.setBlockState(pos.add(x, 5, z), LFBlocks.afb.getDefaultState(), 3);
		}
		}
		worldIn.setBlockState(pos.add(-10, 5, -10), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 10, 5, -10), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 10, 5,  10), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add(-10, 5,  10), Blocks.sea_lantern.getDefaultState(), 3);
		if(!worldIn.isRemote){
	        EntityGreenstal greenstal2f1 = new EntityGreenstal(worldIn);
	        greenstal2f1.setLocationAndAngles((pos.getX() - 9d), (double)(pos.getY() + 7), (pos.getZ() - 9), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal2f1);
	        EntityGreenstal greenstal2f2 = new EntityGreenstal(worldIn);
	        greenstal2f2.setLocationAndAngles((pos.getX() - 9d), (double)(pos.getY() + 7), (pos.getZ() + 10d), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal2f2);
	        EntityGreenstal greenstal2f3 = new EntityGreenstal(worldIn);
	        greenstal2f3.setLocationAndAngles((pos.getX() + 10d), (double)(pos.getY() + 7), (pos.getZ() - 9), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal2f3);
	        EntityGreenstal greenstal2f4 = new EntityGreenstal(worldIn);
	        greenstal2f4.setLocationAndAngles((pos.getX() + 10d), (double)(pos.getY() + 7), (pos.getZ() + 10d), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal2f4);
		}
		for(int x = -8;x <= 8; ++x){
			for(int z = -8; z <= 8; ++z){
			worldIn.setBlockState(pos.add(x, 10, z), LFBlocks.afb.getDefaultState(), 3);
		}
		}
		worldIn.setBlockState(pos.add(-8, 10, -8), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 8, 10, -8), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 8, 10,  8), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add(-8, 10,  8), Blocks.sea_lantern.getDefaultState(), 3);
		if(!worldIn.isRemote){
	        EntityGreenstal greenstal3f1 = new EntityGreenstal(worldIn);
	        greenstal3f1.setLocationAndAngles((pos.getX() - 7), (double)(pos.getY() + 12), (pos.getZ() - 7), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal3f1);
	        EntityGreenstal greenstal3f2 = new EntityGreenstal(worldIn);
	        greenstal3f2.setLocationAndAngles((pos.getX() - 7), (double)(pos.getY() + 12), (pos.getZ() + 8d), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal3f2);
	        EntityGreenstal greenstal3f3 = new EntityGreenstal(worldIn);
	        greenstal3f3.setLocationAndAngles((pos.getX() + 8d), (double)(pos.getY() + 12), (pos.getZ() - 7), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal3f3);
	        EntityGreenstal greenstal3f4 = new EntityGreenstal(worldIn);
	        greenstal3f4.setLocationAndAngles((pos.getX() + 8d), (double)(pos.getY() + 12), (pos.getZ() + 8d), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal3f4);
		}
		for(int x = -4;x <= 4; ++x){
			for(int z = -4; z <= 4; ++z){
			worldIn.setBlockState(pos.add(x, 15, z), LFBlocks.afb.getDefaultState(), 3);
		}
		}
		worldIn.setBlockState(pos.add(-4, 15, -4), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 4, 15, -4), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add( 4, 15,  4), Blocks.sea_lantern.getDefaultState(), 3);
		worldIn.setBlockState(pos.add(-4, 15,  4), Blocks.sea_lantern.getDefaultState(), 3);
		for(int x = -10;x <= 10; ++x){
			for(int z = -10; z <= 10; ++z){
			worldIn.setBlockState(pos.add(x, -5, z), LFBlocks.afb.getDefaultState(), 3);
		}
		}
		for(int x = -7;x <= 7; ++x){
			for(int z = -7; z <= 7; ++z){
			worldIn.setBlockState(pos.add(x, -10, z), LFBlocks.afb.getDefaultState(), 3);
		}
		}
		for(int x = -5;x <= 5; ++x){
			for(int z = -5; z <= 5; ++z){
			worldIn.setBlockState(pos.add(x, -15, z), LFBlocks.afb.getDefaultState(), 3);
		}
		}
		for(int y = -20;y <= 20;++y){
			worldIn.setBlockState(pos.add(0, y, 0), LFBlocks.afb.getDefaultState(), 3);
		}
		for(int y = -19;y <= 20;++y){
			worldIn.setBlockState(pos.add(1, y, 0), Blocks.ladder.getDefaultState().withProperty(((BlockLadder)Blocks.ladder).FACING, EnumFacing.EAST), 3);
			worldIn.setBlockState(pos.add(0, y, 1), Blocks.ladder.getDefaultState().withProperty(((BlockLadder)Blocks.ladder).FACING, EnumFacing.SOUTH), 3);
			worldIn.setBlockState(pos.add(-1, y, 0), Blocks.ladder.getDefaultState().withProperty(((BlockLadder)Blocks.ladder).FACING, EnumFacing.WEST), 3);
			worldIn.setBlockState(pos.add(0, y, -1), Blocks.ladder.getDefaultState().withProperty(((BlockLadder)Blocks.ladder).FACING, EnumFacing.NORTH), 3);
		}
		if(!worldIn.isRemote){
	        EntityRedstal greenstal1 = new EntityRedstal(worldIn);
	        greenstal1.setLocationAndAngles((pos.getX() - 5), (double)(pos.getY() + 2), (pos.getZ() - 5), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal1);
	        EntityRedstal greenstal2 = new EntityRedstal(worldIn);
	        greenstal2.setLocationAndAngles((pos.getX() - 5), (double)(pos.getY() + 7), (pos.getZ() + 5), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal2);
	        EntityRedstal greenstal3 = new EntityRedstal(worldIn);
	        greenstal3.setLocationAndAngles((pos.getX() + 5), (double)(pos.getY() + 12), (pos.getZ() - 5), rand.nextFloat() * 360.0F, 0.0F);
	        worldIn.spawnEntityInWorld(greenstal3);
		}
		return true;
	}

}
