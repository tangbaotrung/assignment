package au.com.livewirelabs.assignment;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

/**
 * The Class ApplicationInitializer.
 */
public class ApplicationInitializer {

  /**
   * Instantiates a new application initializer.
   *
   * @param service
   *          the service
   */
  @Inject
  ApplicationInitializer(final PersistService service) {
    service.start();
  }
}
