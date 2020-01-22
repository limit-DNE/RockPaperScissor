package limit.dne.rockpaperscissor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView background = (ImageView) findViewById(R.id.main_background_imageview);
        Glide.with(this).asGif().load(R.drawable.rockpaperscissor).error(R.drawable.alternative_background).into(background);
        Button start = (Button) findViewById(R.id.start_button_main);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("UserScore", 0);
                editor.putInt("ComputerScore", 0);
                editor.apply();
                Intent toUserChoice = new Intent(getApplicationContext(), UserChoice.class);
                startActivity(toUserChoice);
                finish();
            }
        });
    }
}
