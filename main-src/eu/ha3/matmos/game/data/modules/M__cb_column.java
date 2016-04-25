package eu.ha3.matmos.game.data.modules;

import eu.ha3.matmos.engine.core.interfaces.Data;
import eu.ha3.matmos.game.data.MODULE_CONSTANTS;
import eu.ha3.matmos.game.data.abstractions.module.Module;
import eu.ha3.matmos.game.data.abstractions.module.ModuleProcessor;
import eu.ha3.matmos.game.system.MAtmosUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

/*
--filenotes-placeholder
*/

public class M__cb_column extends ModuleProcessor implements Module
{
	public M__cb_column(Data data)
	{
		super(data, "cb_column");
		
		EI("y-2", "Block under the feet");
		EI("y-1", "Block at the legs");
		EI("y0", "Block at the body (y)");
		EI("y1", "Block over the head");
		EI("topmost_block", "y coordinate of the top most solid block");
		EI("thickness_overhead", "Number of blocks over the player until topmost_block");
		EI("can_rain_reach", "Can rain reach y?");
	}
	
	@Override
	protected void doProcess()
	{
		World w = Minecraft.getMinecraft().theWorld;

		// Aussiemon edit 1.8 -> 1.7.10
//        BlockPos pos = MAtmosUtility.getPlayerPosition();
//        BlockPos topMostBlock = w.getTopSolidOrLiquidBlock(pos);
		
		int x = MAtmosUtility.getPlayerX();
		int y = MAtmosUtility.getPlayerY();
		int z = MAtmosUtility.getPlayerZ();
		
		// Aussiemon edit 1.8 -> 1.7.10
//		setValue("y-1", MAtmosUtility.getNameAt(pos.getX(), pos.getY() - 1, pos.getZ(), MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
//        setValue("y-2", MAtmosUtility.getNameAt(pos.getX(), pos.getY() - 2, pos.getZ(), MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
//		setValue("y0", MAtmosUtility.getNameAt(pos.getX(), pos.getY(), pos.getZ(), MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
//		setValue("y1", MAtmosUtility.getNameAt(pos.getX(), pos.getY() + 1, pos.getZ(), MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
//		setValue("topmost_block", topMostBlock.getY());
//		setValue("thickness_overhead", topMostBlock.getY() - pos.getY());
//		setValue("can_rain_reach", w.canSeeSky(pos) && !(topMostBlock.getY() > pos.getY()));
		
		setValue("y-1", MAtmosUtility.getNameAt(x, y - 1, z, MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
		setValue("y-2", MAtmosUtility.getNameAt(x, y - 2, z, MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
		setValue("y0", MAtmosUtility.getNameAt(x, y + 0, z, MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
		setValue("y1", MAtmosUtility.getNameAt(x, y + 1, z, MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS));
		setValue("topmost_block", w.getTopSolidOrLiquidBlock(x, z));
		setValue("thickness_overhead", w.getTopSolidOrLiquidBlock(x, z) - y);
		setValue("can_rain_reach", w.canBlockSeeTheSky(x, y, z) && !(w.getTopSolidOrLiquidBlock(x, z) > y));
	}
}