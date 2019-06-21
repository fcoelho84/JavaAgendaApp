package br.coelho.agenda.Animations;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;

import br.coelho.agenda.Root;


public class CircularReveal {

    public interface Listener {
        void onAnimationEnd();
        void onAnimationStart();
    }


    ViewGroup layoutRevealForAnim, layoutContent;

    View RootView;

    Context Ctx;

    Listener listener;

    int ScreenWidth, ScreenHeigth, ScreenSize, StartRadius, EndRadius;


    public CircularReveal(View view, ViewGroup layoutReveal, ViewGroup content, Context context) {

        this.layoutRevealForAnim = layoutReveal;
        this.layoutContent = content;
        this.RootView = view;

        this.Ctx = context;

        saveScreenSize();


    }

    public void setAnimationListener(Listener listener) {
        this.listener = listener;
    }

    public void open() {

        StartRadius = 0;

        EndRadius = ScreenSize;

        startAnim();
    }

    public void close() {

        StartRadius = ScreenSize;

        EndRadius = 0;

        new Fade().fadeOut(layoutContent, 600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startAnim();
            }
        }, 500);


    }

    private void startAnim() {

        layoutContent.setVisibility(View.GONE);

        onLayoutChangedListener();
    }

    private void saveScreenSize() {
        WindowManager wm = (WindowManager) Ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();

        display.getSize(size);

        ScreenWidth = size.x;

        ScreenHeigth = size.y;

        ScreenSize = (int) Math.hypot(ScreenWidth, ScreenHeigth);


    }

    private void onLayoutChangedListener() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);

                    startCircularRevealAnim();
                }
            });
        }

    }

    private void startCircularRevealAnim() {


        int value = (int)(ScreenWidth * 11.35) / 100;


        Animator anim = ViewAnimationUtils
                .createCircularReveal(layoutRevealForAnim, ScreenWidth - value, 0 , StartRadius, EndRadius);

        anim.setDuration(800);

        animationListener(anim);

        anim.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Root.FabAction.hide(true);
            }
        }, 550);

    }

    private void animationListener(Animator anim) {

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                listener.onAnimationStart();

                if(EndRadius != 0) {

                    Root.FabAction.hide(true);

                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                onAnimatioEnd(EndRadius == 0);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void onAnimatioEnd(Boolean opening) {

        new Fade().fadeIn(layoutContent, 600);

        if(opening) {

            layoutRevealForAnim.setVisibility(View.GONE);

            listener.onAnimationEnd();

        }else {

            if(Root.ToolbarIsOpen) {
                Root.FabAction.show(true);
            }

            listener.onAnimationEnd();
        }

    }

}
