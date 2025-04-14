package org.acme.model;

public class System {
    private Level level;
    private Price price;
    private Material material;
    private String size;
    private Usage usage;
    private Traits traits;
    private Description description;
    private Bulk bulk;

    // Getters and Setters
    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }
    
    public Price getPrice() { return price; }
    public void setPrice(Price price) { this.price = price; }
    
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public Usage getUsage() { return usage; }
    public void setUsage(Usage usage) { this.usage = usage; }
    
    public Traits getTraits() { return traits; }
    public void setTraits(Traits traits) { this.traits = traits; }
    
    public Description getDescription() { return description; }
    public void setDescription(Description description) { this.description = description; }
    
    public Bulk getBulk() { return bulk; }
    public void setBulk(Bulk bulk) { this.bulk = bulk; }
}
