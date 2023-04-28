package it.bff.biometricprompt.mainActivity;

public class AnimationInterpolator implements android.view.animation.Interpolator
{
    private double mAmplitude;
    private double mFrequency;

    public AnimationInterpolator(double amplitude, double frequency)
    {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    @Override
    public float getInterpolation(float time)
    {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) * Math.cos(mFrequency * time) + 1);
    }
}
