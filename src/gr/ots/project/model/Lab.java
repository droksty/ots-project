package gr.ots.project.model;

import gr.ots.project.model.consts.Category;
import gr.ots.project.model.consts.PredefinedTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Έστω ότι κάθε Lab μπορεί να εκτελεί μόνο μία κατηγορία ελέγχων: PHYSICAL, CHEMICAL, BACTERIOLOGICAL
 */
public class Lab implements ILab {
    private String title;
    private final Category labType;
    private final Queue<WaterSample> samplesQueue = new ArrayDeque<>();

    private Lab(Category labType) {
        this.labType = labType;
    }

    public static Lab createLab(String title, Category labType) {
        Lab lab = new Lab(labType);
        lab.title = title;
        return lab;
    }


    // Getters & Setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Category getLabType() {
        return labType;
    }
    private Queue<WaterSample> getSamplesQueue() {
        return samplesQueue;
    }
    public Queue<WaterSample> getCopyOfSamplesQueue() {
        return new ArrayDeque<>(getSamplesQueue());
    }


    //
    @Override
    public void receiveSample(WaterSample sample) {
        System.out.println("Sample ID: " + sample.getId() + " received..");
        addToQueue(sample);
        System.out.println("Sample ID: " + sample.getId() + " added to queue..");
    }

    @Override
    public void processSample() {
        var sample  = getSamplesQueue().peek();
        System.out.println("Analyzing sample ID: " + sample.getId());
        printResults(sample);
        sendSampleReport(sample.getSamplingStation());
    }


    // Utility
    /**
     * Prints the PredefinedTest results to the analyzed WaterSampleDTO.
     * @param sample    The analyzed WaterSampleDTO.
     */
    private void printResults(WaterSample sample) {
        var testResults = Arrays
                .stream(PredefinedTest.values())
                .filter(test-> test.getCategory().equals(this.labType.name()))
                .map(test -> sample.getTestResults().put(test, result()))
                .collect(Collectors.toList());

        sample.setAnalyzed(true);
    }

    /**
     * Returns the analyzed WaterSampleDTO to the origin station.
     * @param station   The WaterSampleDTO origin station.
     */
    public void sendSampleReport(SamplingStation station) {
        station.receiveSampleReport(removeFromQueue());
    }

    /**
     * Inserts a WaterSampleDTO to the queue.
     * @param sample    The WaterSampleDTO.
     */
    private void addToQueue(WaterSample sample) {
        getSamplesQueue().add(sample);
    }

    /**
     * Removes the WaterSampleDTO from the head of the queue.
     * @return  The WaterSampleDTO at the head of the queue.
     */
    private WaterSample removeFromQueue() {
        return getSamplesQueue().poll();
    }

    /**
     * Dummy method returning random scientific looking numbers.
     * @return  A very scientific looking number representing an imaginary PredefinedTest result.
     */
    private double result() {
        return Math.random();
    }
}
