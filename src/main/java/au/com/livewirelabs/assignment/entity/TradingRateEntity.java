package au.com.livewirelabs.assignment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class TradingRateEntity.
 */
@Entity(name = "TradingRate")
@Table(name = "se_trading_rate")
public class TradingRateEntity implements Serializable {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1397785265327319499L;

  /** The code. */
  @Id
  @Column(name = "trading_rate_code")
  private String code;

  /** The costs. */
  @Column(name = "trading_rate_cost", nullable = false)
  private BigDecimal cost;

  /**
   * Instantiates a new trading rate entity.
   */
  public TradingRateEntity() {

  }

  /**
   * Instantiates a new trading rate entity.
   *
   * @param code
   *          the code
   * @param cost
   *          the cost
   */
  public TradingRateEntity(final String code, final BigDecimal cost) {
    super();
    this.code = code;
    this.cost = cost;
  }

  /**
   * Gets the code.
   *
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * Gets the cost.
   *
   * @return the cost
   */
  public BigDecimal getCost() {
    return cost;
  }

  /**
   * Sets the code.
   *
   * @param code
   *          the new code
   */
  public void setCode(final String code) {
    this.code = code;
  }

  /**
   * Sets the cost.
   *
   * @param cost
   *          the new cost
   */
  public void setCost(final BigDecimal cost) {
    this.cost = cost;
  }

}
