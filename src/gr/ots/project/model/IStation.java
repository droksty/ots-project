package gr.ots.project.model;

import java.time.LocalDate;
import java.util.List;

public interface IStation {

    /**
     * Collects and records a WaterSample.
     * @return  The collected WaterSample.
     */
    WaterSample collectSample();


    /**
     * Sends the last WaterSample for analysis.
     * @param lab   The Lab to send the WaterSample to.
     */
    void sendLatestSample(Lab lab);


    /**
     * Receives an analyzed WaterSampleDTO report and updates Station records.
     * @param sample    The received analyzed WaterSampleDTO
     */
    void receiveSampleReport(WaterSample sample);


    /**
     * Retrieves a WaterSample entry from this station's records.
     * @param date The date the WaterSample was collected.
     * @return A deep copy of the WaterSample matching the passed date. Null, otherwise.
     */
    WaterSample getSample(LocalDate date);


    /**
     * Retrieves a copy of this Station's records.
     * @return  A copy of this Station's records.
     */
    List<WaterSample> getCopyOfRecords();


    /**
     * Removes a WaterSample entry from this station's records.
     * @param id  The id of the WaterSample to be removed.
     */
    void deleteSample(long id);

}
