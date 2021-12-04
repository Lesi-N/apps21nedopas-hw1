package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import lombok.Builder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private double[] temperatureSeries;
    private TemperatureSeriesAnalysis seriesAnalysis;

    @BeforeEach
    public void setUp(){
        temperatureSeries = new double[]{3.0, -5.0, 1.0, 5.0};
        seriesAnalysis = new TemperatureSeriesAnalysis();
    }

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
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }
    @Test
    public void testDeviation(){

        double expected = 3.74;
        double actual = seriesAnalysis.deviation();

        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testMax(){

        double expected = 5.0;
        double actual = seriesAnalysis.max();

        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testMin(){

        double expected = -5.0;
        double actual = seriesAnalysis.min();

        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue(){

        double val = 2.0;

        double expectedVal = 3.0;
        double actualVal = seriesAnalysis.findTempClosestToValue(val);
        assertEquals(expectedVal, actualVal,0.00001);

        double expectedZero = 1.0;
        double actualZero = seriesAnalysis.findTempClosestToValue(0);
        assertEquals(expectedZero, actualZero,0.00001);

        double[] series = {-0.3, 0.3};
        assertEquals(seriesAnalysis.findTempClosestToZero(), 0.3, 0.00001);
    }

    @Test
    public void testFindLessThan(){

        double val = 2.0;

        double[] expected = {-5.0, 0.1};
        double[] actual = seriesAnalysis.findTempsLessThan(val);
        assertEquals(expected, actual);

        double newval = -6.0;

        double[] newexpected = {};
        double[] newactual = seriesAnalysis.findTempsLessThan(newval);
        assertEquals(newexpected, newactual);

    }

    @Test
    public void testFindGreaterThan(){
        double val = 2.0;

        double[] expected = {3.0, 5.0};
        double[] actual = seriesAnalysis.findTempsLessThan(val);
        assertEquals(expected, actual);

        double newval = 6.0;

        double[] newexpected = {};
        double[] newactual = seriesAnalysis.findTempsLessThan(newval);
        assertEquals(newexpected, newactual);
    }

    @Test
    public void testSummary(){

        TempSummaryStatistics expected = new
                TempSummaryStatistics(1.0,3.74,-5.0, 0.5);
        TempSummaryStatistics actual = seriesAnalysis.summaryStatistics();

        assertEquals(expected.avgTemp, actual.avgTemp, 0.00001);
        assertEquals(expected.devTemp, actual.devTemp, 0.00001);
        assertEquals(expected.minTemp, actual.minTemp, 0.00001);
        assertEquals(expected.maxTemp, actual.maxTemp, 0.00001);

    }
    @Test
    public void testAddTemps(){
        seriesAnalysis.addTemps(10.0, 0.0);
        assertEquals(seriesAnalysis.getSeries(), new double[]{3.0, -5.0, 1.0, 5.0, 10.0, 0.0, 0.0, 0.0});

    }

    @Test(expected = InputMismatchException.class)
    public void testAddTooSmallTemps(){
        seriesAnalysis.addTemps(-274);

    }
}
