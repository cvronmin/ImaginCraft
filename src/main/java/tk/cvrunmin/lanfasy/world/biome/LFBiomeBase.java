package tk.cvrunmin.lanfasy.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import tk.cvrunmin.lanfasy.entity.*;
import tk.cvrunmin.lanfasy.init.LFBlocks;
@SuppressWarnings("unchecked")
public class LFBiomeBase extends BiomeGenBase{
	private final static LFBiomeBase INSTANCE = new LFBiomeBase();
	public static int biomeID;
	public static final BiomeGenBase LFocean = new LFBiomeGenOcean(65).setColor(112)
			.setBiomeName("Crashing Ocean");
	public static final BiomeGenBase LFplains = new LFBiomeGenPlains(66).setColor(9286496)
			.setBiomeName("Crashing Plains");
	public static final BiomeGenBase LFextremeHills = new LFBiomeGenHills(67, false)
	.setColor(6316128).setBiomeName("Crashing Exhills");
	public static final BiomeGenBase LFhell = new LFBiomeGenHell(73)
	.setColor(16711680).setBiomeName("Crashing Hell").setDisableRain()
	.setTemperatureRainfall(2.0F, 0.0F);
	public static final BiomeGenBase LFAF = new LFBiomeAltifect(74).setDisableRain();
	public LFBiomeBase() {
      this(biomeID);
	}
	public LFBiomeBase(int par1) {
		super(par1);
        this.topBlock = LFBlocks.dremic.getDefaultState();
        this.fillerBlock = LFBlocks.dremic.getDefaultState();
        this.minHeight = height_Default.rootHeight;
        this.maxHeight = height_Default.variation;
        this.temperature = 0.5F;
        this.enableRain = true;
        this.rainfall = 0.5F;
    	theBiomeDecorator.treesPerChunk = 0;
    	theBiomeDecorator.flowersPerChunk = 0;
    	theBiomeDecorator.grassPerChunk = 0;
    	theBiomeDecorator.deadBushPerChunk = 0;
    	theBiomeDecorator.mushroomsPerChunk = 0;
    	theBiomeDecorator.reedsPerChunk = 0;
    	theBiomeDecorator.cactiPerChunk = 0;
       	theBiomeDecorator.sandPerChunk = 0;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(LFEntityZombie.class,100,4,4));
        this.spawnableMonsterList.add(new SpawnListEntry(LFEntityCreeper.class,100,4,4));
        this.spawnableMonsterList.add(new SpawnListEntry(LFEntitySkeleton.class,100,4,4));
        this.spawnableMonsterList.add(new SpawnListEntry(LFEntitySpider.class,100,4,4));
        this.spawnableMonsterList.add(new SpawnListEntry(LFEntitySlime.class,100,4,4));
        this.spawnableMonsterList.add(new SpawnListEntry(LFEntityEnderman.class,10,1,4));
	}
    public static LFBiomeBase instance(){
    	return INSTANCE;
    }
	static{
		LFextremeHills.createMutation();
		explorationBiomesList.remove(LFhell);
		explorationBiomesList.remove(LFAF);
	}
	public static void registerWithBiomeDictionary() {
		BiomeDictionary.registerBiomeType(LFocean, new BiomeDictionary.Type[] {});
		BiomeDictionary.registerBiomeType(LFplains, new BiomeDictionary.Type[] {});
		BiomeDictionary.registerBiomeType(LFextremeHills, new BiomeDictionary.Type[] {});
		BiomeDictionary.registerBiomeType(LFhell, new BiomeDictionary.Type[] {});
		BiomeDictionary.registerBiomeType(LFAF, new BiomeDictionary.Type[] {});
	}
}
