
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SamuraiSudokuFrame extends JFrame {

    private SamuraiSudokuPanelFor5Thread sudokuPanel;
    private SamuraiSudokuPanelFor10Thread sudokuPanel2;
    static int one = 0, two = 0, three = 0, four = 0, five = 0;
    public static SudokuSolverFor5Thread sudokuSolvers[] = new SudokuSolverFor5Thread[5];
    public static SudokuSolverFor10Thread sudokuSolverss[] = new SudokuSolverFor10Thread[10];

    public SamuraiSudokuFrame() throws HeadlessException, IOException, Exception {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SamuraiSudoku Solver");
        this.setMinimumSize(new Dimension(1500, 800));

        JPanel winPanel = new JPanel();
        winPanel.setLayout(new FlowLayout());
        winPanel.setPreferredSize(new Dimension(1500, 800));
        sudokuPanel = new SamuraiSudokuPanelFor5Thread();
        sudokuPanel.setBounds(0, 0, 750, 750);
        winPanel.add(sudokuPanel);
        this.add(winPanel);
        JButton button1 = new JButton();
        button1.setText("10 tane thread ile çözümü");
        button1.setBounds(750, 750, 100, 40);
        winPanel.add(button1);
        JButton button2 = new JButton();
        button2.setText("Çözüm grafiklerine bakmak için tıklayınız");
        button2.setBounds(900, 900, 100, 40);
        winPanel.add(button2);
        for (int i = 1; i < 6; i++) {
            sudokuSolvers[i - 1] = new SudokuSolverFor5Thread(i);
        }
        sudokuSolvers[0].start();
        sudokuSolvers[1].start();
        sudokuSolvers[2].start();
        sudokuSolvers[3].start();
        sudokuSolvers[4].start();

        button1.addActionListener(new ActionListener() {
            int x = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuSolvers[0].stop();
                sudokuSolvers[1].stop();
                sudokuSolvers[2].stop();
                sudokuSolvers[3].stop();
                sudokuSolvers[4].stop();
                if (x == 0) {
                    for (int i = 1; i < 11; i++) {
                        try {
                            sudokuSolverss[i - 1] = new SudokuSolverFor10Thread(i);
                        } catch (IOException ex) {
                            Logger.getLogger(SamuraiSudokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(SamuraiSudokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    sudokuPanel2 = new SamuraiSudokuPanelFor10Thread();
                    sudokuPanel2.setBounds(750, 0, 750, 750);
                    winPanel.add(sudokuPanel2);
                } else {

                    sudokuSolverss[0].stop();
                    sudokuSolverss[1].stop();
                    sudokuSolverss[2].stop();
                    sudokuSolverss[3].stop();
                    sudokuSolverss[4].stop();
                    sudokuSolverss[5].stop();
                    sudokuSolverss[6].stop();
                    sudokuSolverss[7].stop();
                    sudokuSolverss[8].stop();
                    sudokuSolverss[9].stop();
                    for (int i = 1; i < 11; i++) {
                        try {
                            sudokuSolverss[i - 1] = new SudokuSolverFor10Thread(i);
                        } catch (IOException ex) {
                            Logger.getLogger(SamuraiSudokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(SamuraiSudokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                sudokuSolverss[0].start();
                sudokuSolverss[1].start();
                sudokuSolverss[2].start();
                sudokuSolverss[3].start();
                sudokuSolverss[4].start();
                sudokuSolverss[5].start();
                sudokuSolverss[6].start();
                sudokuSolverss[7].start();
                sudokuSolverss[8].start();
                sudokuSolverss[9].start();
                x++;
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuSolverss[0].stop();
                sudokuSolverss[1].stop();
                sudokuSolverss[2].stop();
                sudokuSolverss[3].stop();
                sudokuSolverss[4].stop();
                sudokuSolverss[5].stop();
                sudokuSolverss[6].stop();
                sudokuSolverss[7].stop();
                sudokuSolverss[8].stop();
                sudokuSolverss[9].stop();
                Graphic graphic = new Graphic();
                graphic.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SamuraiSudokuFrame frame = new SamuraiSudokuFrame();
                    frame.setVisible(true);
                } catch (HeadlessException ex) {
                    Logger.getLogger(SamuraiSudokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SamuraiSudokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(SamuraiSudokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
