package civ;

public enum ResourceType {
    // Assets
    Gold ("Gold"),
    Happiness ("Happiness"),
    Science("Science"),
    
    //Production
    Stone ("Stone"),
    Ore ("Ore"),
    Sulfur ("Sulfur"),
    Coal("Coal"),
    Lumber ("Lumber"),
    
    // Food
    Wheat ("Wheat"),
    Fish ("Fish"),
    Game ("Game"), 
    Hay ("Hay");
    
    private String name;
    
    private ResourceType(String name) {
         this.name = name;        
    }
    
    public String getName() {
        return name;
        
    }
    
}