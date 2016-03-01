package xyz.brassgoggledcoders.moarcarts.renderers;

/**
 * @author SkySom
 */
public interface IRenderBlock
{
	RenderMethod getRenderMethod();

	enum RenderMethod{VMC,ISBRH,TESR,CUSTOM,COMBO}
}
