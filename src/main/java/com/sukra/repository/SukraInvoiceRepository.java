package com.sukra.repository;
import com.sukra.domain.SukraInvoice;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = SukraInvoiceRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = SukraInvoice.class)
@Transactional(readOnly = true)
public interface SukraInvoiceRepository extends DetachableJpaRepository<SukraInvoice, Long>, SukraInvoiceRepositoryCustom {
}
