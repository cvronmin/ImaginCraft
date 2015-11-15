package tk.cvrunmin.lanfasy.world.gen;

import java.util.Random;

import tk.cvrunmin.lanfasy.init.LFBlocks;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class LFWorldGenMinable implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionId()) {
		case 0:
			generateDIM0(world, random, chunkX * 16, chunkZ * 16);
		}
/*		int randPosX = chunkX + random.nextInt(16);
		int randPosY = random.nextInt(256);
		int randPosZ = chunkZ + random.nextInt(16);
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 8)).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 4, BlockHelper.forBlock(Blocks.grass))).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 4, BlockHelper.forBlock(Blocks.dirt))).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 4, BlockHelper.forBlock(Blocks.mycelium))).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));*/
	}
	private void generateDIM0(World world, Random random, int x, int z){
		for(int i = 0; i < 15; i++){
		int randPosX = x + random.nextInt(16);
		int randPosY = random.nextInt(256);
		int randPosZ = z + random.nextInt(16);
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 4)).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 1, BlockHelper.forBlock(Blocks.grass))).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 1, BlockHelper.forBlock(Blocks.dirt))).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		(new WorldGenMinable(LFBlocks.dremic_ore.getDefaultState(), 1, BlockHelper.forBlock(Blocks.mycelium))).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		}
	}
}
