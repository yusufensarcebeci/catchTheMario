package com.yusufensarcebeci.catchthemario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yusufensarcebeci.catchthemario.databinding.ActivityStartingScreenBinding;

public class StartingScreen extends AppCompatActivity {

    private ActivityStartingScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartingScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void startGame(View view){
        Intent intent = new Intent(StartingScreen.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void exitGame(View view){
        finish();
        System.exit(0);
    }
}