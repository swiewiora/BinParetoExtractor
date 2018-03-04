/**
 * @author Sebastian Wiewióra
 * @date 24.03.2017
 */

package com.binpareto;

public class ParetoObject {
  private Boolean type;
  private Integer count;
  private Double percentage;
  
  ParetoObject(Boolean type) {
    this.type = type;
    this.count = 0;
  }
  
  public Integer getCount() {
    return count;
  }
  
  public Double getPercentage() {
    return percentage;
  }
  
  public void setPercentage(Double percentage) {
    this.percentage = percentage;
  }
  
  public Boolean getType() {
    return type;
  }
  
  public void incrementCount () {
    this.count++;
  }
}
