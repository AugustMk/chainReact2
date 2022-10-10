package com.example.thechainreaction;


import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Iterator;


public class GameLogic {
    public static int playing = selectPlayersPage.play ;   // var for  the number of players
    public static int[][]  gridArr = new int[9][6];              // arr for the grid
    private Button  restartGame ;
    public static ArrayList<Integer> currentPlayers =  new ArrayList<Integer>();  // lst for the players
    public static int  player = 1  ;                       // var for the current player


    public  GameLogic(){
        createGrid();
        player();
    }
       /*
      0 -> EMPTY CELL
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


    private void createGrid() {
        /**
         *  insert  zeros into the grid to represent an empty cell
         * @param none
         */

        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 6; i++) {
                gridArr[k][i] = 0;
            }

        }

    }

    private void  player(){
        /**
         *  insert the players into the current players list
         * @param none
         */
        for(int k = 1 ; k <= playing  ; k ++){
            currentPlayers.add(k) ;
        }

    }



    public void setPlayer() {

        /**
         *   change the player
         * @param none
         */
        boolean firstplayerturn = this.player == playing || currentPlayers.size()==1 || currentPlayers.indexOf(player) == currentPlayers.size();
        this.player = (firstplayerturn) ?  currentPlayers.get(0) : currentPlayers.get(player);
    }

    public static int nextPlayer(){
        /**
         *   checks who's playimg after the current player
         * @param none
         */
        boolean firstplayerturn = player == playing || currentPlayers.size()==1 || currentPlayers.indexOf(player) == currentPlayers.size();
        return (firstplayerturn) ?  currentPlayers.get(0) : currentPlayers.get(player);
    }
    // get the grid array
    public static int[][] getGridArr() {

        return gridArr;
    }

    public void setbtn(Button btn){
        this.restartGame = btn ;
    }
    public  boolean insertB(int r , int c ){

        /**
         * placing the  player's orb into the array
         *  first letter represent the color of the player , and the second letter  represent the number of players to place in cell
         * @param  (r)row and c(column)
         */
        if(gridArr[r][c] ==0){   //checks if the cell is empty
            gridArr[r][c] = player* 10 +1  ;
            return true ;}

        else if(gridArr[r][c]/ 10 != player){  // checks if the cell clicked contains the balls of the player
            return false ;
        }
        else if(isCorner(r , c)  && gridArr[r][c]%10 ==1) {     // checks if the  cell clicked is at the corner and cotains one orb
            handleCornerExplosion(r, c,player);
            return true ;

        }else if(isEdge(r , c ) && gridArr[r][c]%10 ==2  ) {   // checks if the  cell clicked is at the edge and cotains 2 orbs
            handleEdgeExplosion(r, c, player);
            return true;

        }else if (gridArr[r][c]%10 == 3){          //checks if the  cell contains 3 orbs ;
            handleExplosion(r , c , player);
            return true ;

        } else if (gridArr[r][c] >0  && gridArr[r][c]%10 < 3  ){   // increment the number of orbs in a cell ;
            gridArr[r][c] =gridArr[r][c] +1;
            return true;
        }

        return false;
    }


    private  boolean isEdge( int r , int c){
        /**
         * checks whether the cell clicked is at the edge
         * @param (r)row and c(column)
         **/
        return ((r==0 || r == 8) && c != 0 &&  c != 5)|| ( (c == 0 || c == 5) && r != 0  && r != 8);
    }

    private  boolean isCorner(int r , int c){
        /**
         * checks whether the cell clicked is at the corner
         * @param (r)row and c(column)
         **/
        return (r == 0 && (c == 0|| c == 5))|| ( r == 8 && ( c == 0 || c == 5));
    }
    private boolean isCentre(int r, int c ){
        return !(isEdge(r,c) || isCorner(r,c));
    }


    //handle explosions that are at the  edges
    private void handleEdgeExplosion(int r , int c, int p){
        /**
         *  handles the explosions which occur at the edges
         * @param (r)row and c(column)  and p (player)
         **/

        if( c== 0 || c == 5){
            explode( r+1 ,  c , p);
            explode( r-1 ,c , p );
            if (c == 0 ){explode(r , c+1 , p );}
            else  { explode(r, c -1, p ); }


        } else if (r == 0 || r == 8){
            explode( r ,  c-1 , p);
            explode( r ,c+1 , p );
            if (r == 0 ){explode(r+1 , c , p );}
            else  { explode(r-1, c , p ); }

        }
        gridArr[r][c] = 0 ;  // remove the orbs in the exploding cell

    }

    //
    private void handleCornerExplosion(int r , int c , int p){
        /**
         * handle the explosions that are happening at the corners
         * @param (r)row , c(column)  and  p (player)
         **/

        if( r== 0) {explode(r+1 , c , p );} else{explode(r-1 , c , p );}
        if (c==0){ explode(r, c+1 , p );} else {  explode(r, c-1 , p );}
        gridArr[r][c] = 0 ;   // remove the orbs in the exploding cell
    }

    //
    private void handleExplosion(int r , int c , int p){
        /**
         * handle explosions that are happenning anywhere except the corners or the edge
         * @param (r)row , c(column)  and  p (player)
         **/

        explode( r ,  c-1 , p);
        explode( r ,c+1 , p );
        explode(r+1 , c , p );
        explode(r-1 , c , p );
        gridArr[r][c] = 0 ;     // remove the orbs in the exploding cell
    }

    // move
    public void explode( int r , int c , int p) {
        /**
         *  change the color of the orbs  in a cell which an orb explode to and move the orb to the  cell
         * @param (r)row , c(column)  and  p (player)
         **/

        if (gridArr[r][c] / 10 != p) {
            gridArr[r][c] = p * 10 +  (gridArr[r][c] % 10);   // change the color of the orbs to that of the exploding orb
        }
        boolean canExplode = ((isCorner(r ,c ) && gridArr[r][c]% 10 != 1) ||  (isEdge(r ,c ) && gridArr[r][c]% 10 !=2 )|| (!(isCorner(r,c)) &&(!(isEdge(r,c))&&gridArr[r][c]% 10 !=3 )));

        if (canExplode){insertB(r ,c ) ; }

    }

    // restart the game
    public void restart(){
        /**
         *  clears the boards and reset the players
         * @param none
         *
         **/
        currentPlayers.clear();

        for (int k = 0 ; k < 9 ; k++){
            for(int i = 0 ;  i < 6; i++){
                gridArr[k][i] = 0;       //change every element in the 2d array to 0
            }
        }

        for(int k = 1 ; k <= playing  ; k ++){
            currentPlayers.add(k) ;
        }
        player = currentPlayers.get(0); ;          //reset the  player counter
        restartGame.setVisibility(View.GONE);

    }

    //checks if only one player is playing and declare the winner
//    public  boolean checkWin(){
//         return currentPlayers.size()==1 ;
//    }


    // checks if a player's orbs have been elimated and removes the player from playing
    public void eliminatePlayer( ) {
        /**
         *  removes  the players from the  grid  if elimated
         * @param (none
         **/
        if (Grid.rounds >= playing) {
            for (Iterator<Integer> iterator = currentPlayers.iterator(); iterator.hasNext(); ) {
                int value = iterator.next();
                if (!(checkPlayer(value))) {
                    iterator.remove();
                }
            }
        }
    }


    //checks if the player is still in the board
    private boolean checkPlayer(int j){
        /**
         * checks if the prbs of the player has been removed
         * @param j (player number)
         */
        for (int k = 0 ; k < 9 ; k++) {
            for (int i = 0; i < 6; i++) {
                if (gridArr[k][i] / 10 == j) {
                    return true;
                }
            }
        }

        return false ;
    }

    //checks if the player can play
    public static boolean canPlay(int p){
        /**
         * checks if the player can play
         * @param p (player)
         */
        return currentPlayers.contains(p);
    }

    public  boolean isWin(){
        /**
         * checks if there's a winner/ win
         * @param none
         */
        boolean winner = currentPlayers.size()== 1 ;
        if(winner){
            restartGame.setVisibility(View.VISIBLE);
        }
        return  winner;
    }

    public static int  getWinner(){
        /**
         * get the winner
         * @param none
         */
        return currentPlayers.get(0);

    }

//    public boolean isCellEmpty(int row, int column){
//        return gridArr[row][column]==0;
//    }
//    public  boolean isUnstableCell(int row, int column){
//        // return boolean: is this cell unstable
//        if (isCellEmpty(row,column)) return false;
//        else if (isCorner(row,column) && gridArr[row][column]%10 >1 ) return true;
//        else if (isEdge(row,column) && gridArr[row][column]%10 >2) return true;
//        else if (isCentre(row,column) && gridArr[row][column]%10 >3) return true;
//        return true;
//    }
//    public  boolean isUnstableGrid(){
//        // return boolean: are there any unstable cells
//        for (int row =0; row<10;row++){
//            for (int column = 0; column<6;column++){
//                if (isUnstableCell(row,column)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public void explodeAll(){
//        //explodes unstable cells until the grid is stable
//        boolean unstable = isUnstableGrid();
//        while (unstable){
//            for (int row =0; row<10;row++){
//                for (int column = 0; column<6;column++){
//                    if (isUnstableCell(row,column)){
//                        explode(row,column,player);
//                    }
//                }
//            }
//            unstable = isUnstableGrid();
//        }
//    }
}
