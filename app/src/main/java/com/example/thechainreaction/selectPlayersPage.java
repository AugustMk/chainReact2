package com.example.thechainreaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class selectPlayersPage extends AppCompatActivity {

    public static int play ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players_page);

    }

   public void goPlayingPage1(View view){
        play = 2;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
    public void goPlayingPage2(View view){
        play = 3;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
    public void goPlayingPage4(View view){
        play = 4;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
    public void goPlayingPage5(View view){
        play = 5;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
    public void goPlayingPage6(View view){
        play = 6;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
    public void goPlayingPage7(View view){
        play = 7;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
    public void goPlayingPage8(View view){
        play = 8;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
    public void goPlayingPage9(View view){
        play = 9;
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);
    }
}