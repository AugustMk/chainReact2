package com.example.thechainreaction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class playingPage extends AppCompatActivity {
    private Grid grid ;
    private String players ;
    private TextView winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playingpage);
        ConstraintLayout constraintLayout = findViewById(R.id.playPage);

        AnimationDrawable animationDrawable =(AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        //players = findViewById(R.id.numPlayers).toString();
        grid = findViewById(R.id.Grid);
        Button btn = findViewById(R.id.btnResta);
        btn.setVisibility(View.GONE);
        grid.game(btn);

        //winner = findViewById(R.id.showWinner);





        //grid.setPlayers(Integer.parseInt(players));
    }


    public void restartButton(View view){
        grid.restartGame();
        grid.invalidate();

    }

    public void startGameClick(View view){
        Intent intent = new Intent(this,playingPage.class);
        startActivity(intent);

    }

    public void win() {

            AlertDialog.Builder b = new AlertDialog.Builder(playingPage.this);
            b.setMessage("player has won");
            b.setTitle("game over!!");
            b.setCancelable(false);
    }

//    public void displayWinner(){
//        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        View winnerDisplay = li.inflate(R.layout.activity_dsiplay_winner , null);
//
//        PopupWindow pW = new PopupWindow( winnerDisplay , 900 , 500 , true);
//        pW.showAtLocation((View) R.id.playPage , Gravity.BOTTOM , 0 , 0);
//
//    }
}