package com.sc.queue;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int dimension = in.nextInt();
        char[][] grid = new char[dimension][dimension];
        for(int i=0;i<dimension;i++){
            String line = in.next();
            for (int j = 0; j < dimension; j++) {
                grid[i][j]= line.charAt(j);
            }
        }
        checkForCavity(grid,dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    private static void checkForCavity(char[][] grid, int dimension)
    {
        for (int i = 1; i < dimension-1; i++) {
            for (int j = 1; j < dimension-1; j++) {
                int prospect = grid[i][j];
                int top = grid[i][j-1];
                int bottom = grid[i][j+1];
                int left = grid[i-1][j];
                int right = grid[i+1][j];
                
                if((prospect>top)&&(prospect>bottom)&&(prospect>left)&&(prospect>right)){
                    grid[i][j]='X';
                }
                
            }
        }
    }
}

