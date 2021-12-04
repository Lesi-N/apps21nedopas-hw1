package ua.edu.ucu.tempseries;

import lombok.Getter;

import java.util.Arrays;
import java.util.InputMismatchException;

@Getter
public class TemperatureSeriesAnalysis {
    private double[] series;
    private int currentSize;
    private int minValue = -273;
    private int maxValue = 1000;

    public TemperatureSeriesAnalysis() {
        this.series = new double[1];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {

        if (!checkCorrectTemp(temperatureSeries)){
            throw new InputMismatchException();
        }

        this.series = temperatureSeries;
        this.currentSize = series.length;
    }

    public boolean checkCorrectTemp(double[] temperatureSeries){
        for (double temp : temperatureSeries){
            if (temp < minValue){
                return false;
            }
        }
        return true;
    }

    public double average() {
        if (this.series.length == 0){
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < currentSize; i++){
            sum += this.series[i];
        }
        return sum / currentSize;
    }

    public double deviation() {
        if (this.series.length == 0){
            throw new IllegalArgumentException();
        }

        double avg = this.average();
        double dev = 0;
        for(double num: series) {
            dev += Math.pow(num - avg, 2);
        }
        return (double) Math.round(Math.sqrt(dev / series.length)*100)/100 ;
    }

    public double min() {
        return this.findTempClosestToValue(minValue);
    }

    public double max() {
        return this.findTempClosestToValue(maxValue);
    }

    public double findTempClosestToZero() {
        return this.findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.series.length == 0){
            throw new IllegalArgumentException();
        }
        double closest = series[0];
        for (double val : series){
            if (Math.abs(tempValue - val) < Math.abs(tempValue - closest)){
                closest = val;
            } else if (Math.abs(tempValue - val) == Math.abs(tempValue - closest)){
                if (closest < 0){
                    closest = val;
                }
            }
        }
        return closest;
    }

    public double[] findTempsLessThan(double tempValue) {
        double[] lessth = new double[currentSize];
        int idx = 0;
        for (double val : series){
            if (val <= tempValue){
                lessth[idx] = val;
                idx++;
            }
        }
        return lessth;
    }

    public double[] findTempsGreaterThan(double tempValue) {
        double[] greaterth = new double[currentSize];
        int idx = 0;
        for (double val : series){
            if (val >= tempValue) {
                greaterth[idx] = val;
                idx++;
            }
        }
        return greaterth;
    }

    public TempSummaryStatistics summaryStatistics() throws IllegalArgumentException {

        return new TempSummaryStatistics(this.average(),
                this.deviation(), this.min(), this.max());
    }

    public int addTemps(double... temps) {
        if (!checkCorrectTemp(temps)){
            throw new InputMismatchException();
        }
        for (double temp : temps){
            if (currentSize == series.length){
                series = Arrays.copyOf(series, series.length*2);
            }
            series[currentSize] = temp;
            currentSize++;
        }
        return (int) Arrays.stream(series).sum();
    }
}
