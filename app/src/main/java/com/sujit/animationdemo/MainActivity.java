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
    public final int ANIM_LANGUAGE_SELECTION = 0;
    public final int ANIM_USERS = 1;
    public final int ANIM_PROFILE_1 = 2;
    public final int ANIM_PROFILE_2 = 3;
    public final int ANIM_PROFILE_3 = 4;
    public final int ANIM_WRITE_JAM = 5;
    Scene languageSelectionScene;
    Scene userOnBoardingScene;
    Scene userWritingScene;
    ConstraintLayout clLanguageSelection,clUserWriting;
    private int counter;
    Animation moveProfile1Animation, moveProfile2Animation, moveProfile3Animation;
    Animation jamInitAnimation, jam1Animation, jam2Animation,jam3Animation,numOfLeftJamAnimation;
    RelativeLayout rootContainer;
    CircleImageView ivProfile1, ivProfile2, ivProfile3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = ANIM_LANGUAGE_SELECTION;
        languageSelectionScene = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.rlRootContainer), R.layout.partial_layout_language_selection, this);
        userOnBoardingScene = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.rlRootContainer), R.layout.partial_layout_multiple_users_onboarding, this);
        userWritingScene = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.rlRootContainer), R.layout.partial_layout_multiple_users_writing, this);

        initAnimations();
        showLanguageSelectionView();

        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == ANIM_WRITE_JAM) {
                    return;
                }
                ++counter;
                switch (counter) {
                    case ANIM_USERS:
                        TransitionManager.go(userOnBoardingScene, new Slide());
                        initUserOnBoardingViews();
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
                        TransitionManager.go(userWritingScene, new Slide());
                        initUsersWritingViews();
                        jamInitAnimation.reset();
                        clUserWriting.findViewById(R.id.tvMemmories).clearAnimation();
                        clUserWriting.findViewById(R.id.tvMemmories).startAnimation(jamInitAnimation);
                        clUserWriting.findViewById(R.id.ivJamProfile1).startAnimation(jamInitAnimation);
                        break;
                    default:
                }
            }
        });
    }


    public void initAnimations(){
        moveProfile1Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile1);
        moveProfile2Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile2);
        moveProfile3Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_profile3);

        jamInitAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        jam1Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        jam2Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        jam3Animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        numOfLeftJamAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);

        moveProfile1Animation.setAnimationListener(this);
        moveProfile2Animation.setAnimationListener(this);
        moveProfile3Animation.setAnimationListener(this);

        jamInitAnimation.setAnimationListener(this);
        jam1Animation.setAnimationListener(this);
        jam2Animation.setAnimationListener(this);
        jam3Animation.setAnimationListener(this);
        numOfLeftJamAnimation.setAnimationListener(this);
    }

    public void showLanguageSelectionView() {
        ((RelativeLayout) languageSelectionScene.getSceneRoot()).setGravity(Gravity.BOTTOM); // to set layout at bottom
        languageSelectionScene.enter();
    }

    public void initUserOnBoardingViews() {

        if (userOnBoardingScene != null && userOnBoardingScene.getSceneRoot() instanceof RelativeLayout) {
            rootContainer = ((RelativeLayout) userOnBoardingScene.getSceneRoot());
            clLanguageSelection = rootContainer.findViewById(R.id.clLanguageSelection);
            ivProfile1 = clLanguageSelection.findViewById(R.id.ivProfile1);
            ivProfile2 = clLanguageSelection.findViewById(R.id.ivProfile2);
            ivProfile3 = clLanguageSelection.findViewById(R.id.ivProfile3);
        }
    }
    public void initUsersWritingViews() {

        if (userWritingScene != null && userWritingScene.getSceneRoot() instanceof RelativeLayout) {
            rootContainer = ((RelativeLayout) userWritingScene.getSceneRoot());
            clUserWriting = rootContainer.findViewById(R.id.clUserWriting);
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
        }else if (animation == jamInitAnimation) {
            clUserWriting.findViewById(R.id.tvJam1).startAnimation(jam1Animation);
        }else if (animation == jam1Animation) {
            clUserWriting.findViewById(R.id.tvJam2).startAnimation(jam2Animation);
            clUserWriting.findViewById(R.id.ivJamProfile2).startAnimation(jam2Animation);
        }else if (animation == jam2Animation) {
            clUserWriting.findViewById(R.id.tvNewJam).startAnimation(jam3Animation);
            clUserWriting.findViewById(R.id.ivJamProfile3).startAnimation(jam3Animation);
        }else if (animation == jam3Animation) {
            clUserWriting.findViewById(R.id.tvNumberOfLeftJam).startAnimation(numOfLeftJamAnimation);
        }else if (animation == numOfLeftJamAnimation) {
//            counter=0;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
