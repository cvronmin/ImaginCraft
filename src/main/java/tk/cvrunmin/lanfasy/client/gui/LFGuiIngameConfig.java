package tk.cvrunmin.lanfasy.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import tk.cvrunmin.lanfasy.util.LFConfig;

public class LFGuiIngameConfig extends GuiConfig {
	public LFGuiIngameConfig(GuiScreen parentScreen) {
		super(parentScreen, getConfigElement(), "lanfasy", false,
				false, "Lanfasy Settings");
	}
	public static List<IConfigElement> getConfigElement(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new DummyCategoryElement("Global Config", "gui.config.GS", new ConfigElement(LFConfig.config.getCategory("globelsettings")).getChildElements()));
        list.add(new DummyCategoryElement("New Elements Config", "gui.config.NES", new ConfigElement(LFConfig.config.getCategory("newelementsettings")).getChildElements()));
//		list.addAll(new ConfigElement(LFConfig.config.getCategory("newelementsettings")).getChildElements());
//		list.addAll(new ConfigElement(LFConfig.config.getCategory("globelsettings")).getChildElements());
		return list;
	}
}
