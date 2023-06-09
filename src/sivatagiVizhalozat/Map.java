package sivatagiVizhalozat;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores the different fields and returns a fieldelement by a given type and id
 */
public class Map implements Serializable {
    private static final long serialVersionUID = -1045029823783761155L;

	/**
     * Stores cisterns on the map
     */
    private ArrayList<Cistern> cisterns;

    /**
     * Stores pipes on the map
     */
    private ArrayList<Pipe> pipes;

    /**
     * Stores pumps on the map
     */
    private ArrayList<Pump> pumps;

    /**
     * Stores springs on the map
     */
    private ArrayList<Spring> springs;

    public Map() {
        cisterns = new ArrayList<>();
        pipes = new ArrayList<>();
        springs = new ArrayList<>();
        pumps = new ArrayList<>();
    }

    /**
     * Returns a Cistern object by id
     * @param id The id of the wanted Cistern object
     * @return The Cistern object
     */
    public Cistern getCistern(int id) {
        for (Cistern cistern : cisterns) {
            if(cistern.getId() == id) {
                return cistern;
            }
        }
        return null;
    }

    /**
     * Returns a Pipe object by id
     * @param id The id of the wanted Pipe object
     * @return The Pipe object
     */
    public Pipe getPipe(int id) {
        for (Pipe pipe : pipes) {
            if(pipe.getId() == id) {
                return pipe;
            }
        }
        return null;
    }

    /**
     * Returns a Pump object by id
     * @param id The id of the wanted Pump object
     * @return The Pump object
     */
    public Pump getPump(int id) {
        for (Pump pump : pumps) {
            if(pump.getId() == id) {
                return pump;
            }
        }
        return null;
    }

    /**
     * Returns a Spring object by id
     * @param id The id of the wanted Spring object
     * @return The Spring object
     */
    public Spring getSpring(int id) {
        for (Spring spring : springs) {
            if(spring.getId() == id) {
                return spring;
            }
        }
        return null;
    }
    
    /**
    * Adds a new field element to the corresponding list 
    * @param type The type of the object we want to add to the Map
    * @param field The object we want to add to the Map
    */
    public void addFieldElement(String type, FieldElement field) {
        switch (type) {
            case "Pipe": if(!pipes.contains(field)) pipes.add((Pipe) field); break;
            case "Pump": if(!pumps.contains(field)) pumps.add((Pump) field); break;
            case "Spring": if(!springs.contains(field)) springs.add((Spring) field); break;
            case "Cistern": if(!cisterns.contains(field)) cisterns.add((Cistern) field); break;
            default: return;
        }
    }
    
    
    /**
     * Gets the field element.
     *
     * @param str the str
     * @return the field element
     */
    public FieldElement getFieldElement(String str)
    {
    	String type = str.replaceAll("\\d", "");
    	int id = Integer.parseInt(str.replaceAll("[\\D]", ""));
    	switch(type.toLowerCase())
    	{
    	case"pipe":
    		return getPipe(id);
    	case"pump":
    		return getPump(id);
    	case"spring":
    		return getSpring(id);
    	case"cistern":
    		return getCistern(id);
    	}
		return null;
    }
    
    public ArrayList<FieldElement> getFields() {
    	ArrayList<FieldElement> fields = new ArrayList<>();
    	fields.addAll(pipes);
    	fields.addAll(pumps);
    	fields.addAll(cisterns);
    	fields.addAll(springs);
    	return fields;
    }
    
    public ArrayList<Pump> getPumps() {
    	return pumps;
    }
    
    public ArrayList<Pipe> getPipes() {
    	return pipes;
    }
    public ArrayList<Cistern> getCisterns() {
    	return cisterns;
    }
    public ArrayList<Spring> getSprings() {
    	return springs;
    }

}






