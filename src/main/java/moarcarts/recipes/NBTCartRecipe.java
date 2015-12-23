package moarcarts.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SkySom
 */
public class NBTCartRecipe extends ShapelessRecipes
{
	protected ItemStack recipeOutput;
	protected ItemStack inputItem;

	public NBTCartRecipe(Item outputCartItem, Block cartBlock)
	{
		this(outputCartItem, cartBlock, 0);
	}

	public NBTCartRecipe(Item outputCartItem, Block cartBlock, int metadata)
	{
		this(new ItemStack(outputCartItem), new ItemStack(Item.getItemFromBlock(cartBlock), 1, metadata));
	}

	public NBTCartRecipe(Item outputCartItem, int itemMetadata, Block cartBlock, int metadata)
	{
		this(new ItemStack(outputCartItem, 1, itemMetadata), new ItemStack(Item.getItemFromBlock(cartBlock), 1, metadata));
	}

	public NBTCartRecipe(ItemStack outputCartItem, ItemStack cartBlock)
	{
		super(outputCartItem, getInputList(cartBlock));

		this.inputItem = cartBlock;
		this.recipeOutput = outputCartItem;
	}

	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
	{
		ItemStack output = this.recipeOutput.copy();
		for(int slot = 0; slot < inventoryCrafting.getSizeInventory(); slot++)
		{
			ItemStack currentSlotStack = inventoryCrafting.getStackInSlot(slot);
			if(currentSlotStack != null && currentSlotStack.getItem() != null)
			{
				if(inputItem != null && inputItem.getItem() != null)
				{
					if(currentSlotStack.getItem().equals(inputItem.getItem()))
					{
						if(currentSlotStack.hasTagCompound())
						{
							NBTTagCompound outputTagCompound;
							if(output.hasTagCompound())
							{
								outputTagCompound = output.getTagCompound();
							} else
							{
								outputTagCompound = new NBTTagCompound();
							}
							outputTagCompound.setTag("tilenbt", currentSlotStack.getTagCompound());
							output.setTagCompound(outputTagCompound);
						}
						if(currentSlotStack.hasDisplayName())
						{
							output = output.setStackDisplayName(currentSlotStack.getDisplayName());
						}
					}
				}

			}

		}
		return output;
	}

	@SuppressWarnings("unchecked")
	public static List getInputList(ItemStack cartBlock)
	{
		List<ItemStack> list = new ArrayList();
		list.add(cartBlock);
		list.add(new ItemStack(Items.minecart));
		return list;
	}
}
