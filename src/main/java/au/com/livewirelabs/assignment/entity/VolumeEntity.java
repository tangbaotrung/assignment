package au.com.livewirelabs.assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class StockExchangeVolumeEntity.
 */
@Entity(name = "Volume")
@Table(name = "se_volume")
public class VolumeEntity implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 5646444940609184718L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "volume_id")
  private Integer id;

  /** The code. */
  @Column(name = "volume_code", unique = true, nullable = false)
  private String code;

  /** The units. */
  @Column(name = "volume_units", nullable = false)
  private int units;

  /**
   * Instantiates a new volume entity.
   */
  public VolumeEntity() {
    super();
  }

  /**
   * Instantiates a new volume entity.
   *
   * @param code
   *          the code
   * @param units
   *          the units
   */
  public VolumeEntity(final String code, final Integer units) {
    super();
    this.code = code;
    this.units = units;
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
   * Sets the code.
   *
   * @param code
   *          the new code
   */
  public void setCode(final String code) {
    this.code = code;
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
