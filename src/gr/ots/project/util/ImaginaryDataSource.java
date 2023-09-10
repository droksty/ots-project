package gr.ots.project.util;

public final class ImaginaryDataSource {
    private static int idCount = 0;

    private ImaginaryDataSource() {}

    public static long getId() {
        idCount++;
        return idCount;
    }

}
