package moarcarts.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * @author SkySom
 */
public class ContainingGui extends GuiContainer
{
	protected GuiContainer containedGui;
	public ContainingGui(Container container, GuiContainer containedGui)
	{
		super(container);
		this.containedGui = containedGui;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {/*DrawScreen*/}

	@Override
	public void initGui()
	{
		this.containedGui.initGui();
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		this.containedGui.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@Override
	public void onGuiClosed()
	{
		this.containedGui.onGuiClosed();
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return this.containedGui.doesGuiPauseGame();
	}

	@Override
	public void updateScreen()
	{
		this.containedGui.updateScreen();
	}

	@Override
	public void handleKeyboardInput()
	{
		this.containedGui.handleKeyboardInput();
	}

	@Override
	public void handleMouseInput()
	{
		this.containedGui.handleMouseInput();
	}

	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height)
	{
		this.containedGui.setWorldAndResolution(mc, width, height);
	}
}
