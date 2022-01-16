import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class SamuraiSudokuPanelFor5Thread extends JPanel {

    int array1[][] = new int[9][9];
    int array2[][] = new int[9][9];
    int array3[][] = new int[9][9];
    int array4[][] = new int[9][9];
    int array5[][] = new int[9][9];

    SamuraiSudokuPanelFor5Thread() {
        this.setPreferredSize(new Dimension(35 * 21, 35 * 21));
        try {
            FileReaderAndArrayCreater.funct();
            array1 = FileReaderAndArrayCreater.getArray1();
            array2 = FileReaderAndArrayCreater.getArray2();
            array3 = FileReaderAndArrayCreater.getArray3();
            array4 = FileReaderAndArrayCreater.getArray4();
            array5 = FileReaderAndArrayCreater.getArray5();
            /* for(int i=0;i<9;i++)
            {
                for(int j=0;j<9;j++)
                {
                    System.out.print(array1[i][j]+"  ");
                }
                System.out.println("");
            }*/
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SamuraiSudokuPanelFor5Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.repaint();
        super.paintComponent(g);
        super.repaint();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(1.0f, 1.0f, 1.0f));

        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(new Color(0.0f, 0.0f, 0.0f));
        int slotWidth = this.getWidth() / 21;
        int slotHeight = this.getHeight() / 21;

        //dik çizgileri çizdirdik
        for (int x = 0; x <= 21; x++) {
            if (x <= 9) {
                if (x % 3 == 0) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(x * 35, 0, x * 35, this.getHeight() / 21 * 9);
                    g2d.drawLine(x * 35, 12 * 35, x * 35, this.getHeight() / 21 * 21);
                } else {
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawLine(x * 35, 0, x * 35, this.getHeight() / 21 * 9);
                    g2d.drawLine(x * 35, 12 * 35, x * 35, this.getHeight() / 21 * 21);
                }
            }
            if (x >= 6 && x <= 15) {
                if (x % 3 == 0) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(x * 35, 6 * 35, x * 35, this.getHeight() / 21 * 15);
                } else {
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawLine(x * 35, 6 * 35, x * 35, this.getHeight() / 21 * 15);
                }
            }
            if (x >= 12) {
                if (x % 3 == 0) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(x * 35, 0, x * 35, this.getHeight() / 21 * 9);
                    g2d.drawLine(x * 35, 12 * 35, x * 35, this.getHeight() / 21 * 21);
                } else {
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawLine(x * 35, 0, x * 35, this.getHeight() / 21 * 9);
                    g2d.drawLine(x * 35, 12 * 35, x * 35, this.getHeight() / 21 * 21);
                }
            }
        }

        //yatay çizgileri çizdirdik
        for (int y = 0; y <= 21; y++) {

            if (y <= 9) {
                if (y % 3 == 0) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(0, y * 35, this.getWidth() / 21 * 9, y * 35);
                    g2d.drawLine(35 * 12, y * 35, this.getWidth(), y * 35);
                } else {
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawLine(0, y * 35, this.getWidth() / 21 * 9, y * 35);
                    g2d.drawLine(35 * 12, y * 35, this.getWidth(), y * 35);
                }
            }
            if (y >= 6 && y <= 15) {
                if (y % 3 == 0) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(35 * 6, y * 35, this.getWidth() / 21 * 15, y * 35);
                } else {
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawLine(35 * 6, y * 35, this.getWidth() / 21 * 15, y * 35);
                }
            }
            if (y >= 12) {
                if (y % 3 == 0) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawLine(0, y * 35, this.getWidth() / 21 * 9, y * 35);
                    g2d.drawLine(35 * 12, y * 35, this.getWidth(), y * 35);
                } else {
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawLine(0, y * 35, this.getWidth() / 21 * 9, y * 35);
                    g2d.drawLine(35 * 12, y * 35, this.getWidth(), y * 35);
                }
            }

        }

        Font f = new Font("Times New Roman", Font.PLAIN, 15);
        g2d.setFont(f);
        FontRenderContext fContext = g2d.getFontRenderContext();
        //verileri yazdırdık
        for (int row = 0;
                row < 21; row++) {
            this.repaint();
            super.repaint();
            for (int col = 0; col < 21; col++) {
                this.repaint();
                super.repaint();
                int textWidth;
                int textHeight;
                //1.
                if (col < 9 && row < 9) {
                    textWidth = (int) f.getStringBounds("1", fContext).getWidth();
                    textHeight = (int) f.getStringBounds("1", fContext).getHeight();
                    g2d.drawString(array1[row][col] == -1 ? " " : String.valueOf(array1[row][col]), (col * slotWidth) + ((slotWidth / 2) - (textWidth / 2)), (row * slotHeight) + ((slotHeight / 2) + (textHeight / 2)));
                }
                //4.
                if (col < 9 && row >= 12) {
                    textWidth = (int) f.getStringBounds("1", fContext).getWidth();
                    textHeight = (int) f.getStringBounds("1", fContext).getHeight();
                    g2d.drawString(array4[row - 12][col] == -1 ? " " : String.valueOf(array4[row - 12][col]), (col * slotWidth) + ((slotWidth / 2) - (textWidth / 2)), (row * slotHeight) + ((slotHeight / 2) + (textHeight / 2)));
                }
                //5.
                if (col >= 12 && row >= 12) {
                    textWidth = (int) f.getStringBounds("1", fContext).getWidth();
                    textHeight = (int) f.getStringBounds("1", fContext).getHeight();
                    g2d.drawString(array5[row - 12][col - 12] == -1 ? " " : String.valueOf(array5[row - 12][col - 12]), (col * slotWidth) + ((slotWidth / 2) - (textWidth / 2)), (row * slotHeight) + ((slotHeight / 2) + (textHeight / 2)));
                }
                //2.
                if (col >= 12 && row < 9) {
                    textWidth = (int) f.getStringBounds("1", fContext).getWidth();
                    textHeight = (int) f.getStringBounds("1", fContext).getHeight();
                    g2d.drawString(array2[row][col - 12] == -1 ? " " : String.valueOf(array2[row][col - 12]), (col * slotWidth) + ((slotWidth / 2) - (textWidth / 2)), (row * slotHeight) + ((slotHeight / 2) + (textHeight / 2)));
                }
                //3.
                if (col >= 6 && row >= 6 && col < 15 && row < 15) {
                    textWidth = (int) f.getStringBounds("1", fContext).getWidth();
                    textHeight = (int) f.getStringBounds("1", fContext).getHeight();
                    g2d.drawString(array3[row - 6][col - 6] == -1 ? " " : String.valueOf(array3[row - 6][col - 6]), (col * slotWidth) + ((slotWidth / 2) - (textWidth / 2)), (row * slotHeight) + ((slotHeight / 2) + (textHeight / 2)));
                }
            }
        }
    }

}
