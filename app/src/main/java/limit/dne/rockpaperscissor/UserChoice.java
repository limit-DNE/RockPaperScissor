package limit.dne.rockpaperscissor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class UserChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        ImageView background = (ImageView) findViewById(R.id.userchoice_background_imageview);
        Glide.with(this).asGif().load(R.drawable.rockpaperscissor).error(R.drawable.alternative_background).into(background);
        final NumberPicker numberPicker = (NumberPicker) findViewById(R.id.userchoice_numberpicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2);
        numberPicker.setDisplayedValues(new String[]{"ROCK", "PAPER", "SCISSOR"});
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        Button continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("UserChoice", numberPicker.getValue());
                editor.apply();
                Intent toGamePanel = new Intent(getApplicationContext(), GamePanel.class);
                startActivity(toGamePanel);
                finish();
            }
        });
    }
}
