package br.coelho.agenda.Animations;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class Fade {


    public void fadeIn(final ViewGroup view, int delay) {

        view.setVisibility(View.VISIBLE);

        view.startAnimation(fade(delay, 0, 1));

    }

    public void fadeOut(final ViewGroup view, int delay) {
        view.setVisibility(View.VISIBLE);

        Animation anim = fade(delay, 1, 0);

        view.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private Animation fade(int delay, int a1, int a2) {

        Animation fade = new AlphaAnimation(a1, a2);

        fade.setInterpolator(new DecelerateInterpolator());

        fade.setDuration(delay);

        return fade;

    };

    public void fadeBlink(final TextView view, final int delay) {

        Animation anim = fade(delay, 1, 0);

        view.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                view.setAnimation(fade(delay, 0, 1));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }



}
