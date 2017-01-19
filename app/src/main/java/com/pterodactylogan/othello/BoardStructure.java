package com.pterodactylogan.othello;

import java.util.ArrayList;

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

    //white=true, black=false
    public int[] getGoodMove(boolean color){
        int[] move = {-1,-1};
        int maxFlips = 0;
        //loop through each square in the grid
        for(int i=0; i<BoardSize; i++){
            for(int j =0; j<BoardSize; j++){
                if(isEmpty(i,j)){
                    ArrayList<int[]> flips = getFlips(color, i, j);
                    if(flips.size()>maxFlips){
                        maxFlips = flips.size();
                        move[0]=i;
                        move[1]=j;
                    }
                }
            }
        }
        if(maxFlips==0) return null;
        return move;
    }



    public BoardStructure(int size){
        for (int r = 0 ; r < size ; r++){
            for (int c = 0; c < size ; c++){
                board[r][c] = OthelloCell.EMPTY;
            }
        }
        int a = size/2 -1;
        int b = size/2;
        board[a][a] = OthelloCell.WHITE;
        board[a][b] = OthelloCell.BLACK;
        board[b][a] = OthelloCell.BLACK;
        board[b][b] = OthelloCell.WHITE;
    }

    /**
     *if tile is already filled, funciton does nothing
     * otherwise places tile--if player is true white, if false black
     * @param color
     * @param r
     * @param c
     */
    public boolean placeTile(boolean color, int r, int c){
        if(board[r][c]!= OthelloCell.EMPTY) return false;
        if(color) {
            boolean flipped = flipTiles(color, r, c);
            if(!flipped) return false;
            board[r][c] = OthelloCell.WHITE;
        }
        else {
            boolean flipped = flipTiles(color, r, c);
            if(!flipped) return false;
            board[r][c] = OthelloCell.BLACK;
        }
        return true;
    }

    public ArrayList<int[]> getFlips(boolean color, int r, int c){
        ArrayList<int[]> tiles = new ArrayList();
        //placed tile was white
        if(color){
            //above tile, make sure not on edge
            if(r>=1){
                int i=0;
                //go up until you reach a tile that is not black
                while(isBlack(r-i-1, c)){
                    i++;
                }
                //if that tile is white, flip everything btwn it and the original white tile
                if(isWhite(r-i-1, c)){
                    for(int j=i; j>0; j--){
                        int[] point = {r-j, c};
                        tiles.add(point);
                    }
                }
            }

            //upper right tile
            if(r>=1 && c<7){
                int i=0;
                while(isBlack(r-i-1, c+i+1)){
                    i++;
                }
                if(isWhite(r-i-1, c+i+1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r-j, c+j};
                        tiles.add(point);
                    }
                }
            }

            //right side
            if(c<7){
                int i=0;
                while(isBlack(r, c+i+1)){
                    i++;
                }
                if(isWhite(r, c+i+1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r, c+j};
                        tiles.add(point);
                    }
                }
            }

            //lower right corner
            if(r<7 && c<7){
                int i=0;
                while(isBlack(r+i+1, c+i+1)){
                    i++;
                }
                if(isWhite(r+i+1, c+i+1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r+j, c+j};
                        tiles.add(point);
                    }
                }
            }

            //below
            if(r<7){
                int i=0;
                while(isBlack(r+i+1, c)){
                    i++;
                }
                if(isWhite(r+i+1, c)){
                    for(int j=i; j>0; j--){
                        int[] point = {r+j, c};
                        tiles.add(point);
                    }
                }
            }

            //lower left
            if(r<7 && c>=1){
                int i=0;
                while(isBlack(r+i+1, c-i-1)){
                    i++;
                }
                if(isWhite(r+i+1, c-i-1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r+j, c-j};
                        tiles.add(point);
                    }
                }
            }

            //left side
            if(c>=1){
                int i=0;
                while(isBlack(r, c-i-1)){
                    i++;
                }
                if(isWhite(r, c-i-1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r, c-j};
                        tiles.add(point);
                    }
                }
            }

            //upper left
            if(r>=1 && c>=1){
                int i=0;
                while(isBlack(r-i-1, c-i-1)){
                    i++;
                }
                if(isWhite(r-i-1, c-i-1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r-j, c-j};
                        tiles.add(point);
                    }
                }
            }

        }
        //tile being placed is black
        else{
            if(r>=1){
                int i=0;
                while(isWhite(r-i-1, c)){
                    i++;
                }
                if(isBlack(r-i-1, c)){
                    for(int j=i; j>0; j--){
                        int[] point = {r-j, c};
                        tiles.add(point);
                    }
                }
            }

            //upper right tile
            if(r>=1 && c<7){
                int i=0;
                while(isWhite(r-i-1, c+i+1)){
                    i++;
                }
                if(isBlack(r-i-1, c+i+1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r-j, c+j};
                        tiles.add(point);
                    }
                }
            }

            //right side
            if(c<7){
                int i=0;
                while(isWhite(r, c+i+1)){
                    i++;
                }
                if(isBlack(r, c+i+1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r, c+j};
                        tiles.add(point);
                    }
                }
            }

            //lower right corner
            if(r<7 && c<7){
                int i=0;
                while(isWhite(r+i+1, c+i+1)){
                    i++;
                }
                if(isBlack(r+i+1, c+i+1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r+j, c+j};
                        tiles.add(point);
                    }
                }
            }

            //below
            if(r<7){
                int i=0;
                while(isWhite(r+i+1, c)){
                    i++;
                }
                if(isBlack(r+i+1, c)){
                    for(int j=i; j>0; j--){
                        int[] point = {r+j, c};
                        tiles.add(point);
                    }
                }
            }

            //lower left
            if(r<7 && c>=1){
                int i=0;
                while(isWhite(r+i+1, c-i-1)){
                    i++;
                }
                if(isBlack(r+i+1, c-i-1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r+j, c-j};
                        tiles.add(point);
                    }
                }
            }

            //left side
            if(c>=1){
                int i=0;
                while(isWhite(r, c-i-1)){
                    i++;
                }
                if(isBlack(r, c-i-1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r, c-j};
                        tiles.add(point);
                    }
                }
            }

            //upper left
            if(r>=1 && c>=1){
                int i=0;
                while(isWhite(r-i-1, c-i-1)){
                    i++;
                }
                if(isBlack(r-i-1, c-i-1)){
                    for(int j=i; j>0; j--){
                        int[] point = {r-j, c-j};
                        tiles.add(point);
                    }
                }
            }
        }
        return tiles;

    }

    /**
     * Flips tiles according to the tile that was just placed
     * true= white, false=black tile being placed
     * @param color
     * @param r
     * @param c
     *
     */
    public boolean flipTiles(boolean color, int r, int c){
        ArrayList<int[]> tiles = getFlips(color, r, c);
        if(tiles.size()==0) return false;
        for(int i=0; i<tiles.size(); i++){
            int[] coords = tiles.get(i);
            if(color) board[coords[0]][coords[1]] = OthelloCell.WHITE;
            else board[coords[0]][coords[1]] = OthelloCell.BLACK;
        }
        return true;
    }

    public String nicerToString(){
        String boardString="";
        for(int n=0;n<BoardSize;n++){
            boardString+=" "+n;
        }
        boardString+="\n";
        for(int i=0;i<board.length;i++) {
            boardString+=i;
            for (int j = 0; j < board.length; j++){
                boardString +="|";
                if(board[i][j]==OthelloCell.EMPTY){
                    boardString+=" ";
                }
                if(board[i][j]==OthelloCell.BLACK){
                    boardString+="x";
                }
                if(board[i][j]==OthelloCell.WHITE){
                    boardString+="o";
                }

            }
            boardString+="\n";
        }
        return boardString;
    }

    /**
     * Evaluates the value of a cell.
     * @param r
     * @param c
     * @return
     */
    public OthelloCell eval(int r, int c){
        if (board[r][c] == OthelloCell.EMPTY){
            return OthelloCell.EMPTY;
        }
        else if (board[r][c] == OthelloCell.BLACK){
            return OthelloCell.BLACK;
        }
        else {
            return OthelloCell.WHITE;
        }
    }

    /**
     * returns true iff there is a cell at that location, and that cell is empty
     * r=row, c=column
     * @param r
     * @param c
     * @return
     */
    public boolean isEmpty(int r, int c) {
        if(r>7 || r<0 || c>7 || c<0) return false;
        return board[r][c] == OthelloCell.EMPTY;
    }

    /**
     * returns true iff there is a cell at that location which contains a black tile
     * r=row, c=colomn
     * @param r
     * @param c
     * @return
     */
    public boolean isBlack (int r, int c) {
        if(r>7 || r<0 || c>7 || c<0) return false;
        return board[r][c] == OthelloCell.BLACK;
    }

    /**
     * returns true iff there is a cell at that location which contains a white tile
     * @param r
     * @param c
     * @return
     */
    public boolean isWhite (int r, int c) {
        if(r>7 || r<0 || c>7 || c<0) return false;
        return board[r][c] == OthelloCell.WHITE;
    }

    public boolean isStuck(boolean color){
        //check all cells
        for(int i=0; i<BoardSize; i++){
            for(int j=0; j<BoardSize; j++){
                if(board[i][j] ==OthelloCell.EMPTY){
                    ArrayList<int[]> flips = getFlips(color, i, j);
                    if(flips.size()!=0) return false;
                }
            }
        }
        return false;
    }

}