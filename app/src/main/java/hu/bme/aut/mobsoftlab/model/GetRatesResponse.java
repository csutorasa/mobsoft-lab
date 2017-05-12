package hu.bme.aut.mobsoftlab.model;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class GetRatesResponse   {
  
  @SerializedName("rates")
  private List<ExchangeRateWithCurrency> rates = new ArrayList<ExchangeRateWithCurrency>();

  public List<ExchangeRateWithCurrency> getRates() {
    return rates;
  }
  public void setRates(List<ExchangeRateWithCurrency> rates) {
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
    GetRatesResponse getRatesResponse = (GetRatesResponse) o;
    return Objects.equals(rates, getRatesResponse.rates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetRatesResponse {\n");
    
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
