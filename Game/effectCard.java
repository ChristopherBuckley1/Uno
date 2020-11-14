package Game;

public class effectCard extends card{
    private String effectType;
    public effectCard(String colour, String effectType)
    {
        super(colour);
        setEffectType(effectType);
    }

    public String getEffectType() {
        return effectType;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }
}
