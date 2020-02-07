package com.sujit.animationdemo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.Scene;
import androidx.transition.Slide;
import androidx.transition.TransitionManager;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {
    private static final String TAG = "MainActivity";
    public final int ANIM_LANGUAGE = 0;
    public final int ANIM_USERS = 1;
    public final int ANIM_PROFILE_1 = 2;
    public final int ANIM_PROFILE_2 = 3;
    public final int ANIM_PROFILE_3 = 4;
    public final int ANIM_WRITE_JAM = 5;
    Scene scene1;
    Scene scene2;
    Scene scene3;
    ConstraintLayout clRoot, clLanguageSelection;
    private int counter = 0;
    Animation moveProfile1Animation, moveProfile2Animation, moveProfile3Animation;
    RelativeLayout rootContainer;
    CircleImageView ivProfile1, ivProfile2, ivProfile3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = 0;
        clRoot = findViewById(R.id.clRoot);
        scene1 = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.rlRootContainer), R.layout.partial_layout_language_selection, this);
        scene2 = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.rlRootContainer), R.layout.partial_layout_multiple_users_onboarding, this);
        scene3 = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.rlRootContainer), R.layout.partial_layout_multiple_users_writing, this);

        moveProfile1Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile1);
        moveProfile2Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile2);
        moveProfile3Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile3);

        moveProfile1Animation.setAnimationListener(this);
        moveProfile2Animation.setAnimationListener(this);
        moveProfile3Animation.setAnimationListener(this);

        showLanguageSelectionView();

        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++counter;
                switch (counter) {
                    case ANIM_USERS:
                        TransitionManager.go(scene2, new Slide());
                        initProfileViews();
                        break;
                    case ANIM_PROFILE_1:
                        ivProfile1.startAnimation(moveProfile1Animation);
                        break;
                    case ANIM_PROFILE_2:
                        ivProfile2.startAnimation(moveProfile2Animation);
                        break;
                    case ANIM_PROFILE_3:
                        ivProfile3.startAnimation(moveProfile3Animation);
                        break;
                    case ANIM_WRITE_JAM:
                        TransitionManager.go(scene3, new Slide());
                        break;
                    default:
                }
            }
        });
    }

    public void showLanguageSelectionView() {
        ((RelativeLayout) scene1.getSceneRoot()).setGravity(Gravity.BOTTOM); // to set layout at bottom
        scene1.enter();
    }

    public void initProfileViews() {

        if (scene2 != null && scene2.getSceneRoot() instanceof RelativeLayout) {
            rootContainer = ((RelativeLayout) scene2.getSceneRoot());
            clLanguageSelection = rootContainer.findViewById(R.id.clLanguageSelection);
            ivProfile1 = clLanguageSelection.findViewById(R.id.ivProfile1);
            ivProfile2 = clLanguageSelection.findViewById(R.id.ivProfile2);
            ivProfile3 = clLanguageSelection.findViewById(R.id.ivProfile3);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == moveProfile1Animation) {
            ivProfile1.setVisibility(View.GONE);
            clLanguageSelection.findViewById(R.id.btn1).setBackground(getResources().getDrawable(R.drawable.rounded_button_dark_red));

        } else if (animation == moveProfile2Animation) {
            ivProfile2.setVisibility(View.GONE);
            clLanguageSelection.findViewById(R.id.btn2).setBackground(getResources().getDrawable(R.drawable.rounded_button_dark_orange));

        } else if (animation == moveProfile3Animation) {
            ivProfile3.setVisibility(View.GONE);
            clLanguageSelection.findViewById(R.id.btn3).setBackground(getResources().getDrawable(R.drawable.rounded_button_dark_yellow));
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
