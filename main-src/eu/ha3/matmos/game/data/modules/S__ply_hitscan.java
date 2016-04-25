package eu.ha3.matmos.game.data.modules;

import eu.ha3.matmos.engine.core.interfaces.Data;
import eu.ha3.matmos.game.data.MODULE_CONSTANTS;
import eu.ha3.matmos.game.data.abstractions.module.Module;
import eu.ha3.matmos.game.data.abstractions.module.ModuleProcessor;
import eu.ha3.matmos.game.system.MAtmosUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

import java.util.HashMap;
import java.util.Map;

/*
--filenotes-placeholder
*/

public class S__ply_hitscan extends ModuleProcessor implements Module
{
	private final Map<MovingObjectType, String> equiv = new HashMap<MovingObjectPosition.MovingObjectType, String>();
	
	public S__ply_hitscan(Data data)
	{
		super(data, "ply_hitscan");
		this.equiv.put(MovingObjectType.MISS, "");
		this.equiv.put(MovingObjectType.ENTITY, "entity");
		this.equiv.put(MovingObjectType.BLOCK, "block");
	}
	
	@Override
	protected void doProcess()
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit == null)
		{
			setValue("mouse_over_something", false);
			setValue("mouse_over_what", "");
			setValue("block", MODULE_CONSTANTS.NO_BLOCK_IN_THIS_CONTEXT);
			setValue("meta", MODULE_CONSTANTS.NO_META);
			setValue("powermeta", MODULE_CONSTANTS.NO_POWERMETA);
			setValue("entity_id", MODULE_CONSTANTS.NO_ENTITY);
			
			return;
		}
		
		setValue("mouse_over_something", mc.objectMouseOver.typeOfHit != MovingObjectType.MISS);
		setValue("mouse_over_what", this.equiv.get(mc.objectMouseOver.typeOfHit));
        // dag edit -> getBlockPos().get..()
		// Aussiemon reversed 1.8 -> 1.7.10
		setValue(
			"block",
			mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK ? MAtmosUtility.getNameAt(
//				mc.objectMouseOver.getBlockPos().getX(), mc.objectMouseOver.getBlockPos().getY(),
//                    mc.objectMouseOver.getBlockPos().getZ(),
					mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ,
				MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS) : MODULE_CONSTANTS.NO_BLOCK_IN_THIS_CONTEXT);
        // dag edit -> getBlockPos().get..()
		// Aussiemon reversed 1.8 -> 1.7.10
		setValue(
			"meta",
			mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK ? MAtmosUtility.getMetaAsStringAt(
//				mc.objectMouseOver.getBlockPos().getX(), mc.objectMouseOver.getBlockPos().getY(),
//                    mc.objectMouseOver.getBlockPos().getZ(),
					mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ,
				MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS) : MODULE_CONSTANTS.NO_BLOCK_IN_THIS_CONTEXT);
        // dag edit -> getBlockPos().get..()
		// Aussiemon reversed 1.8 -> 1.7.10
		setValue(
			"powermeta",
			mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK ? MAtmosUtility.getPowerMetaAt(
//				mc.objectMouseOver.getBlockPos().getX(), mc.objectMouseOver.getBlockPos().getY(),
//                    mc.objectMouseOver.getBlockPos().getZ(),
					mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ,
				MODULE_CONSTANTS.NO_BLOCK_OUT_OF_BOUNDS) : MODULE_CONSTANTS.NO_BLOCK_IN_THIS_CONTEXT);
        // dag edit -> getBlockPos().get..()
		// Aussiemon reversed 1.8 -> 1.7.10
		setValue(
				"entity_id",
				mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY ? EntityList
					.getEntityID(mc.objectMouseOver.entityHit) : MODULE_CONSTANTS.NO_ENTITY);
	}
}