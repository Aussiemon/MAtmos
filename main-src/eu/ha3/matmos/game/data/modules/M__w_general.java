package eu.ha3.matmos.game.data.modules;

import eu.ha3.matmos.engine.core.interfaces.Data;
import eu.ha3.matmos.game.data.abstractions.module.Module;
import eu.ha3.matmos.game.data.abstractions.module.ModuleProcessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

/*
--filenotes-placeholder
*/

public class M__w_general extends ModuleProcessor implements Module
{
	public M__w_general(Data data)
	{
		super(data, "w_general");
	}
	
	@Override
	protected void doProcess()
	{
		World w = Minecraft.getMinecraft().theWorld;
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		WorldInfo info = w.getWorldInfo();

		setValue("time_modulo24k", (int) (info.getWorldTime() % 24000L));
		setValue("rain", w.isRaining());
		//setValue("thunder", info.isThundering());
        // dag edit getWeightedThunderStrength(..) - > getThunderStrength(..)
		// Aussiemon reversed 1.8 -> 1.7.10
		setValue("thunder", w.getWeightedThunderStrength(0f) > 0.9f);
        setValue("dimension", player.dimension);
        // dag edit .skylightSubtracted -> getSkylightSubtracted()
        // Aussiemon reversed 1.8 -> 1.7.10
		setValue("light_subtracted", w.skylightSubtracted);
		setValue("remote", w.isRemote);
		setValue("moon_phase", w.getMoonPhase());
		// Aussiemon added and refactored for 1.8 -> 1.7.10
        setValue("can_rain_on", w.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ));

        // dag edit + ..canSpawnLightningBolt()
        // If snowy biome is False. If biome disables rain is False
        // Aussiemon reversed 1.8 -> 1.7.10
//        setValue("biome_can_rain", w.getBiomeGenForCoords(player.getPosition()).canSpawnLightningBolt());
        setValue("biome_can_rain", w.getBiomeGenForCoords((int) player.posX, (int) player.posZ).canSpawnLightningBolt());
		
		setValue("rain_force1k", Math.round(w.getRainStrength(0f) * 1000));
		// Aussiemon reversed 1.8 -> 1.7.10
		setValue("thunder_force1k", Math.round(w.getWeightedThunderStrength(0f) * 1000));
		
	}
}