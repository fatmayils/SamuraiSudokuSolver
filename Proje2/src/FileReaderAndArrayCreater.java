
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderAndArrayCreater {

    static int array1[][] = new int[9][9];
    static int array2[][] = new int[9][9];
    static int array3[][] = new int[9][9];
    static int array4[][] = new int[9][9];
    static int array5[][] = new int[9][9];

    public static void funct() throws FileNotFoundException {

        int row = 0;
        int coulumn = 0;
        int array1StartIndex = 0;
        int array2StartIndex = 0;
        int array3StartIndex = 0;
        int array4StartIndex = 0;
        int array5StartIndex = 0;
        File myObj = new File("proje.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            for (int i = 0; i < data.length(); i++) {
                if (coulumn < 9 && i < 9) {
                    array1[array1StartIndex][i] = Character.getNumericValue(data.charAt(i));

                    if (i == 8) {
                        array1StartIndex++;
                    }
                }
                if (coulumn < 9 && i >= 9 && i < 21) {
                    if (coulumn > 5 && i >= 12 && i < 21) {
                        array2[array2StartIndex][i - 12] = Character.getNumericValue(data.charAt(i));

                        if (i - 12 == 8) {
                            array2StartIndex++;
                        }
                    } else if (i < 18) {
                        array2[array2StartIndex][i - 9] = Character.getNumericValue(data.charAt(i));
                        if (i - 9 == 8) {
                            array2StartIndex++;
                        }
                    }
                }
                if (coulumn < 15 && coulumn >= 6) {

                    if (coulumn >= 9 && coulumn < 12) {
                        array3[array3StartIndex][i] = Character.getNumericValue(data.charAt(i));
                        if (i == 8) {
                            array3StartIndex++;
                        }
                    } else if (i >= 6 && i < 15) {
                        array3[array3StartIndex][i - 6] = Character.getNumericValue(data.charAt(i));
                        if (i - 6 == 8) {
                            array3StartIndex++;
                        }
                    }
                }
                if (coulumn >= 12 && i < 9) {
                    array4[array4StartIndex][i] = Character.getNumericValue(data.charAt(i));
                    if (i == 8) {
                        array4StartIndex++;
                    }
                }
                if (coulumn >= 12) {
                    if (coulumn < 15 && i >= 12 && i < 21) {
                        array5[array5StartIndex][i - 12] = Character.getNumericValue(data.charAt(i));
                        if (i - 12 == 8) {
                            array5StartIndex++;
                        }
                    } else if (coulumn >= 15 && i >= 9) {
                        array5[array5StartIndex][i - 9] = Character.getNumericValue(data.charAt(i));
                        if (i - 9 == 8) {
                            array5StartIndex++;
                        }
                    }
                }

            }
            coulumn++;
            // System.out.println(data);
        }
        myReader.close();
    }

    public static int[][] getArray1() throws FileNotFoundException {

        return array1;
    }

    public static int[][] getArray2() throws FileNotFoundException {

        return array2;
    }

    public static int[][] getArray3() throws FileNotFoundException {

        return array3;
    }

    public static int[][] getArray4() throws FileNotFoundException {

        return array4;
    }

    public static int[][] getArray5() throws FileNotFoundException {
        return array5;
    }

    public static void setArray1(int[][] array1) {
        FileReaderAndArrayCreater.array1 = array1;
    }

    public static void setArray2(int[][] array2) {
        FileReaderAndArrayCreater.array2 = array2;
    }

    public static void setArray3(int[][] array3) {
        FileReaderAndArrayCreater.array3 = array3;
    }

    public static void setArray4(int[][] array4) {
        FileReaderAndArrayCreater.array4 = array4;
    }

    public static void setArray5(int[][] array5) {
        FileReaderAndArrayCreater.array5 = array5;
    }
}
