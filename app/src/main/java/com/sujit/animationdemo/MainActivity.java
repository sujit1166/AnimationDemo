package com.sujit.animationdemo;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    RelativeLayout rlRoot, rlCircularWithUsers, rlLanguageSelection;
    Button btnNext;

    int counter = 0;
    Animation moveProfle1Animation, moveProfle2Animation, moveProfle3Animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlRoot = findViewById(R.id.rlRoot);
        rlCircularWithUsers = findViewById(R.id.rlCircularWithUsers);
        rlLanguageSelection = findViewById(R.id.rlLanguageSelection);
        btnNext = findViewById(R.id.btnShare);

        moveProfle1Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile1);
        moveProfle2Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile2);
        moveProfle3Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile3);

        moveProfle1Animation.setAnimationListener(this);
        moveProfle2Animation.setAnimationListener(this);
        moveProfle3Animation.setAnimationListener(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++counter;
                switch (counter) {
                    case 1:
                        rlLanguageSelection.setVisibility(View.GONE);
                        rlCircularWithUsers.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        rlCircularWithUsers.findViewById(R.id.ivProfile1).startAnimation(moveProfle1Animation);
                        break;
                    case 3:
                        rlCircularWithUsers.findViewById(R.id.ivProfile2).startAnimation(moveProfle2Animation);
                        break;
                    case 4:
                        rlCircularWithUsers.findViewById(R.id.ivProfile3).setVisibility(View.GONE);
                        rlCircularWithUsers.findViewById(R.id.btn3).setBackgroundColor(getResources().getColor(R.color.dark_yellow));
//                        rlCircularWithUsers.findViewById(R.id.ivProfile3).startAnimation(moveProfle3Animation); // could not animate as per required
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == moveProfle1Animation) {
            rlCircularWithUsers.findViewById(R.id.ivProfile1).setVisibility(View.GONE);
            rlCircularWithUsers.findViewById(R.id.btn1).setBackgroundColor(getResources().getColor(R.color.dark_red));

        } else if (animation == moveProfle2Animation) {
            rlCircularWithUsers.findViewById(R.id.ivProfile2).setVisibility(View.GONE);
            rlCircularWithUsers.findViewById(R.id.btn2).setBackgroundColor(getResources().getColor(R.color.dark_orange));

        } else if (animation == moveProfle3Animation) {
//            rlCircularWithUsers.findViewById(R.id.btn3).setBackgroundColor(getResources().getColor(R.color.dark_yellow));
//            rlCircularWithUsers.findViewById(R.id.ivProfile3).setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
