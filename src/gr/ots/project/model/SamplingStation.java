package gr.ots.project.model;

import gr.ots.project.util.ImaginaryDataSource;

import java.time.LocalDate;
import java.util.*;


public class SamplingStation implements IStation {
    private final long id;
    private double latitude;
    private double longitude;
    private final List<WaterSample> stationRecords = new ArrayList<>();

    private SamplingStation() {
        this.id = ImaginaryDataSource.getId();
    }

    public static SamplingStation createStation(double latitude, double longitude) {
        SamplingStation station = new SamplingStation();
        station.latitude = latitude;
        station.longitude = longitude;
        return station;
    }


    // Getters & Setters
    public long getId() {
        return id;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    //
    public double[] getCoordinates() {
        return new double[] {getLatitude(), getLongitude()};
    }

    @Override
    public WaterSample collectSample() {
        WaterSample sample = WaterSample.instantiate(this, LocalDate.now());
        System.out.println("Water sample collected.");
        insertSample(sample);
        return sample;
    }

    @Override
    public void sendLatestSample(Lab lab) {
        var lastPosition = stationRecords.size() - 1;
        var sample = stationRecords.get(lastPosition);
        sample.setSentToLab(true);
        sample.setTestLab(lab);
        updateSample(sample);
        lab.receiveSample(new WaterSample(sample));
    }

    @Override
    public void receiveSampleReport(WaterSample sample) {
        updateSample(sample);
    }

    @Override
    public WaterSample getSample(LocalDate date) {
        for (int i = stationRecords.size() - 1; i >= 0; i--) {
            if (stationRecords.get(i).getDateCollected().equals(date)) {
                var sample = stationRecords.get(i);
                return new WaterSample(sample);
            }
        }
        return null;
    }

    @Override
    public List<WaterSample> getCopyOfRecords() {
        return new ArrayList<>(getStationRecords());
    }

    private List<WaterSample> getStationRecords() {
        return stationRecords;
    }

    @Override
    public void deleteSample(long id) {
        System.out.println("Not implemented yet");
    }


    // Utility
    /**
     * Updates a WaterSample in Station's records.
     * @param sample    The updated WaterSampleDTO.
     */
    private void updateSample(WaterSample sample) {
        stationRecords.set(getSamplePosition(sample.getDateCollected()), sample);
        System.out.println("Sample updated.");
    }

    /**
     * Inserts an instance of the freshly collected water sample data to this Station's records.
     * @param sample    The freshly collected water sample.
     */
    private void insertSample(WaterSample sample) {
        this.stationRecords.add(sample);
    }

    /**
     * Searches Station records for a WaterSample entry position matching a LocalDate.
     * A Station collects a WaterSample only ONCE per day.
     * @param date  The LocalDate date to search for.
     * @return  The position in Station records matching the WaterSample entry with the passed date. -1, if not found.
     */
    private int getSamplePosition(LocalDate date) {
        for (int i = stationRecords.size() - 1; i >= 0; i--) {
            if (stationRecords.get(i).getDateCollected().equals(date)) {
                return i;
            }
        }
        return -1;
    }
}
