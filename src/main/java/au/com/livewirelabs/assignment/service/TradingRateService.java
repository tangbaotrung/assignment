package au.com.livewirelabs.assignment.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;

import au.com.livewirelabs.assignment.entity.TradingRateEntity;

/**
 * The Class TradingRateService.
 */
public class TradingRateService {
  /** The em. */
  @Inject
  private EntityManager em;

  /**
   * Load trading costs.
   *
   * @return the map
   */
  public Map<String, BigDecimal> loadTradingCosts() {
    final TypedQuery<TradingRateEntity> query =
        em.createQuery("FROM TradingRate", TradingRateEntity.class);
    final Map<String, BigDecimal> map = query.getResultStream()
        .collect(Collectors.toMap(TradingRateEntity::getCode, TradingRateEntity::getCost));
    return map;
  }

  /**
   * Save or update.
   *
   * @param code
   *          the code
   * @param cost
   *          the cost
   */
  public void saveOrUpdate(final String code, final BigDecimal cost) {
    em.merge(new TradingRateEntity(code, cost));
  }
}
