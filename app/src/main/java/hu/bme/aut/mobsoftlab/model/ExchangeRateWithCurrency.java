package hu.bme.aut.mobsoftlab.model;

import java.util.Objects;
import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;


public class ExchangeRateWithCurrency   {
  
  @SerializedName("from")
  private String from = null;
  
  @SerializedName("to")
  private String to = null;
  
  @SerializedName("rate")
  private BigDecimal rate = null;
  

  
  /**
   **/
  public String getFrom() {
    return from;
  }
  public void setFrom(String from) {
    this.from = from;
  }

  
  /**
   **/
  public String getTo() {
    return to;
  }
  public void setTo(String to) {
    this.to = to;
  }

  
  /**
   **/
  public BigDecimal getRate() {
    return rate;
  }
  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeRateWithCurrency exchangeRateWithCurrency = (ExchangeRateWithCurrency) o;
    return Objects.equals(from, exchangeRateWithCurrency.from) &&
        Objects.equals(to, exchangeRateWithCurrency.to) &&
        Objects.equals(rate, exchangeRateWithCurrency.rate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, rate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeRateWithCurrency {\n");
    
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
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
