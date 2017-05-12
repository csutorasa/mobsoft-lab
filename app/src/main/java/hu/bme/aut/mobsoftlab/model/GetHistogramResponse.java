package hu.bme.aut.mobsoftlab.model;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class GetHistogramResponse   {
  
  @SerializedName("rates")
  private List<RateWithDate> rates = new ArrayList<RateWithDate>();

  public List<RateWithDate> getRates() {
    return rates;
  }
  public void setRates(List<RateWithDate> rates) {
    this.rates = rates;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetHistogramResponse getHistogramResponse = (GetHistogramResponse) o;
    return Objects.equals(rates, getHistogramResponse.rates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetHistogramResponse {\n");
    
    sb.append("    rates: ").append(toIndentedString(rates)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
