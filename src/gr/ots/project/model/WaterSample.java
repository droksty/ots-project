package gr.ots.project.model;

import gr.ots.project.model.consts.PredefinedTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WaterSample {
    private long id;
    private LocalDate dateCollected; // Έστω ότι συλλέγεται μόνο ένα δείγμα τη μέρα.
    private SamplingStation samplingStation;
    private boolean sentToLab;
    private Lab testLab; // Έστω ότι ένα δείγμα αποστέλλεται μόνο σε ένα εργαστήριο, μία φορά.
    private boolean analyzed;
    private Map<PredefinedTest, Double> testResults = new HashMap<>();

    private WaterSample() {}

    /**
     * copy constructor
     * @param sample
     */
    public WaterSample(WaterSample sample) {
        this.id = sample.getId();
        this.dateCollected = sample.getDateCollected();
        this.samplingStation = sample.getSamplingStation();
        this.sentToLab = sample.isSentToLab();
        this.testLab = sample.testLab;
        this.analyzed = sample.isAnalyzed();
        this.testResults = new HashMap<>(sample.getTestResults());
    }

    public static WaterSample instantiate(SamplingStation station, LocalDate date) {
        WaterSample sample = new WaterSample();
        sample.setDateCollected(date);
        sample.setSamplingStation(station);
        sample.setId(sample.getSamplingStation().hashCode() + sample.getDateCollected().hashCode());
        return sample;
    }


    // Getters & Setters
    public LocalDate getDateCollected() {
        return dateCollected;
    }
    private void setDateCollected(LocalDate dateCollected) {
        this.dateCollected = dateCollected;
    }
    public SamplingStation getSamplingStation() {
        return samplingStation;
    }
    private void setSamplingStation(SamplingStation samplingStation) {
        this.samplingStation = samplingStation;
    }
    public long getId() {
        return id;
    }
    private void setId(long id) {
        this.id = id;
    }
    public boolean isSentToLab() {
        return sentToLab;
    }
    public void setSentToLab(boolean sentToLab) {
        this.sentToLab = sentToLab;
    }
    public Lab getTestLab() {
        return testLab;
    }
    public void setTestLab(Lab testLab) {
        this.testLab = testLab;
    }
    public boolean isAnalyzed() {
        return analyzed;
    }
    public void setAnalyzed(boolean analyzed) {
        this.analyzed = analyzed;
    }
    public Map<PredefinedTest, Double> getTestResults() {
        return testResults;
    }


    @Override
    public String toString() {
        return  "Sample ID: " + getId() +
                ", collected on: " + getDateCollected() +
                " from Station ID: " + getSamplingStation().getId() +
                "\nSample sent for analysis: " + isSentToLab() +
                ", to: " + (getTestLab() != null ? getTestLab().getTitle() : "N/A")  +
                "\nSample analyzed: " + isAnalyzed() +
                "\nResults: " + getTestResults();
    }
}
