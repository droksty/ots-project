package gr.ots.project.model;

public interface ILab {

    /**
     * Receives a WaterSampleDTO and adds it to the queue.
     * @param sample    The received WaterSampleDTO.
     */
    void receiveSample(WaterSample sample);


    /**
     * Analyzes the WaterSampleDTO at the head of the queue.
     * and returns the report its origin Station.
     */
    void processSample();

}
