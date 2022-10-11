package com.example.thechainreaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grid  extends View {

    private final  int color  ; // array to contain all the player's colors
    private final Paint pnt = new Paint();    // paint brush for drawing the grid
    private int sizeC ;                       //  var for cell size
    GameLogic logic = new GameLogic();
    public static  int rounds = 0  ;          // var for how many orbs have been placed on the grid
    private int player ;                      // var for the current player
    private final Particles particles ;       // game logic class
    Boolean winner = false ;

    public HashMap<Integer , Integer > playerColors = new HashMap<Integer , Integer >();  // hp for the colors of the player's orbs


    public Grid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ats = context.getTheme().obtainStyledAttributes(attrs,R.styleable.Grid ,0 ,0); //gets the color from the xml files
        try{
            color = ats.getInteger(R.styleable.Grid_black , 0);
             playerColors.put(1 ,ats.getInteger(R.styleable.Grid_gridRed ,0) );
             playerColors.put(2 , ats.getInteger(R.styleable.Grid_gridWhite ,0));
            playerColors.put( 3 , ats.getInteger(R.styleable.Grid_gridBlue ,0) );
            playerColors.put(4 ,ats.getInteger(R.styleable.Grid_gridSblue,0));
            playerColors.put( 5 , ats.getInteger(R.styleable.Grid_gridYellow ,0));
            playerColors.put( 6 , ats.getInteger(R.styleable.Grid_gridPink,0));
            playerColors.put( 7 ,ats.getInteger(R.styleable.Grid_gridPurple ,0));
            playerColors.put( 8 , ats.getInteger(R.styleable.Grid_gridPurple2 ,0));
            playerColors.put(9 ,ats.getInteger(R.styleable.Grid_gridGreen,0));
        } finally{
            ats.recycle();   // recycling the typed array
        }
        particles = new Particles( sizeC , playerColors);
        player = GameLogic.player ;
        pnt.setColor(playerColors.get(player));  // setting the first color of the grid to represent the first player

    }

    @Override
    protected void onMeasure(int w , int h){
        super.onMeasure(w, h);
        int minL = Math.min(getMeasuredWidth(), getMeasuredHeight());       // get the minimum length of  between the user's  screen width & height
        setMeasuredDimension( (int) (minL * 0.60) , (int) (minL * 0.90) );  // set the dimensions of the  grid in ratio that makes sure that all the squares fit into a rectangles
        sizeC=  (int) (minL * 0.6 /6);                                      //  determine the sizes of the cell (squares)
        particles.setSizeC(sizeC);

    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e){

        if (e.getAction() == MotionEvent.ACTION_DOWN) {   // checks if the user clicked inside the grid
          if(!logic.isWin()){                         //checks if there's a win
              if ( logic.insertB((int) Math.ceil(e.getY() / sizeC) - 1, (int) Math.ceil(e.getX() / sizeC) - 1 , false) ) {  //  determine whih row % column was clicked and populate the corrosponding  array element
                 //logic.handleMultipleExplosions();
                  logic.eliminatePlayer();                       //eliminate player
                  logic.setPlayer();                    //change the player
                  player = GameLogic.player;
                  logic.isWin() ;
                  invalidate();                               //calls the onDraw method
                  rounds++ ;
                  pnt.setColor(playerColors.get(GameLogic.nextPlayer())); // change the color of the grid
                  // change the player
                  invalidate();                              //calls the onDraw method
              }
          }
            invalidate();                                   //calls the onDraw method
        }


        return true;
    }



    @Override
    protected void onDraw(Canvas canvas){
       if (rounds == 0 ) { pnt.setColor( playerColors.get(1));} else {pnt.setColor( playerColors.get(player));}
        drawGrid(canvas);
       particles.placeBalls(canvas);
//        if(logic.isWin()){
//            winnerDisplay(canvas);
//        }


    }

    private void drawGrid(Canvas canvas) {

        //modify the paint brush  properties
        pnt.setAntiAlias(true);
        pnt.setStyle(Paint.Style.STROKE);
        pnt.setStrokeWidth(3);


         // draw the columns of the grid
        //colums
        for (int c = 0; c < 7; c++) {
            canvas.drawLine(sizeC * c, 0, sizeC * c, canvas.getHeight(), pnt);
            canvas.drawLine( (float)(sizeC * c + sizeC * 0.1), 0, (float) (sizeC * c + sizeC * 0.1), canvas.getHeight(), pnt);

        }
        canvas.drawLine( (float)(sizeC * 6 - sizeC * 0.1), 0, (float) (sizeC * 6 - sizeC * 0.1), canvas.getHeight(), pnt);
        //draws the rows of the grid
        //rows
        for (int i = 0; i < 10; i++) {
            canvas.drawLine(0, sizeC * i, canvas.getWidth(), sizeC * i, pnt);
            canvas.drawLine(0, (float) (sizeC * i + sizeC * 0.1), canvas.getWidth(), (float) (sizeC * i+ sizeC * 0.1), pnt);
        }
        canvas.drawLine(0, (float) canvas.getHeight(), canvas.getWidth(), (float) canvas.getHeight(), pnt);
    }

    private void winnerDisplay(Canvas canvas){
        Paint pnt2 = new Paint();
        pnt2.setColor(color);
        pnt2.setAntiAlias(true);
        //pnt2.setStyle(Paint.Style.FILL);
        pnt.setTextSize(65);
       // canvas.drawRect((float )(sizeC*1.5) , (float)(sizeC*3.5) , (float)(sizeC*4.5) , (float) (sizeC*5.5) , pnt2);
        canvas.drawText("player "+ logic.getWinner() +" has won" , (float) (sizeC*1.65)  , (float)(sizeC*4.5) , pnt );
    }

    public void game(Button btn , TextView update  ){
        logic.setviews(btn , update );

    }
    //restarting the game
    public void restartGame() {
        player = GameLogic.player ;
        pnt.setColor( playerColors.get(1));
        rounds = 0 ;
        logic.restart();

    }


    /*
    to do : sept/19 to sept/20

       ---> enable the user to place 2 and 3  particles in a cell

         sept/22 to sept/23

       ---> enable animate cells with 2 and three particles

        sept/25  to  sept/28

       ---> handle explosions

        sept/29

        ---> handle win

        oct/1  to oct/7
         -->get user input for the number of players  and modify the code to work with the specified number of players
              (this is a little tricky , that's why it is at the end )
          ---> making the undo and restart button interactable
          --> adding extra optional things to make the app look pretty
          ---> adding the splash screen


         ()
             --->  code review(team meeting at hamilton) & commenting 





     */






}

