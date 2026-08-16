class Solution {
    public int[][] generateMatrix(int n) {
        int matrix[][] = new int[n][n];
        int top = 0;
        int bottom = n-1;
        int right = n-1;
        int left =0;
        int num=1;

        while(top <= bottom && left <= right){

            // top row left => right
            for(int col = left;col <= right;col++){
                matrix[top][col]=num++;
            }
            top++;

            // top => bottom
            for(int row = top; row <= bottom;row++){
                matrix[row][right]=num++;
            }
            right--;

            // right => =num++
            if(top <= bottom){
                for(int col = right;col >= left;col--){
                    matrix[bottom][col]=num++;
                }
                bottom--;
            }

            // bottom => top

            if(left <= right){
                for(int row = bottom;row >= top;row--){
                    matrix[row][left]=num++;
                }
                left++;
            }
        }
        return matrix;
    }
    
}