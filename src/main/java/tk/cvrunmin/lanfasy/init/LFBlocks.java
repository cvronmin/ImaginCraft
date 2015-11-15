package tk.cvrunmin.lanfasy.init;

import tk.cvrunmin.fansy.api.block.FBlock;
import tk.cvrunmin.fansy.api.init.Init;
import tk.cvrunmin.lanfasy.block.BlockAFB;
import tk.cvrunmin.lanfasy.block.BlockAFPortal;
import tk.cvrunmin.lanfasy.block.BlockCrashNRack;
import tk.cvrunmin.lanfasy.block.BlockDIMChanger;
import tk.cvrunmin.lanfasy.block.BlockDamaged;
import tk.cvrunmin.lanfasy.block.BlockDremic;
import tk.cvrunmin.lanfasy.block.BlockDremicOre;
import tk.cvrunmin.lanfasy.block.BlockLFFire;
import tk.cvrunmin.lanfasy.block.BlockLFPortal;
import tk.cvrunmin.lanfasy.block.BlockLFPortalHell;
import tk.cvrunmin.lanfasy.block.BlockLightless;
import tk.cvrunmin.lanfasy.block.BlockSlimeBoss;
import tk.cvrunmin.lanfasy.block.BlockTextureless;

public class LFBlocks extends Init{
	public final static FBlock dremic = new BlockDremic();
	public final static FBlock dremic_ore = new BlockDremicOre();
	public final static BlockLFPortal lfportal = (BlockLFPortal) new BlockLFPortal().setRegisteredName("lf_portal").setUnlocalizedName("lfportal");
	public final static BlockLFPortalHell portal_crashhell = (BlockLFPortalHell) new BlockLFPortalHell().setRegisteredName("portal_crashhell").setUnlocalizedName("lfportal_hell");
	public final static BlockLFFire lffire = new BlockLFFire();
	public final static FBlock lightless = new BlockLightless();
	public final static FBlock textureless = new BlockTextureless();
	public final static FBlock damaged = new BlockDamaged();
	public final static FBlock crash_netherrack = new BlockCrashNRack();
	public static final BlockAFPortal afportal = new BlockAFPortal();
	public static final FBlock afb = new BlockAFB();
	public static final BlockDIMChanger dimchanger = new BlockDIMChanger();
	public static final BlockSlimeBoss slimeboss = new BlockSlimeBoss();
    public static void initBlocks(){
    	registerBlock(dremic);
    	registerBlock(dremic_ore);
    	registerBlock(lfportal);
    	registerBlock(lffire);
    	registerBlock(lightless);
    	registerBlock(textureless);
    	registerBlock(damaged);
    	registerBlock(crash_netherrack);
    	registerBlock(portal_crashhell);
    	registerBlock(afportal);
    	registerBlock(afb);
    	registerBlock(dimchanger);
    	registerBlock(slimeboss);
    }
}
