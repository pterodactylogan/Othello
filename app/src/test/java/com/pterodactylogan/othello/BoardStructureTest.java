package com.pterodactylogan.othello;

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
        assertTrue(myBoard.isBlack(3,3));
        assertFalse(myBoard.isBlack(3,4));
        assertFalse(myBoard.isBlack(0,0));
        assertTrue(myBoard.isBlack(4,4));
    }

    @Test
    public void testIsEmpty(){
        BoardStructure myBoard = new BoardStructure(8);
        assertTrue(myBoard.isEmpty(0,0));
        assertFalse(myBoard.isEmpty(3,3));

    }

    @Test
    public void testIsWhite(){
        BoardStructure myBoard = new BoardStructure(8);
        assertTrue(myBoard.isWhite(3,4));
        assertFalse(myBoard.isWhite(3,3));
        assertFalse(myBoard.isWhite(0,0));
        assertTrue(myBoard.isWhite(4,3));
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
        myBoard.placeTile(true,4,2);
        assertTrue(myBoard.isWhite(4,3));
        assertTrue(myBoard.isWhite(4,2));
        myBoard.placeTile(false,3,2);
        assertTrue(myBoard.isBlack(3,3));
        assertTrue(myBoard.isBlack(3,2));
    }







}
