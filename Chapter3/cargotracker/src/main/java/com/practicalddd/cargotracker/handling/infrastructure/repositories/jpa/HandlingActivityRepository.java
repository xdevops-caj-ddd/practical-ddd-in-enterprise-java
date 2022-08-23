package com.practicalddd.cargotracker.handling.infrastructure.repositories.jpa;


import com.practicalddd.cargotracker.handling.domain.model.aggregates.HandlingActivity;
import com.practicalddd.cargotracker.handling.domain.model.valueobjects.HandlingActivityHistory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class HandlingActivityRepository {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    public void store(HandlingActivity handlingActivity) {
        entityManager.persist(handlingActivity);
    }

    /**
     * Get the entire handling history for a Cargo
     * @param bookingId
     * @return
     */
    public HandlingActivityHistory lookupHandlingHistoryOfCargo(String bookingId) {
        return new HandlingActivityHistory(entityManager.createNamedQuery(
                "HandlingActivity.findByBookingId", HandlingActivity.class)
                .setParameter("bookingId", bookingId).getResultList());
    }
}
