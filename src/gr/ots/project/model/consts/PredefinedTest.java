package gr.ots.project.model.consts;

public enum PredefinedTest {

    COLOUR(Category.PHYSICAL, Frequency.WEEKLY, "UTC", Effect.POTENTIALLY_SEVERE, 15d),
    ODOUR(Category.PHYSICAL, Frequency.WEEKLY, "OU", Effect.POTENTIALLY_SEVERE, 1d),
    TASTE(Category.PHYSICAL, Frequency.WEEKLY, "TU", Effect.POTENTIALLY_SEVERE, 1d),
    TURBIDITY(Category.PHYSICAL, Frequency.WEEKLY, "NTU", Effect.POTENTIALLY_SEVERE, 5d),

    ALKALINITY(Category.CHEMICAL, Frequency.QUARTERLY, "pH", Effect.MEDIOCRE, 8.5d),
    CONDUCTIVITY(Category.CHEMICAL, Frequency.QUARTERLY, "μS/cm", Effect.POTENTIALLY_MILD, 2500d),
    HARDNESS(Category.CHEMICAL, Frequency.QUARTERLY, "mg/l", Effect.POTENTIALLY_MEDIOCRE, 500d),
    CHLORIDE(Category.CHEMICAL, Frequency.QUARTERLY, "mg/l", Effect.POTENTIALLY_SEVERE, 250d),

    E_COLI(Category.BACTERIOLOGICAL, Frequency.MONTHLY, "cfu/100ml", Effect.MEDIOCRE, 1d),
    SALMONELLA(Category.BACTERIOLOGICAL, Frequency.MONTHLY, "cfu/100ml", Effect.MEDIOCRE, 1d);


    private final String category;
    private final String frequency;
    private final String unit;
    private final String effect;
    private final Double minLimit;


    PredefinedTest(Enum category, Enum frequency, String unit, Enum effect, Double minLimit) {
        this.category = String.valueOf(category);
        this.frequency = String.valueOf(frequency);
        this.unit = unit;
        this.effect = String.valueOf(effect);
        this.minLimit = minLimit;
    }


    public String getCategory() {
        return category;
    }
    public String getFrequency() {
        return frequency;
    }
    public String getUnit() {
        return unit;
    }
    public String getEffect() {
        return effect;
    }
    public Double getMinLimit() {
        return minLimit;
    }

}
