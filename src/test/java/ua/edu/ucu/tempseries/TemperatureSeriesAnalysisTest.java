package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;


import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private double[] temperatureSeries1 = new double[]{3.0, -5.0, 1.0, 5.0};
    private double[] temperatureSeries2 = new double[]{-1.0};
    private double[] temperatureSeries3 = new double[]{};
    private TemperatureSeriesAnalysis seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries1);
    private TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
    private TemperatureSeriesAnalysis seriesAnalysis3 = new TemperatureSeriesAnalysis(temperatureSeries3);


    @Test(expected = InputMismatchException.class)
    public void testMinTemp() {
        double[] temperatureSeries = {-274};

        // expect exception
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

    }

    @Test
    public void testAverageWithOneElementArray() {
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis2.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {

        // expect exception here
        seriesAnalysis3.average();
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;

        double actualResult = seriesAnalysis1.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }
    @Test
    public void testDeviation(){

        double expected = 3.74;
        double actual = seriesAnalysis1.deviation();

        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testMax(){

        double expected = 5.0;
        double actual = seriesAnalysis1.max();

        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testMin(){

        double expected = -5.0;
        double actual = seriesAnalysis1.min();

        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue(){

        double val = 2.0;

        double expectedVal = 3.0;
        double actualVal = seriesAnalysis1.findTempClosestToValue(val);
        assertEquals(expectedVal, actualVal,0.00001);

        double expectedZero = 1.0;
        double actualZero = seriesAnalysis1.findTempClosestToValue(0);
        assertEquals(expectedZero, actualZero,0.00001);

        double[] series = {-0.3, 0.3};
        TemperatureSeriesAnalysis seriesAnalysis4 = new TemperatureSeriesAnalysis(series);
        assertEquals(seriesAnalysis4.findTempClosestToZero(), 0.3, 0.00001);
    }

    @Test
    public void testFindLessThan(){

        double val = 2.0;

        double[] expected = new double[]{-5.0, 1.0, 0.0, 0.0};
        double[] actual = seriesAnalysis1.findTempsLessThan(val);
        assertArrayEquals(expected, actual,0.00001);

        double newval = -6.0;

        double[] newexpected = new double[]{0.0, 0.0, 0.0, 0.0};
        double[] newactual = seriesAnalysis1.findTempsLessThan(newval);
        assertArrayEquals(newexpected, newactual,0.00001);

    }

    @Test
    public void testFindGreaterThan(){
        double val = 2.0;

        double[] expected = {3.0, 5.0, 0.0, 0.0};
        double[] actual = seriesAnalysis1.findTempsGreaterThan(val);
        assertArrayEquals(expected, actual, 0.00001);

        double newval = 6.0;

        double[] newexpected = {0.0, 0.0, 0.0, 0.0};
        double[] newactual = seriesAnalysis1.findTempsGreaterThan(newval);
        assertArrayEquals(newexpected, newactual, 0.00001);
    }

    @Test
    public void testSummary(){

        TempSummaryStatistics expected = new
                TempSummaryStatistics(1.0,3.74,-5.0, 5.0);
        TempSummaryStatistics actual = seriesAnalysis1.summaryStatistics();

        assertEquals(expected.getAvgTemp(), actual.getAvgTemp(), 0.00001);
        assertEquals(expected.getDevTemp(), actual.getDevTemp(), 0.00001);
        assertEquals(expected.getMinTemp(), actual.getMinTemp(), 0.00001);
        assertEquals(expected.getMaxTemp(), actual.getMaxTemp(), 0.00001);

    }
    @Test
    public void testAddTemps(){
        seriesAnalysis1.addTemps(10.0, 0.0);
        assertArrayEquals(seriesAnalysis1.getSeries(),
                new double[]{3.0, -5.0, 1.0, 5.0, 10.0, 0.0, 0.0, 0.0}, 0.00001);

    }

    @Test(expected = InputMismatchException.class)
    public void testAddTooSmallTemps(){
        seriesAnalysis1.addTemps(-274.0);

    }
}
