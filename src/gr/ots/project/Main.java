package gr.ots.project;

import gr.ots.project.model.SamplingStation;
import gr.ots.project.model.Lab;
import gr.ots.project.model.consts.Category;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        var station = SamplingStation.createStation(38.8951, -77.0364);
        var chemLab = Lab.createLab("Chem Lab", Category.CHEMICAL);


        station.collectSample();
        System.out.println(station.getSample(LocalDate.now())); // expected

        station.sendLatestSample(chemLab);
        System.out.println(station.getSample(LocalDate.now())); // expected

        chemLab.processSample();
        System.out.println(station.getSample(LocalDate.now())); // expected


        System.out.println("-".repeat(100));


        var x = station.getSample(LocalDate.now());
        x.setAnalyzed(false);
        System.out.println(x); // expected
        System.out.println(station.getSample(LocalDate.now())); // expected
        System.out.println(station.getCopyOfRecords().get(0)); // expected
    }
}
