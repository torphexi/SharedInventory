package org.acme.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PathfinderItem {
    @JsonProperty("_id")
    private String id;
    private String img;
    private String name;
    private ItemSystem system;
    
    // Inner class for system properties
    public static class ItemSystem {
        private Bulk bulk;
        private String baseItem;
        private String containerId;
        private Description description;
        private int hardness;
        private HP hp;
        private Level level;
        private Material material;
        private Price price;
        
        public static class Bulk {
            private double value;
            public double getValue() { return value; }
            public void setValue(double value) { this.value = value; }
        }
        
        public static class Description {
            private String value;
            public String getValue() { return value; }
            public void setValue(String value) { this.value = value; }
        }
        
        public static class HP {
            private int max;
            private int value;
            public int getMax() { return max; }
            public void setMax(int max) { this.max = max; }
            public int getValue() { return value; }
            public void setValue(int value) { this.value = value; }
        }
        
        public static class Level {
            private int value;
            public int getValue() { return value; }
            public void setValue(int value) { this.value = value; }
        }
        
        public static class Material {
            private String grade;
            private String type;
            public String getGrade() { return grade; }
            public void setGrade(String grade) { this.grade = grade; }
            public String getType() { return type; }
            public void setType(String type) { this.type = type; }
        }
        
        public static class Price {
            private Value value;
            public Value getValue() { return value; }
            public void setValue(Value value) { this.value = value; }
            
            public static class Value {
                private int gp;
                public int getGp() { return gp; }
                public void setGp(int gp) { this.gp = gp; }
            }
        }
        
        // Getters and Setters
        public Bulk getBulk() { return bulk; }
        public void setBulk(Bulk bulk) { this.bulk = bulk; }
        
        public String getBaseItem() { return baseItem; }
        public void setBaseItem(String baseItem) { this.baseItem = baseItem; }
        
        public String getContainerId() { return containerId; }
        public void setContainerId(String containerId) { this.containerId = containerId; }
        
        public Description getDescription() { return description; }
        public void setDescription(Description description) { this.description = description; }
        
        public int getHardness() { return hardness; }
        public void setHardness(int hardness) { this.hardness = hardness; }
        
        public HP getHp() { return hp; }
        public void setHp(HP hp) { this.hp = hp; }
        
        public Level getLevel() { return level; }
        public void setLevel(Level level) { this.level = level; }
        
        public Material getMaterial() { return material; }
        public void setMaterial(Material material) { this.material = material; }
        
        public Price getPrice() { return price; }
        public void setPrice(Price price) { this.price = price; }
    }

    // Getters and Setters for main class
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public ItemSystem getSystem() { return system; }
    public void setSystem(ItemSystem system) { this.system = system; }
}
