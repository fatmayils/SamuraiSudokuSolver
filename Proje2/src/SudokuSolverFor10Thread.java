
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SudokuSolverFor10Thread extends Thread {

    PreparedStatement preparedStatement = null;
    Connection connection;
    String query;
    double beginTime = 0, endTime = 0;
    static int array1[][] = new int[9][9];
    static int array2[][] = new int[9][9];
    static int array3[][] = new int[9][9];
    static int array4[][] = new int[9][9];
    static int array5[][] = new int[9][9];
    int array11[][] = new int[9][9];
    int array22[][] = new int[9][9];
    int array33[][] = new int[9][9];
    int array333[][] = new int[9][9];

    int array44[][] = new int[9][9];
    int array55[][] = new int[9][9];
    static int N = 9;
    int choose;
    public boolean calisiyor;
    static SamuraiSudokuPanelFor10Thread panel = new SamuraiSudokuPanelFor10Thread();
    static int count = 0;
    static FileWriter fileWriter;
    static ArrayList array = new ArrayList();
    static FileWriter fileWriterAll;

    SudokuSolverFor10Thread(int x) throws IOException, Exception {
        beginTime = System.currentTimeMillis();
        fileWriter = new FileWriter("src/10ThreadRunOrder.txt");
        fileWriterAll = new FileWriter("C:/Users/fatma/Desktop/10thread.txt");

        choose = x;
        try {
            FileReaderAndArrayCreater.funct();
            array1 = FileReaderAndArrayCreater.getArray1();
            array2 = FileReaderAndArrayCreater.getArray2();
            array3 = FileReaderAndArrayCreater.getArray3();
            array4 = FileReaderAndArrayCreater.getArray4();
            array5 = FileReaderAndArrayCreater.getArray5();
            array11 = FileReaderAndArrayCreater.getArray1();
            array22 = FileReaderAndArrayCreater.getArray2();
            array33 = FileReaderAndArrayCreater.getArray3();
            array333 = FileReaderAndArrayCreater.getArray3();
            array44 = FileReaderAndArrayCreater.getArray4();
            array55 = FileReaderAndArrayCreater.getArray5();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SamuraiSudokuPanelFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean solveSuduko(int grid[][], int row,
            int col) throws InterruptedException, IOException, SQLException, Exception {

        fileWriterAll.write(choose + ". thread çalıştı\n");
        if (row == N - 1 && col == N) {
            return true;
        }

        // Check if column value becomes 9 ,
        // we move to next row
        // and column start from 0
        if (col == N) {
            row++;
            col = 0;
        }
        panel.repaint();
        // Check if the current position
        // of the grid already
        // contains value >0, we iterate
        // for next column
        if (grid[row][col] != -1) {
            return solveSuduko(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

            // Check if it is safe to place
            // the num (1-9) in the
            // given row ,col ->we move to next column
            if (isSafe(grid, row, col, num)) {

                /* assigning the num in the current
				(row,col) position of the grid and
				assuming our assigned num in the position
				is correct */
                grid[row][col] = num;
                panel.repaint();
                // Checking for next
                // possibility with next column
                if (solveSuduko(grid, row, col + 1)) {

                    endTime = System.currentTimeMillis();
                    System.out.println("10lu");
                    System.out.println(String.valueOf(choose) + ". thread ( " + row + "," + col + ") " + (endTime - beginTime) + " süre geçti\n");
                    array.add(endTime - beginTime);
                    fileWriter.write(String.valueOf(choose) + ". thread ( " + row + "," + col + ") " + (endTime - beginTime) + " süre geçti\n");

                    query = " insert into 10luthread (x, y, thread,time)"
                            + " values (?, ?, ?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, row);
                    preparedStatement.setInt(2, col);
                    preparedStatement.setInt(3, choose);
                    preparedStatement.setDouble(4, (endTime - beginTime));
                    preparedStatement.execute();
                    return true;
                }
            }
            grid[row][col] = -1;
        }
        return false;
    }

    boolean solveSudukoBack(int grid[][], int row,
            int col) throws InterruptedException, IOException, Exception {
        fileWriterAll.write(choose + ". thread çalıştı\n");
        //System.out.println("choose:" + choose);
        // sleep(1);
        //System.out.println("choose:" + choose);
        //      System.out.println("choose:" + choose);
        if (row == 0 && col == -1) {
            return true;
        }

        // Check if column value becomes 9 ,
        // we move to next row
        // and column start from 0
        if (col == -1) {
            row--;
            col = 8;
        }

        // Check if the current position
        // of the grid already
        // contains value >0, we iterate
        // for next column
        if (grid[row][col] != -1) {
            return solveSudukoBack(grid, row, col - 1);
        }

        for (int num = 1; num < 10; num++) {

            // Check if it is safe to place
            // the num (1-9) in the
            // given row ,col ->we move to next column
            if (isSafe(grid, row, col, num)) {

                /* assigning the num in the current
				(row,col) position of the grid and
				assuming our assigned num in the position
				is correct */
                grid[row][col] = num;

                // Checking for next
                // possibility with next column
                if (solveSudukoBack(grid, row, col - 1)) {

                    endTime = System.currentTimeMillis();
                    fileWriter.write(String.valueOf(choose) + ". thread ( " + row + "," + col + ") " + (endTime - beginTime) + " süre geçti\n");
                    System.out.println("10lu");
                    System.out.println(String.valueOf(choose) + ". thread ( " + row + "," + col + ") " + (endTime - beginTime) + " süre geçti\n");
                    array.add(endTime - beginTime);

                    query = " insert into 10luthread (x, y, thread,time)"
                            + " values (?, ?, ?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, row);
                    preparedStatement.setInt(2, col);
                    preparedStatement.setInt(3, choose);
                    preparedStatement.setDouble(4, (endTime - beginTime));
                    preparedStatement.execute();
                    return true;
                }
            }
            grid[row][col] = -1;
        }
        return false;
    }

    /* A utility function to print grid */
    static void print(int[][] grid) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + "   ");
            }
            //System.out.println();
        }
    }

    boolean isSafe2(int[][] grid, int row, int col,
            int num) throws InterruptedException {
        panel.repaint();

        // Check if we find the same num
        // in the similar row , we
        // return false
        for (int x = 0; x <= 8; x++) {
            panel.repaint();
            if (grid[row][x] == num) {
                return false;
            }

        }

        // Check if we find the same num
        // in the similar column ,
        // we return false
        for (int x = 0; x <= 8; x++) {
            panel.repaint();
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Check if we find the same num
        // in the particular 3*3
        // matrix, we return false
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check whether it will be legal
    // to assign num to the
    // given row, col
    boolean isSafe(int[][] grid, int row, int col,
            int num) throws InterruptedException {

        panel.repaint();
        // Check if we find the same num
        // in the similar row , we
        // return false
        for (int x = 0; x <= 8; x++) {

            if (grid[row][x] == num) {
                return false;
            }

        }

        // Check if we find the same num
        // in the similar column ,
        // we return false
        for (int x = 0; x <= 8; x++) {

            if (grid[x][col] == num) {
                return false;
            }
        }

        // Check if we find the same num
        // in the particular 3*3
        // matrix, we return false
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        //3le ortak alan
        if (choose == 1 || choose == 6) {

            if (col > 5 && row > 5) {
                if (col > 5 && row > 5) {

                    if (isSafe2(array3, row - 6, col - 6, num)) {

                    } else {
                        return false;
                    }

                    for (int x = 0; x <= 2; x++) {
                        panel.repaint();
                        if (array2[row][x] == num) {
                            return false;
                        }

                    }
                    for (int x = 0; x <= 2; x++) {
                        panel.repaint();
                        if (array4[x][col] == num) {
                            return false;
                        }
                    }
                }
            }

        } else if (choose == 2 || choose == 7) {
            if (col < 3 && row > 5) {
                if (isSafe2(array3, row - 6, col + 6, num)) {

                } else {
                    return false;
                }
                for (int x = 6; x <= 8; x++) {
                    panel.repaint();
                    if (array1[row][x] == num) {
                        return false;
                    }

                }
                for (int x = 0; x <= 2; x++) {
                    panel.repaint();
                    if (array5[x][col] == num) {
                        return false;
                    }
                }
            }
        } else if (choose == 3 || choose == 8) {

            if (row < 3 && col < 3) {

                if (isSafe2(array1, row + 6, col + 6, num)) {

                } else {
                    return false;
                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array1[row + 6][x] == num) {
                        return false;
                    }

                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array1[x][col + 6] == num) {
                        return false;
                    }
                }
            }

            if (row < 3 && col > 5) {
                if (isSafe2(array2, row + 6, col - 6, num)) {

                } else {
                    return false;
                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array2[row + 6][x] == num) {
                        return false;
                    }

                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array2[x][col - 6] == num) {
                        return false;
                    }
                }
            }

            if (row > 5 && col < 3) {

                if (isSafe2(array4, row - 6, col + 6, num)) {

                } else {
                    return false;
                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array4[row - 6][x] == num) {
                        return false;
                    }

                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array4[x][col + 6] == num) {
                        return false;
                    }
                }
            }

            if (row > 5 && col > 5) {
                if (isSafe2(array5, row - 6, col - 6, num)) {

                } else {
                    return false;
                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array5[row - 6][x] == num) {
                        return false;
                    }

                }
                for (int x = 0; x <= 8; x++) {
                    panel.repaint();
                    if (array5[x][col - 6] == num) {
                        return false;
                    }
                }
            }
        } else if (choose == 4 || choose == 9) {
            if (col > 5 && row < 3) {
                if (isSafe2(array3, row + 6, col - 6, num)) {

                } else {
                    return false;
                }
                for (int x = 0; x <= 2; x++) {
                    panel.repaint();
                    if (array5[row][x] == num) {
                        return false;
                    }

                }
                for (int x = 6; x <= 8; x++) {
                    panel.repaint();
                    if (array1[x][col] == num) {
                        return false;
                    }
                }
            }
        } else if (choose == 5 || choose == 10) {
            if (col < 3 && row < 3) {
                if (isSafe2(array3, row + 6, col + 6, num)) {

                } else {
                    return false;
                }
            }
            for (int x = 6; x <= 8; x++) {
                panel.repaint();
                if (array4[row][x] == num) {
                    return false;
                }

            }
            for (int x = 6; x <= 8; x++) {
                panel.repaint();
                if (array2[x][col] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Driver Code
    public void solverMethod(int i) throws InterruptedException, IOException, Exception {

        if (i == 1) {
            if (solveSuduko(array1, 0, 0)) {
                count++;
                one = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        array33[x][y] = array1[x + 6][y + 6];
                    }
                }
                array3 = array33;
                durdur();
            } else {
                one = false;
                three = false;
                eight = false;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        //  array33[x][y] = -1;
                    }
                }
                array3 = array333;
            }
        }
        if (i == 2) {
            count++;
            if (solveSuduko(array2, 0, 0)) {
                two = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        array33[x][y + 6] = array2[x + 6][y];
                    }
                }
                array3 = array33;
                durdur();
            } else {
                two = false;
                three = false;
                eight = false;
                // solveSuduko(array22, 0, 0);
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {

                        //array3[x][y + 6] = array2[x + 6][y];
                        // array33[x][y + 6] = -1;
                    }
                }
                array3 = array333;

            }
        }
        if (i == 3) {
            count++;
            calisiyor = true;
            if (solveSuduko(array3, 0, 0)) {
                three = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        // array1[x + 6][y + 6] = array3[x][y];
                        array11[x + 6][y + 6] = array3[x][y];
                        //  array2[x + 6][y] = array3[x][y + 6];
                        array22[x + 6][y] = array3[x][y + 6];
                        // array4[x][y + 6] = array3[x + 6][y];
                        array44[x][y + 6] = array3[x + 6][y];
                        //  array5[x][y] = array3[x + 6][y + 6];
                        array55[x][y] = array3[x + 6][y + 6];
                        array33[x][y] = array3[x][y];
                    }
                }
                array1 = array11;
                array2 = array22;
                // array3 = array33;
                array4 = array44;
                array5 = array55;
                durdur();
            } else {
                three = false;
                array3 = array333;
            }
        }
        if (i == 4) {
            count++;
            if (solveSuduko(array4, 0, 0)) {
                four = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {

                        array33[x + 6][y] = array4[x][y + 6];

                    }
                }
                array3 = array33;
                durdur();
            } else {
                three = false;
                eight = false;
                four = false;
                array3 = array333;
            }
        }
        if (i == 5) {
            count++;
            if (solveSuduko(array5, 0, 0)) {
                five = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {

                        array33[x + 6][y + 6] = array5[x][y];
                    }
                }
                array3 = array33;
                durdur();
            } else {
                three = false;
                eight = false;
                five = false;
                array3 = array333;
            }

        }

        if (i == 6) {
            if (solveSudukoBack(array1, 8, 8)) {
                count++;
                six = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        array33[x][y] = array1[x + 6][y + 6];
                    }
                }
                array3 = array33;
                durdur();
            } else {
                three = false;
                eight = false;
                six = false;
                array3 = array333;

            }
        }
        if (i == 7) {
            count++;
            if (solveSudukoBack(array2, 8, 8)) {
                seven = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {

                        array33[x][y + 6] = array2[x + 6][y];

                    }
                }
                array3 = array33;
                durdur();
            } else {
                three = false;
                eight = false;
                seven = false;
                array3 = array333;
            }
        }
        if (i == 8) {
            count++;
            calisiyor = true;
            if (solveSudukoBack(array3, 8, 8)) {
                eight = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        // array1[x + 6][y + 6] = array3[x][y];
                        array11[x + 6][y + 6] = array3[x][y];
                        //  array2[x + 6][y] = array3[x][y + 6];
                        array22[x + 6][y] = array3[x][y + 6];
                        // array4[x][y + 6] = array3[x + 6][y];
                        array44[x][y + 6] = array3[x + 6][y];
                        //  array5[x][y] = array3[x + 6][y + 6];
                        array55[x][y] = array3[x + 6][y + 6];
                        array33[x][y] = array3[x][y];
                    }
                }
                array1 = array11;
                array2 = array22;
                // array3 = array33;
                array4 = array44;
                array5 = array55;
                durdur();
            } else {
                three = false;
                eight = false;
                array3 = array333;
            }
        }
        if (i == 9) {
            count++;
            if (solveSudukoBack(array4, 8, 8)) {
                nine = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {

                        array33[x + 6][y] = array4[x][y + 6];

                    }
                }
                array3 = array33;
                durdur();
            } else {
                three = false;
                eight = false;
                nine = false;
                array3 = array333;
            }
        }
        if (i == 10) {
            count++;
            if (solveSudukoBack(array5, 8, 8)) {
                ten = true;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {

                        array33[x + 6][y + 6] = array5[x][y];
                    }
                }
                array3 = array33;
                durdur();
            } else {
                three = false;
                eight = false;
                ten = false;
                array3 = array333;
            }

        }

    }

    public void durdur() {
        calisiyor = false;
    }
    static boolean one = false, two = false, three = false, four = false, five = false, six = false, seven = false, eight = false, nine = false, ten = false;
 static int a=0;
    @Override
    public void run() {

        calisiyor = true;
        int x = 5;
        while (x == 5) {
            if(a==0)
            {
                try {
                    this.connection = DatabaseConnection.connect();
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor10Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
                 a++;
            }
            if (choose == 1 && one == false) {
                try {

                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 2 && two == false) {
                try {

                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 3 && three == false) {
                try {
                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 4 && four == false) {

                try {
                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 5 && five == false) {
                try {
                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (choose == 6 && one == false) {
                try {

                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 7 && two == false) {
                try {

                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 8 && three == false) {
                try {
                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 9 && four == false) {

                try {
                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choose == 10 && five == false) {
                try {
                    solverMethod(choose);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SudokuSolverFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
