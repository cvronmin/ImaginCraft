package tk.cvrunmin.lanfasy.util;

import java.util.Set;

import tk.cvrunmin.lanfasy.client.gui.LFGuiIngameConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class LFGuiFactory implements IModGuiFactory{
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement arg0) {
		return null;
	}

	public void initialize(Minecraft arg0) {
	}

	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return LFGuiIngameConfig.class;
	}

	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
//		return categories;
		return null;
	}
}
