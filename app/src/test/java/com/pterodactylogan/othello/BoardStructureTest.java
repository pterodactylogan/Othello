package com.pterodactylogan.othello;

import android.util.Log;


import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by demouser on 1/17/17.
 */

public class BoardStructureTest {
    @Test
    public void testIsBlack(){
        BoardStructure myBoard = new BoardStructure(8);
        assertFalse(myBoard.isBlack(3,3));
        assertTrue(myBoard.isBlack(3,4));
        assertFalse(myBoard.isBlack(0,0));
        assertFalse(myBoard.isBlack(4,4));
        assertFalse(myBoard.isBlack(20,0));
        assertFalse(myBoard.isBlack(-1, 6));
    }

    @Test
    public void testIsEmpty(){
        BoardStructure myBoard = new BoardStructure(8);
        assertTrue(myBoard.isEmpty(0,0));
        assertFalse(myBoard.isEmpty(3,3));
        assertFalse(myBoard.isEmpty(8, 0));
    }

    @Test
    public void testIsWhite(){
        BoardStructure myBoard = new BoardStructure(8);
        assertFalse(myBoard.isWhite(3,4));
        assertTrue(myBoard.isWhite(3,3));
        assertFalse(myBoard.isWhite(0,0));
        assertFalse(myBoard.isWhite(4,3));
    }

    @Test
    public void testPlaceTile(){
        BoardStructure myBoard = new BoardStructure(8);
        myBoard.placeTile(true,4,2);
        assertTrue(myBoard.isWhite(4,2));
    }

    @Test
    public void testFlipTiles(){ //true is white, false is black
        BoardStructure myBoard = new BoardStructure(8);
        myBoard.placeTile(true,5,3);
        //System.out.println(myBoard.nicerToString());
        assertTrue(myBoard.isWhite(4,3));
        myBoard.placeTile(false,3,2);
        //System.out.println(myBoard.nicerToString());
        assertTrue(myBoard.isBlack(3,3));
        assertTrue(myBoard.isBlack(3,2));
    }


    @Test
    public void testGetGoodMove(){
        BoardStructure myBoard = new BoardStructure(8);
        for(int i=0; i<10; i++){
            boolean turn;
            if(i%2==0) turn = true;
            else turn =false;
            int[] move = myBoard.getGoodMove(turn); //move for black
            myBoard.placeTile(turn, move[0], move[1]);
            System.out.println(myBoard.nicerToString());
        }

    }

    @Test
    public void testIsStuck(){
        BoardStructure myBoard = new BoardStructure(8);
        myBoard.placeTile(false,3, 2);
        myBoard.placeTile(false, 4, 5);
        boolean stuck = myBoard.isStuck(true);
        assertTrue(stuck);
        myBoard.placeTile(true,0,0);
        stuck = myBoard.isStuck(true);
        assertTrue(stuck);
        myBoard.placeTile(true, 2,3);
        stuck=myBoard.isStuck(true);
        assertTrue(stuck);
    }


}
