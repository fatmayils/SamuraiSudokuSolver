
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class Graphic1 extends JFrame {

    public Graphic1() {

        initUI();
    }

    public void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        pack();
        this.setTitle("Line chart");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public XYDataset createDataset() {

        var threadFor5Graphic = new XYSeries("5 Thread");
        Collections.sort(SudokuSolverFor5Thread1.array);
        threadFor5Graphic.add(0, 0);
        for (int i = 0; i < SudokuSolverFor5Thread1.array.size(); i++) {
            threadFor5Graphic.add((Number) (SudokuSolverFor5Thread1.array.get(i)), i + 1);
        }

        var threadFor10Graphic = new XYSeries("10 Thread");
        Collections.sort(SudokuSolverFor10Thread1.array);
        threadFor10Graphic.add(0, 0);
        for (int i = 0; i < SudokuSolverFor10Thread1.array.size(); i++) {
            threadFor10Graphic.add((Number) (SudokuSolverFor10Thread1.array.get(i)), i + 1);
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(threadFor5Graphic);
        dataset.addSeries(threadFor10Graphic);

        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Zaman ve Bulunan Kutu Sayısı",
                "Zaman",
                "Kutu Sayısı",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Zaman ve Bulunan Kutu Sayısı",
                new Font("Serif", Font.BOLD, 18)
        )
        );

        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Graphic1 ex = new Graphic1();
                    ex.setVisible(true);
                } catch (HeadlessException ex) {
                    Logger.getLogger(SamuraiSudokuFrame1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
