package tk.cvrunmin.lanfasy.client.gui;

import tk.cvrunmin.lanfasy.util.LFConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class LFGuiIngameConfig extends GuiConfig {
	public LFGuiIngameConfig(GuiScreen parentScreen) {
		super(parentScreen, new ConfigElement(LFConfig.config.getCategory("newelementsettings")).getChildElements(), "lanfasy", false,
				false, "Lanfasy Settings");
	}
}
