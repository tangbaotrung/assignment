package au.com.livewirelabs.assignment.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

import au.com.livewirelabs.assignment.entity.TradingActivityEntity;

public class TradingActivityService {
  /** The em. */
  @Inject
  private EntityManager em;

  /**
   * Count costs.
   *
   * @return the big decimal
   */
  public BigDecimal countCosts() {
    final BigDecimal result =
        em.createQuery("SELECT SUM(e.costs) FROM TradingActivity e", BigDecimal.class)
            .getSingleResult();
    return result;
  }

  /**
   * Save.
   *
   * @param code
   *          the code
   * @param action
   *          the action
   * @param units
   *          the units
   * @param cost
   *          the cost
   */
  public void save(String code, String action, int units, BigDecimal cost) {
    em.persist(new TradingActivityEntity(code, action, units, cost));
  }
}
