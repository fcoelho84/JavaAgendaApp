package br.coelho.agenda.Animations;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

public class Fade {

    public void fadeIn(ViewGroup view, int delay) {
        Animation fade = new AlphaAnimation(0, 1);

        fade.setInterpolator(new DecelerateInterpolator());

        fade.setDuration(delay);

        view.setVisibility(View.VISIBLE);

        view.startAnimation(fade);


    }

    public void fadeOut(ViewGroup view, int delay) {
        Animation fade = new AlphaAnimation(1, 0);

        fade.setInterpolator(new AccelerateInterpolator());

        fade.setDuration(delay);

        view.startAnimation(fade);
    }
}
