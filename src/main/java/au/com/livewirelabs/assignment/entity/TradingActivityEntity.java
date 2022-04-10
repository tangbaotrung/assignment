package au.com.livewirelabs.assignment.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class TradingActivityEntity.
 */
@Entity(name = "TradingActivity")
@Table(name = "se_trading_activity")
public class TradingActivityEntity {
  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trading_activity_id")
  private Integer id;

  /** The code. */
  @Column(name = "trading_activity_code", unique = true, nullable = false)
  private String code;

  /** The action. */
  @Column(name = "trading_activity_action", unique = true, nullable = false)
  private String action;

  /** The units. */
  @Column(name = "trading_activity_units", nullable = false)
  private int units;

  /** The costs. */
  @Column(name = "trading_activity_costs", nullable = false)
  private BigDecimal costs;

  /**
   * Instantiates a new trading activity entity.
   */
  public TradingActivityEntity() {
    super();
  }

  /**
   * Instantiates a new trading activity entity.
   *
   * @param code
   *          the code
   * @param action
   *          the action
   * @param units
   *          the units
   * @param costs
   *          the costs
   */
  public TradingActivityEntity(String code, String action, int units, BigDecimal costs) {
    super();
    this.code = code;
    this.action = action;
    this.units = units;
    this.costs = costs;
  }

  /**
   * Gets the action.
   *
   * @return the action
   */
  public String getAction() {
    return action;
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
   * Gets the costs.
   *
   * @return the costs
   */
  public BigDecimal getCosts() {
    return costs;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Gets the units.
   *
   * @return the units
   */
  public int getUnits() {
    return units;
  }

  /**
   * Sets the action.
   *
   * @param action
   *          the new action
   */
  public void setAction(final String action) {
    this.action = action;
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
   * Sets the costs.
   *
   * @param costs
   *          the new costs
   */
  public void setCosts(final BigDecimal costs) {
    this.costs = costs;
  }

  /**
   * Sets the id.
   *
   * @param id
   *          the new id
   */
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Sets the units.
   *
   * @param units
   *          the new units
   */
  public void setUnits(final int units) {
    this.units = units;
  }
}
