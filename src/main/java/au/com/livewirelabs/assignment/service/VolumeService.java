package au.com.livewirelabs.assignment.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import au.com.livewirelabs.assignment.entity.VolumeEntity;

/**
 * The Class VolumeService.
 */
public class VolumeService {

  /** The em. */
  @Inject
  private EntityManager em;

  /**
   * Load.
   *
   * @param code
   *          the code
   * @return the volume entity
   */
  public VolumeEntity findByCode(final String code) {
    final List<VolumeEntity> list =
        em.createQuery("SELECT v FROM Volume v WHERE v.code = ?1", VolumeEntity.class)
            .setParameter(1, code).getResultList();
    if (list.isEmpty()) {
      return null;
    }
    return list.get(0);
  }

  /**
   * Find all.
   *
   * @return the list
   */
  public List<VolumeEntity> findAll() {
    TypedQuery<VolumeEntity> query = em.createQuery("FROM Volume", VolumeEntity.class);
    return query.getResultList();
  }

  /**
   * Persist.
   *
   * @param code
   *          the code
   * @param units
   *          the units
   */
  @Transactional
  public void persist(final String code, final Integer units) {
    final VolumeEntity entity = new VolumeEntity(code, units);
    em.persist(entity);
  }

  /**
   * Persist.
   *
   * @param entity
   *          the entity
   */
  @Transactional
  public void merge(final VolumeEntity entity) {
    em.merge(entity);
  }
}
