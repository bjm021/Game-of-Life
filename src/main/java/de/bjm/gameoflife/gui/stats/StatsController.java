package de.bjm.gameoflife.gui.stats;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StatsController {

    private XYChart.Series dataSeries1 = new XYChart.Series();

    @FXML
    AnchorPane mainPane;

    @FXML
    Label livingLabel, stepLabel;





    @FXML
    public void initialize() {
        mainPane.getChildren().remove(0);

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Steps");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Living Cells");

        AreaChart areaChart = new AreaChart(xAxis, yAxis);
        areaChart.setTitle("Living Cells");


        dataSeries1.setName("Cells");



        areaChart.getData().add(dataSeries1);
        areaChart.setCreateSymbols(false);

        mainPane.getChildren().add(areaChart);
    }

    public void addData(int step, int livingCells) {
        dataSeries1.getData().add(new XYChart.Data(step, livingCells));
        livingLabel.setText(String.valueOf(livingCells));
        stepLabel.setText(String.valueOf(step));

    }

    public void resetGraph() {
        dataSeries1.getData().clear();
        dataSeries1.getData().add(new XYChart.Data(0, 0));


        livingLabel.setText("0");
        stepLabel.setText("0");
    }

}
