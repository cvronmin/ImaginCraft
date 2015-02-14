package tk.cvrunmin.lanfasy.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class LFBlock extends Block{
	private String registeredName;
	private Random rand = new Random();
	public LFBlock(Material materialIn) {
		super(materialIn);
	}
	public String getRegisteredName() {
		String name = null;
		if(registeredName != null){
		 name = registeredName;
		}
		else{
			name = "lanfasy_block_" + Integer.toString(rand.nextInt(256));
		}
		return name;
	}
	public void setRegisteredName(String registerName) {
		this.registeredName = registerName;
	}
}
