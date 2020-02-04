package com.sujit.animationdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rlRoot, rlCircularWithUsers, rlLanguageSelection;
    Button btnNext;

    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlRoot = findViewById(R.id.rlRoot);
        rlCircularWithUsers = findViewById(R.id.rlCircularWithUsers);
        rlLanguageSelection = findViewById(R.id.rlLanguageSelection);
        btnNext = findViewById(R.id.btnShare);


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
                        rlCircularWithUsers.findViewById(R.id.ivProfile1).setVisibility(View.GONE);
                        rlCircularWithUsers.findViewById(R.id.btn1).setBackgroundColor(getResources().getColor(R.color.dark_red));
                        break;
                    case 3:
                        rlCircularWithUsers.findViewById(R.id.ivProfile2).setVisibility(View.GONE);
                        rlCircularWithUsers.findViewById(R.id.btn2).setBackgroundColor(getResources().getColor(R.color.dark_orange));
                        break;
                    case 4:
                        rlCircularWithUsers.findViewById(R.id.ivProfile3).setVisibility(View.GONE);
                        rlCircularWithUsers.findViewById(R.id.btn3).setBackgroundColor(getResources().getColor(R.color.dark_yellow));
                        break;
                    default:
                }
            }
        });
    }
}
