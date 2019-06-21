package br.coelho.agenda.Animations;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import br.coelho.agenda.Adapters.DataItem;
import br.coelho.agenda.R;

public class ReminderExpander {

    private Boolean ShowShortDescription = false;

    private int ShortDescriptionHeight, FullDescriptionHeight;

    private TextView Description;

    private ImageView ExpandableIco;


    private void startExpandableAnim(int heightOne, int heightTwo) {

        ValueAnimator anim;

        anim = ValueAnimator.ofInt(heightOne, heightTwo);

        anim.setInterpolator(new DecelerateInterpolator());

        anim.setDuration(400);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Description.getLayoutParams().height = (int) animation.getAnimatedValue();

                Description.requestLayout();
            }
        });

        anim.start();


    }




    private void startSpinIco(final int fromDegree, final int toDegree) {
        RotateAnimation rotate = new RotateAnimation(fromDegree, toDegree, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(300);

        rotate.setFillAfter(true);
        rotate.setFillEnabled(true);

        rotate.setInterpolator(new LinearInterpolator());

        ExpandableIco.startAnimation(rotate);
    }


    private int getHeight(Context context, String text) {

        int deviceWidth;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Display display = wm.getDefaultDisplay();

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){

            Point size = new Point();

            display.getSize(size);

            deviceWidth = size.x;

        }
        else {
            deviceWidth = display.getWidth();
        }

        TextView textView = new TextView(context);

        textView.setPadding(0, 0,0, 0);

        textView.setTypeface(Typeface.DEFAULT);

        textView.setText(text, TextView.BufferType.SPANNABLE);

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);

        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        textView.measure(widthMeasureSpec, heightMeasureSpec);

        return textView.getMeasuredHeight();
    }


    public void setExpandable(final View reminderView, final DataItem item, Context ctx) {

        Description = reminderView.findViewById(R.id.Description);

        ExpandableIco = reminderView.findViewById(R.id.ExpandableIco);

        FullDescriptionHeight = getHeight(ctx, item.getDescrition());

        ShortDescriptionHeight = getHeight(ctx, item.getShortDescrition());


        (reminderView.findViewById(R.id.Reminder)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Fade().fadeBlink(Description, 400);

                if(ShowShortDescription) {

                    Description.setText(item.getShortDescrition());

                    ShowShortDescription = false;

                    startExpandableAnim(FullDescriptionHeight, ShortDescriptionHeight);

                    startSpinIco(180, 360);
                }
                else {

                    Description.setText(item.getDescrition());

                    ShowShortDescription = true;

                    startExpandableAnim(ShortDescriptionHeight, FullDescriptionHeight);

                    startSpinIco(0, 180);
                }


            }
        });





    }
}
