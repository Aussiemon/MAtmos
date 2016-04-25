package eu.ha3.matmos.game.system;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

/*
--filenotes-placeholder
*/

public class AttenuationSound extends PositionedSoundRecord
{
	private boolean isLooping = true;
	
	public AttenuationSound(ResourceLocation loc, float volume, float pitch, boolean isLooping, int repeatDelay, ISound.AttenuationType attenuationType, float x, float y, float z)
	{
		super(loc, volume, pitch, x, y, z);
		this.attenuationType = attenuationType;
		this.isLooping = isLooping;
		this.repeat = isLooping;
		this.repeatDelay = repeatDelay;
	}
}
