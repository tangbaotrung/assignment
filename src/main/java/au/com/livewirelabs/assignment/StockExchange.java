package au.com.livewirelabs.assignment;

import java.math.BigDecimal;
import java.util.Map;

import au.com.livewirelabs.assignment.exception.InsufficientUnitsException;
import au.com.livewirelabs.assignment.exception.InvalidCodeException;

/**
 * The Interface StockExchange.
 */
public interface StockExchange {
  /**
   * Buy stock
   */
  void buy(String code, Integer units) throws InsufficientUnitsException, InvalidCodeException;

  /**
   * Sell stock
   */
  void sell(String code, Integer units) throws InvalidCodeException;

  /**
   * Report aggregate volume available for each code
   */
  Map<String, Integer> getOrderBookTotalVolume();

  /**
   * Returns dollar value of trading activity
   */
  BigDecimal getTradingCosts();
}
