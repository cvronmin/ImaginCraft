package tk.cvrunmin.lanfasy.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import tk.cvrunmin.lanfasy.entity.LFEntityGhast;
import tk.cvrunmin.lanfasy.entity.LFEntityPigZombie;

public class MLBiomeGenHell extends LFBiomeBase {

	@SuppressWarnings("unchecked")
	public MLBiomeGenHell(int par1) {
		super(par1);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(LFEntityGhast.class, 50, 4, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(LFEntityPigZombie.class, 100, 4, 4));
//		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
	}
}
