package eu.ha3.matmos.game.system;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import eu.ha3.matmos.engine.core.implem.HelperFadeCalculator;

import eu.ha3.matmos.engine.core.implem.SystemClock;
import net.minecraft.client.audio.SoundCategory;

/*
--filenotes-placeholder
*/

public class NoAttenuationMovingSound extends MovingSound implements StreamingSound
{
	private HelperFadeCalculator fade;
	
	private boolean isLooping;
	private boolean usesPause;
	
	private final HelperFadeCalculator helper = new HelperFadeCalculator(new SystemClock());
	private float desiredVolume;
	private float desiredPitch;
	
	protected NoAttenuationMovingSound(
		ResourceLocation p_i45104_1_, HelperFadeCalculator fade, boolean isLooping, boolean usesPause)
	{
		super(p_i45104_1_);
		this.attenuationType = ISound.AttenuationType.NONE;
		
		this.isLooping = isLooping;
		this.repeat = isLooping;
		
		this.usesPause = usesPause;
	}
	
	protected NoAttenuationMovingSound(
			ResourceLocation p_i45104_1_, float volume, float pitch, boolean isLooping, boolean usesPause)
		{
			super(p_i45104_1_);
			this.attenuationType = ISound.AttenuationType.NONE;

			this.isLooping = isLooping;
			this.repeat = isLooping;
			
			this.pitch = pitch;
			this.volume = volume;
			
			this.usesPause = usesPause;
		}
	
	
	
	public NoAttenuationMovingSound copy()
	{
		return new NoAttenuationMovingSound(this.getSoundLocation(), desiredVolume, desiredPitch, repeat, usesPause);
	}
	
	@Override
	public void update()
	{
		Entity e = Minecraft.getMinecraft().thePlayer;
		
		this.xPosF = (float) e.posX;
		this.yPosF = (float) e.posY;
		this.zPosF = (float) e.posZ;

		this.volume = helper.calculateFadeFactor() * desiredVolume;

		if (volume < 0.01f && usesPause)
		{
			pitch = 0f;
		}
		if (volume > 0.01f && usesPause)
        {
            pitch = desiredPitch;
        }
        if (helper.isDoneFadingOut() && this.repeat && !this.isDonePlaying())
        {
            dispose();
        }
	}

	@Override
	public void play(float fadeIn)
	{
		this.helper.fadeIn((long) (fadeIn * 1000));
	}

	@Override
	public void stop(float fadeOut)
	{
		this.helper.fadeOut((long) (fadeOut * 1000));
	}

	@Override
	public void applyVolume(float volumeMod)
	{
        this.volume = volumeMod;
	}

	@Override
	public void dispose()
	{
		this.donePlaying = true;
	}

	@Override
	public void interrupt()
	{
		this.donePlaying = true;
	}
}
