package gradetracker.gui;

import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

import gradetracker.data.AppData;
/**
 * Area Chart with 3 series
 * 
 * Demonstrates the following:
 * <ul>
 * <li>Area Chart
 * <li>Place legend at Inside-NE position
 * <li>ChartBuilder
 */
public class AreaChart implements ExampleChart<XYChart>{
 
  public static void main(String[] args) {
    ExampleChart<XYChart> exampleChart = new AreaChart();
    XYChart chart = exampleChart.getChart();
    new SwingWrapper<XYChart>(chart).displayChart();
  }
 
  public XYChart getChart() {
 
    // Create Chart
    XYChart chart = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("Grade Level").yAxisTitle("Frequency").build();
 
    // Customize Chart
    chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
    chart.getStyler().setAxisTitlesVisible(false);
    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
 
    // Series
    double[] frequency = new double[7];
    
    for (int i = 0; i < AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests
			.size(); i++) {
    	frequency[AppData.students.get(AppData.studentNumber).subjects.get(AppData.subjectNumber).tests.get(i)
  				.getLevel() - 1]++;
	}
    
    chart.addSeries("Frequency", new double[] {1,2,3,4,5,6,7}, frequency);
 
    return chart;
  }

@Override
public String getExampleChartName() {
	// TODO Auto-generated method stub
	return null;
}
 
}