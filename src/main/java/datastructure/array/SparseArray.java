package datastructure.array;

/**
 * 稀疏数组，可以对二维数组进行压缩
 * 第一行记录原始数组行、列、有效数据的大小
 * 后面的行记录有效数据的行、列、值
 * 注意：需要注意  如果原始数组不够稀疏 压缩后会比原数组还大
 * 例如原始数组为6行7列 42个数据   可以压缩为n+1行3列的稀疏数组   n为原始数组的有效值   当n>=13是就没有压缩效果了
 */
public class SparseArray {
    public static void main(String[] args) {
        /**
         * 11 * 11的二维数组模拟棋盘
         * 0代表没有棋子，1代表白子，2代表黑子
         */
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        System.out.println("=======原始二维数组=======");
        for (int[] row : chessArray){
            for(int value : row){
                System.out.printf("%d\t",value);
            }
            System.out.println();
        }

        int[][] sparseArray = transformToSparseArray(chessArray);
        System.out.println("=======稀疏数组=======");
        for (int[] row : sparseArray) {
            for (int value : row) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }

        int[][] chessArray1 = transformFromSparseArray(sparseArray);
        System.out.println("=======恢复后的二维数组=======");
        for (int[] row : chessArray1){
            for(int value : row){
                System.out.printf("%d\t",value);
            }
            System.out.println();
        }
    }

    private static int[][] transformFromSparseArray(int[][] sparseArray) {
        int[][] chessArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++){
            chessArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return chessArray;
    }

    private static int[][] transformToSparseArray(int[][] chessArray) {
        int hasValueCount = 0;
        for(int i = 0; i < chessArray.length; i++){
            for(int j = 0; j < chessArray[0].length; j++){
                if(chessArray[i][j] != 0){
                    hasValueCount ++;
                }
            }
        }
        //创建稀疏数组
        int[][] sparseArray = new int[hasValueCount + 1][3];
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = hasValueCount;
        //为稀疏数组赋值
        int count = 1;
        for(int i = 0; i < chessArray.length; i++){
            for(int j = 0; j < chessArray[0].length; j++){
                if(chessArray[i][j] != 0){
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                    count++;
                }
            }
        }
        return sparseArray;
    }
}
