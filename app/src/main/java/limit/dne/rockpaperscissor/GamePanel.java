package limit.dne.rockpaperscissor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GamePanel extends AppCompatActivity {

    private ImageView userChoiceImage;
    private ImageView computerChoiceImage;
    private int userChoice;
    private String computerChoice;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_panel);
        userChoiceImage = (ImageView) findViewById(R.id.userchoice_imageview);
        computerChoiceImage = (ImageView) findViewById(R.id.computerchoice_imageview);
        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userChoice = sharedPreferences.getInt("UserChoice", 0);
        computerChoice = getComputerChoice();
        setUserChoiceImage();
        setComputerChoiceImage();
        setScore();
        int userScore = sharedPreferences.getInt("UserScore", 0);
        int computerScore = sharedPreferences.getInt("ComputerScore", 0);
        TextView scoreText = (TextView) findViewById(R.id.score_textview);
        scoreText.setText(getString(R.string.scoreboard, userScore, computerScore));
        Button replay = (Button) findViewById(R.id.button_replay);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replayIntent = new Intent(getApplicationContext(), UserChoice.class);
                startActivity(replayIntent);
                finish();
            }
        });
        Button exit = (Button) findViewById(R.id.button_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                System.exit(1);
            }
        });
    }

    private String getComputerChoice(){
        int randomChoice = new Random().nextInt(3);
        switch (randomChoice){
            case 0:
                return "ROCK";
            case 1:
                return "PAPER";
            case 2:
                return "SCISSOR";
            default:
                return "ROCK";
        }
    }

    private void setUserChoiceImage(){
        if (userChoice == 0){
            userChoiceImage.setImageResource(R.drawable.rock);
        } else if (userChoice == 1){
            userChoiceImage.setImageResource(R.drawable.paper);
        } else if (userChoice == 2){
            userChoiceImage.setImageResource(R.drawable.scissor);
        }
    }

    private void setComputerChoiceImage(){
        if (computerChoice.equals("ROCK")){
            computerChoiceImage.setImageResource(R.drawable.rock);
        } else if (computerChoice.equals("PAPER")){
            computerChoiceImage.setImageResource(R.drawable.paper);
        } else if (computerChoice.equals("SCISSOR")){
            computerChoiceImage.setImageResource(R.drawable.scissor);
        }
    }

    private void setScore() {
        if (userChoice == 0 && computerChoice.equals("PAPER")){
            editor.putInt("ComputerScore", sharedPreferences.getInt("ComputerScore", 0) + 1);
            editor.apply();
        } else if (userChoice == 0 && computerChoice.equals("SCISSOR")){
            editor.putInt("UserScore", sharedPreferences.getInt("UserScore", 0) + 1);
            editor.apply();
        } else if (userChoice == 1 && computerChoice.equals("ROCK")){
            editor.putInt("UserScore", sharedPreferences.getInt("UserScore", 0) + 1);
            editor.apply();
        } else if (userChoice == 1 && computerChoice.equals("SCISSOR")){
            editor.putInt("ComputerScore", sharedPreferences.getInt("ComputerScore", 0) + 1);
            editor.apply();
        } else if (userChoice == 2 && computerChoice.equals("ROCK")){
            editor.putInt("ComputerScore", sharedPreferences.getInt("ComputerScore", 0) + 1);
            editor.apply();
        } else if (userChoice == 2 && computerChoice.equals("PAPER")){
            editor.putInt("UserScore", sharedPreferences.getInt("UserScore", 0) + 1);
            editor.apply();
        }
    }
}
