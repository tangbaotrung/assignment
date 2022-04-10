package au.com.livewirelabs.assignment;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import au.com.livewirelabs.assignment.entity.VolumeEntity;
import au.com.livewirelabs.assignment.exception.InsufficientUnitsException;
import au.com.livewirelabs.assignment.exception.InvalidCodeException;
import au.com.livewirelabs.assignment.service.TradingActivityService;
import au.com.livewirelabs.assignment.service.TradingRateService;
import au.com.livewirelabs.assignment.service.VolumeService;

/**
 * The Class StockExchangeImpl.
 */
public class StockExchangeImpl implements StockExchange {

  /** The volume service. */
  @Inject
  private VolumeService volumeService;

  /** The trading activity service. */
  @Inject
  private TradingActivityService tradingActivityService;

  /** The trading rate service. */
  @Inject
  private TradingRateService tradingRateService;

  /**
   * {@inheritDoc}
   *
   * @see au.com.livewirelabs.assignment.StockExchange#buy(java.lang.String,
   *      java.lang.Integer)
   */
  @Transactional
  @Override
  public void buy(final String code, final Integer units)
      throws InsufficientUnitsException, InvalidCodeException {
    if (units == null || units.intValue() <= 0) {
      throw new IllegalArgumentException("Units must be positive number");
    }
    Map<String, BigDecimal> tradingCosts = tradingRateService.loadTradingCosts();
    BigDecimal tradingCode = tradingCosts.get(code);
    if (tradingCode == null) {
      throw new InvalidCodeException("Trading code " + code + " is not registered");
    }
    VolumeEntity entity = volumeService.findByCode(code);
    if (entity == null || entity.getUnits() < units.intValue()) {
      throw new InsufficientUnitsException("Insufficient volume for code " + code);
    } else {
      entity.setUnits(entity.getUnits() - units.intValue());
      volumeService.merge(entity);
    }
    tradingActivityService.save(code, "BUY", units, tradingCode);
  }

  /**
   * {@inheritDoc}
   *
   * @see au.com.livewirelabs.assignment.StockExchange#getOrderBookTotalVolume()
   */
  @Override
  public Map<String, Integer> getOrderBookTotalVolume() {
    final List<VolumeEntity> list = volumeService.findAll();
    final Map<String, Integer> map = new LinkedHashMap<>(list.size());
    list.forEach(e -> map.put(e.getCode(), e.getUnits()));
    return map;
  }

  /**
   * {@inheritDoc}
   *
   * @see au.com.livewirelabs.assignment.StockExchange#getTradingCosts()
   */
  @Override
  public BigDecimal getTradingCosts() {
    BigDecimal countCosts = tradingActivityService.countCosts();
    if (countCosts == null) {
      countCosts = BigDecimal.ZERO;
    }
    return countCosts;
  }

  /**
   * {@inheritDoc}
   *
   * @see au.com.livewirelabs.assignment.StockExchange#sell(java.lang.String,
   *      java.lang.Integer)
   */
  @Transactional
  @Override
  public void sell(final String code, final Integer units) throws InvalidCodeException {
    if (units == null || units.intValue() <= 0) {
      throw new IllegalArgumentException("Units must be positive number");
    }
    Map<String, BigDecimal> tradingCosts = tradingRateService.loadTradingCosts();
    BigDecimal tradingCode = tradingCosts.get(code);
    if (tradingCode == null) {
      throw new InvalidCodeException("Trading code " + code + " is not registered");
    }
    VolumeEntity entity = volumeService.findByCode(code);
    if (entity == null) {
      volumeService.persist(code, units);
    } else {
      entity.setUnits(entity.getUnits() + units.intValue());
      volumeService.merge(entity);
    }
    tradingActivityService.save(code, "SELL", units, tradingCode);
  }

}
