package au.com.livewirelabs.assignment;

import java.security.SecureRandom;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import au.com.livewirelabs.assignment.exception.InsufficientUnitsException;
import au.com.livewirelabs.assignment.exception.InvalidCodeException;
import au.com.livewirelabs.assignment.service.TradingRateService;
import au.com.livewirelabs.assignment.service.VolumeService;

/**
 * The Class Assignment.
 */
public class Assignment extends AbstractModule {

  /** The Constant LOG. */
  private static final Logger LOG = LoggerFactory.getLogger(Assignment.class);

  /**
   * The main method.
   *
   * @param args
   *          the arguments
   */
  public static void main(final String[] args) {
    final Injector injector =
        Guice.createInjector(new Assignment(), new JpaPersistModule("persistence-unit"));
    injector.getInstance(ApplicationInitializer.class);
    final Assignment assignment = injector.getInstance(Assignment.class);
    if (args.length < 2) {
      throw new IllegalArgumentException("Missing code");
    }
    assignment.code = args[1];
    assignment.trade();
  }

  /** The stock exchange. */
  @Inject
  private StockExchange stockExchange;

  @Inject
  private PersistService PersistService;

  /** The code. */
  private String code;

  /**
   * {@inheritDoc}
   *
   * @see com.google.inject.AbstractModule#configure()
   */
  @Override
  protected void configure() {
    bind(StockExchange.class).to(StockExchangeImpl.class);
    bind(VolumeService.class).in(Singleton.class);
    bind(TradingRateService.class).in(Singleton.class);
  }

  /**
   * Prints the report.
   */
  private void printReport() {
    final Map<String, Integer> map = stockExchange.getOrderBookTotalVolume();
    LOG.info("Code: {}, remaining volume: {}, income of change: {}", code, map.get(code),
        stockExchange.getTradingCosts());
  }

  /**
   * Trade.
   */
  protected void trade() {
    final SecureRandom random = new SecureRandom();
    try {
      final int sellNum = random.nextInt(100);
      LOG.info("Code: {} Sell: {} units", code, sellNum);
      stockExchange.sell(code, sellNum);
    } catch (final InvalidCodeException | IllegalArgumentException e) {
      LOG.error(e.getMessage());
    } catch (final Exception e) {
      LOG.error(e.getMessage(), e);
    }
    printReport();

    try {
      final int buyNum = random.nextInt(100);
      LOG.info("Code: {} Buy: {} units", code, buyNum);
      stockExchange.buy(code, buyNum);
    } catch (final InvalidCodeException | InsufficientUnitsException
        | IllegalArgumentException e) {
      LOG.error(e.getMessage());
    } catch (final Exception e) {
      LOG.error(e.getMessage(), e);
    }
    printReport();

    PersistService.stop();
  }
}
