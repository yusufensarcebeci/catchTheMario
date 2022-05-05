package com.yusufensarcebeci.catchthemario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yusufensarcebeci.catchthemario.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Runnable runnable;
    Handler handler;
    ImageView[] imageViews;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        score =0;

        imageViews =new ImageView[]{binding.imageView,binding.imageView2,binding.imageView3,binding.imageView4,binding.imageView5,binding.imageView6,binding.imageView7,binding.imageView8,binding.imageView9};

        moveImages();

        new CountDownTimer(20000,1000) {

            @Override
            public void onTick(long l) {
                binding.textViewTime.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() {
                binding.textViewTime.setText("Time Off!");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(getApplicationContext(), "Time Off!", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Game Over");
                alert.setMessage("Your Score: "+score + "\nTry Again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this,StartingScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                alert.show();
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mario_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.game_restart){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void increaseScore(View view){
        binding.textViewScore.setText("Score: "+ ++score);
    }

    public void moveImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random= new Random();
                int i = random.nextInt(9);
                imageViews[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,600);
            }
        };
        handler.post(runnable);
    }

    public void restartGame(){
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }


}