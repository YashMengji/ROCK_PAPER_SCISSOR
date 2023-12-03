package com.example.game;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class SettingsActivity extends AppCompatActivity {
    Button rock, paper, scissor;
    TextView result;

    ImageView nRockImage, nPaperImage, nScissorImage, nRock1Image, nPaper1Image, nScissor1Image;
    LottieAnimationView compThinkAnimation,rockImage, sciImage, paperImage, rock1Image, sci1Image, paper1Image,backgroundAnimation;

    int myScore = 0, compScore = 0, randNum = 0;
    int topLevel = 3;
    String playerName, resultName=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);

        result = findViewById(R.id.result);

        rockImage = findViewById(R.id.rockAnimation);
        sciImage = findViewById(R.id.scissorAnimation);
        paperImage = findViewById(R.id.paperAnimation);

        rock1Image = findViewById(R.id.rockAnimation1);
        sci1Image = findViewById(R.id.scissorAnimation1);
        paper1Image = findViewById(R.id.paperAnimation1);

        nRockImage = findViewById(R.id.nRockImage);
        nPaperImage = findViewById(R.id.nPaperImage);
        nScissorImage = findViewById(R.id.nScissorImage);

        nRock1Image = findViewById(R.id.nRock1Image);
        nPaper1Image = findViewById(R.id.nPaper1Image);
        nScissor1Image = findViewById(R.id.nScissor1Image);

        compThinkAnimation = findViewById(R.id.compThinkAnimation);
        compThinkAnimation.playAnimation();

        showScore();



        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeCompThink();
                playAnimation(rockImage,nRockImage);
                //rockImage.bringToFront();
                randNum = generateRandNo();
                showComChoice(randNum);
                evaluateResult(randNum, 1);
                showScore();
                delayPlayAnimation();
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeCompThink();
                playPaperAnimation(paperImage,nPaperImage);
                randNum = generateRandNo();
                showComChoice(randNum);
                evaluateResult(randNum, 2);
                showScore();
                delayPlayAnimation();
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeCompThink();
                playAnimation(sciImage, nScissorImage);
                //sciImage.bringToFront();
                randNum = generateRandNo();
                showComChoice(randNum);
                evaluateResult(randNum, 3);
                showScore();
                delayPlayAnimation();
            }
        });
    }

    public void showResultDialog(int myScore, int compScore, int topLevel1) {

            Dialog resultDialog = new Dialog(this);
            resultDialog.setContentView(R.layout.activity_result); // Replace with the layout you want for the dialog
            resultDialog.setCancelable(false);
            // Initialize views in the dialog

            Intent i = getIntent();
            playerName = i.getStringExtra("playerName");

        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("name.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder data = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            resultName = data.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

            TextView myScoreText = resultDialog.findViewById(R.id.myScoreText);
            TextView compScoreText = resultDialog.findViewById(R.id.compScoreText);
            TextView resultText = resultDialog.findViewById(R.id.resultText);
            //LottieAnimationView winAnimation = resultDialog.findViewById(R.id.winAnimation);
            LottieAnimationView win1Animation = resultDialog.findViewById(R.id.win1Animation);

            myScoreText.setText("Your Score: " + myScore);
            compScoreText.setText("Comp Score: " + compScore);

            if (myScore == topLevel1) {
                resultText.setText("YOU WIN!!");
                win1Animation.setAlpha(1.0f);
                win1Animation.playAnimation();

            } else {

                resultText.setText("YOU LOSE!!");

            }
            Intent k = new Intent(this, MainActivity.class);
            Button homeButton = resultDialog.findViewById(R.id.homeButton);
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(k);
                    resultDialog.dismiss();
                }
            });
            resultDialog.show();

    }
    public void playPaperAnimation(final LottieAnimationView animationView, ImageView choiceImg) {
        animationView.setAlpha(1.0f);
        animationView.playAnimation();
        animationView.bringToFront();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                choiceImg.setAlpha(1.0f);
                choiceImg.bringToFront();
            }
        }, 1950);
    }

    public void delayPlayAnimation(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast("Play your move!");
                compThinkAnimation.playAnimation();
                compThinkAnimation.bringToFront();
                hideChoice();
                compThinkAnimation.setVisibility(View.VISIBLE);
            }
        }, 4000);

    }
    public void closeCompThink(){
        compThinkAnimation.cancelAnimation();
        compThinkAnimation.setVisibility(View.INVISIBLE);
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 2700);
    }

    public void hideChoice(){
        rockImage.setAlpha(0.0f);
        sciImage.setAlpha(0.0f);
        paperImage.setAlpha(0.0f);

        rock1Image.setVisibility(View.INVISIBLE);
        sci1Image.setVisibility(View.INVISIBLE);
        paper1Image.setVisibility(View.INVISIBLE);

        nRockImage.setAlpha(0.0f);
        nPaperImage.setAlpha(0.0f);
        nScissorImage.setAlpha(0.0f);

        nRock1Image.setAlpha(0.0f);
        nPaper1Image.setAlpha(0.0f);
        nScissor1Image.setAlpha(0.0f);
    }
    public void showComChoice(int randNum) {

        playComputerAnimation(randNum);

        if (randNum == 1) {
            rock1Image.setVisibility(View.VISIBLE);
        } else if (randNum == 2) {
            paper1Image.setVisibility(View.VISIBLE);
        } else {
            sci1Image.setVisibility(View.VISIBLE);
        }
    }

    public void playComputerAnimation(int randNum) {
        switch (randNum) {
            case 1:
                play1Animation(rock1Image, nRock1Image);
                break;
            case 2:
                playPaperAnimation(paper1Image, nPaper1Image);
                break;
            case 3:
                play1Animation(sci1Image, nScissor1Image);
                break;
            default:
                break;
        }
    }

    public void evaluateResult(int compChoice, int userChoice) {
        if (compChoice == userChoice) {
            showToast("Match is a draw");
        } else if ((compChoice == 1 && userChoice == 3) || (compChoice == 2 && userChoice == 1) || (compChoice == 3 && userChoice == 2)) {
            showToast("You lose the match!");
            compScore++;
        } else {
            showToast("You win the match!");
            myScore++;
        }
    }

    public int generateRandNo() {
        Random rand = new Random();
        return rand.nextInt(3) + 1;
    }

    public void showScore() {
        Intent i = getIntent();
        if (i != null && i.hasExtra("level1Value")) {
            topLevel = Integer.parseInt(i.getStringExtra("levelValue"));
        } else {
            // Handle the case where "levelValue" is not provided in the Intent
            topLevel = 3; // Set a default value or handle it differently based on your requirements.
        }

        result.setText("YOUR SCORE: " + myScore + "  COMP SCORE: " + compScore);
        if(myScore==topLevel || compScore==topLevel){
        showResultDialog(myScore,compScore,topLevel);
        }
    }

    public void playAnimation(final LottieAnimationView animationView, ImageView choiceImg) {
        animationView.setAlpha(1.0f);
        animationView.playAnimation();
        animationView.bringToFront();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                choiceImg.setAlpha(1.0f);
                choiceImg.bringToFront();
            }
        }, 1550);
    }

    public void play1Animation(final LottieAnimationView animationView, ImageView choiceImg) {
        animationView.setAlpha(1.0f);
        animationView.playAnimation();
        animationView.bringToFront();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                choiceImg.setAlpha(1.0f);
                choiceImg.bringToFront();
            }
        }, 1550);
    }
}
