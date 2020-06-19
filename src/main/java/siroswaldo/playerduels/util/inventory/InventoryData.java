package siroswaldo.playerduels.util.inventory;

import java.util.Map;
import java.util.HashMap;

public class InventoryData{

  private final String type;
  private Map<String, Object> data;

  public InventoryData(String type){
    this.type = type;
    data = new HashMap<>();
  }

  /**
   * @return the type
   */
  public String getType() {
	  return type;
  }

  /**
   * @return Un mapa de los datos
   */
  public Map<String, Object> getData() {
  	return data;
  }

}
