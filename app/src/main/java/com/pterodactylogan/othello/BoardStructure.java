package com.pterodactylogan.othello;

/**
 * Created by demouser on 1/17/17.
 */

public class BoardStructure {

    public int BoardSize = 8;

    OthelloCell [][] board = new OthelloCell[BoardSize][BoardSize];

    public enum OthelloCell {
        EMPTY,
        BLACK,
        WHITE
    }

    public BoardStructure(int size){
        for (int r = 0 ; r < BoardSize ; r++){
            for (int c = 0; c < BoardSize ; c++){
                board[r][c] = OthelloCell.EMPTY;
            }
        }
        int a = BoardSize/2 -1;
        int b = BoardSize/2;
        board[a][a] = OthelloCell.WHITE;
        board[a][b] = OthelloCell.BLACK;
        board[b][a] = OthelloCell.BLACK;
        board[b][b] = OthelloCell.WHITE;
    }

    //if tile is already filled, funciton does nothing
    //otherwise places tile--if player is true white, if false black
    public void placeTile(boolean color, int r, int c){
        if(board[r][c]!= OthelloCell.EMPTY) return;
        if(color) board[r][c] = OthelloCell.WHITE;
        else board[r][c] = OthelloCell.BLACK;
        flipTiles(color,r,c);
    }

    //true=white, false= black
    public void flipTiles(boolean color, int r, int c){
        //placed tile was white
        if(color){
            //above tile, make sure not on edge
            if(r>=1){
                int i=0;
                //go up until you reach a tile that is not black
                while(isBlack(r-i, c)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r-i, c)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //upper right tile
            if(r>=1 && c<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isBlack(r-i, c+i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r-i, c+i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //right side
            if(c<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isBlack(r, c+i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r, c+i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //lower right corner
            if(r<7 && c<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isBlack(r+i, c+i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r+i, c+i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //below
            if(r<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isBlack(r+i, c)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r+i, c)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //lower left
            if(r<7 && c>=1){
                int i=0;
                //go until you reach a tile that is not black
                while(isBlack(r+i, c-i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r+i, c-i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //left side
            if(c>=1){
                int i=0;
                //go until you reach a tile that is not black
                while(isBlack(r, c-i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r, c-i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //upper left
            if(r>=1 && c>=1){
                int i=0;
                //go until you reach a tile that is not black
                while(isBlack(r-i, c-i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r-i, c-i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

        }
        else{

            //above tile, make sure not on edge
            if(r>=1){
                int i=0;
                //go up until you reach a tile that is not black
                while(isWhite(r-i, c)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r-i, c)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //upper right tile
            if(r>=1 && c<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isWhite(r-i, c+i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r-i, c+i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //right side
            if(c<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isWhite(r, c+i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r, c+i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //lower right corner
            if(r<7 && c<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isWhite(r+i, c+i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r+i, c+i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //below
            if(r<7){
                int i=0;
                //go until you reach a tile that is not black
                while(isWhite(r+i, c)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r+i, c)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //lower left
            if(r<7 && c>=1){
                int i=0;
                //go until you reach a tile that is not black
                while(isWhite(r+i, c-i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r+i, c-i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //left side
            if(c>=1){
                int i=0;
                //go until you reach a tile that is not black
                while(isWhite(r, c-i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r, c-i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }

            //upper left
            if(r>=1 && c>=1){
                int i=0;
                //go until you reach a tile that is not black
                while(isWhite(r-i, c-i)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isBlack(r-i, c-i)){
                    for(int j=i; j>0; j--){
                        board[r][c]=OthelloCell.WHITE;
                    }
                }
            }
        }
    }

    public String toString(){ //ROW, COL
        String boardString="";
        for(int i=0;i<board.length;i++) {
            for (int j = 0; j < board.length; j++){
                boardString += board[i][j]+" | ";
            }
            boardString+="\n";
        }
        return boardString;
    }


    public boolean isEmpty(int r, int c) {
        if(r>7 || r<0 || c>7 || c<0) return false;
        return board[r][c] == OthelloCell.EMPTY;
    }

    public boolean isBlack (int r, int c) {
        if(r>7 || r<0 || c>7 || c<0) return false;
        return board[r][c] == OthelloCell.BLACK;
    }

    public boolean isWhite (int r, int c) {
        if(r>7 || r<0 || c>7 || c<0) return false;
        return board[r][c] == OthelloCell.WHITE;
    }



}
