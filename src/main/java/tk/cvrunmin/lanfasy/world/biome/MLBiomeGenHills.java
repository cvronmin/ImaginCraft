package tk.cvrunmin.lanfasy.world.biome;


public class MLBiomeGenHills extends LFBiomeBase{
	private int field_150635_aE;
	private int field_150636_aF;
	private int field_150637_aG;
	private int field_150638_aH;
	public MLBiomeGenHills(int par1, boolean register) {
		super(par1);
		this.field_150635_aE = 0;
		this.field_150636_aF = 1;
		this.field_150637_aG = 2;
		this.field_150638_aH = this.field_150635_aE;
		setHeight(height_MidHills);
		setTemperatureRainfall(0.2F, 0.3F);
		if (!(register))
			return;
		this.theBiomeDecorator.treesPerChunk = 3;
		this.field_150638_aH = this.field_150636_aF;
	}

}
