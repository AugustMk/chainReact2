package com.example.thechainreaction;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Button;

import java.util.HashMap;

public class Particles {

    private HashMap<Integer , Integer > colors ;
    private int sizeC  ;
    private Paint pntFill = new Paint();

   public Particles( int CellSize , HashMap<Integer , Integer > colors){
       this.colors = colors ;
       this.sizeC = CellSize;
   }
    public void setSizeC(int sizeC) {
        this.sizeC = sizeC;
    }



    private void drawBall(Canvas canvas  , int c , int r  , int info){
        int ballColor = info/10;         // get the circle color
        int ballNums =  info%10;         //checks how many cirlces the uswer has placed inside the cell

        //modify the plant properites
        pntFill.setAntiAlias(true);
        pntFill.setStyle(Paint.Style.FILL);
        pntFill.setStrokeWidth(4);
        pntFill.setColor((Integer) colors.get(ballColor));



        //draw the cirlcle with the color of the player
        if( ballNums  >= 1){
            canvas.drawCircle( (float) (sizeC*c + sizeC/2) , (float)(sizeC*r + sizeC/2)  , (float)( (2.7 /7.0 * sizeC ) / 2.0), pntFill );
        }

        if(ballNums >= 2){
            canvas.drawCircle((float) (sizeC*c + sizeC/2 + 20) , (float)(sizeC*r + sizeC/2 + 20)  , (float)( (2.7 /7.0 * sizeC ) / 2.0), pntFill );
        }
        if(ballNums ==3){
            canvas.drawCircle( (float) (sizeC*c + sizeC/2 - 20) , (float)(sizeC*r + sizeC/2 +5 )   , (float)( (2.7 /7.0 * sizeC ) / 2.0), pntFill );
        }
    }

    /*
     1 - > PLAYER 1 : COLOR RED
     2 - >  PLAYER 2 : COLOR WHITE
     3 -> PLAYER 3 : BLUE
     4 -> PLAYER 4 : SKY BLUE
     5 -> PLAYER 5 : YELLOW
     6 -> PLAYER 6 : LIGHT PURPLE
     7 -> PLAYER 7 : DARK PURPLE
     8 - > PLAYER 8 : PINK
     9 - > PLAYER 9 : GREEN
     */

    // method that drq
    public void placeBalls(Canvas canvas){
        for (int k = 0  ; k < 10 ; k++){
            for(int i = 0 ;  i < 6; i++){
                if(GameLogic.getGridArr()[k][i] != 0 && GameLogic.getGridArr()[k][i]/10 != 0){   // check whether the the cell is empty
                    drawBall(canvas , i, k ,GameLogic.getGridArr()[k][i]);
                }
            }
        }

    }






}
