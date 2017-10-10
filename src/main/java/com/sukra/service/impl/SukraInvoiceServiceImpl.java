package com.sukra.service.impl;
import com.sukra.service.api.SukraInvoiceService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.sukra.domain.SukraInvoice;
import com.sukra.repository.SukraInvoiceRepository;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = SukraInvoiceServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = SukraInvoiceService.class)
@Service
@Transactional(readOnly = true)
public class SukraInvoiceServiceImpl implements SukraInvoiceService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SukraInvoiceRepository sukraInvoiceRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param sukraInvoiceRepository
     */
    @Autowired
    public SukraInvoiceServiceImpl(SukraInvoiceRepository sukraInvoiceRepository) {
        setSukraInvoiceRepository(sukraInvoiceRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return SukraInvoiceRepository
     */
    public SukraInvoiceRepository getSukraInvoiceRepository() {
        return sukraInvoiceRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoiceRepository
     */
    public void setSukraInvoiceRepository(SukraInvoiceRepository sukraInvoiceRepository) {
        this.sukraInvoiceRepository = sukraInvoiceRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     */
    @Transactional
    public void delete(SukraInvoice sukraInvoice) {
        getSukraInvoiceRepository().delete(sukraInvoice);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<SukraInvoice> save(Iterable<SukraInvoice> entities) {
        return getSukraInvoiceRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<SukraInvoice> toDelete = getSukraInvoiceRepository().findAll(ids);
        getSukraInvoiceRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return SukraInvoice
     */
    @Transactional
    public SukraInvoice save(SukraInvoice entity) {
        return getSukraInvoiceRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return SukraInvoice
     */
    public SukraInvoice findOne(Long id) {
        return getSukraInvoiceRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return SukraInvoice
     */
    public SukraInvoice findOneForUpdate(Long id) {
        return getSukraInvoiceRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<SukraInvoice> findAll(Iterable<Long> ids) {
        return getSukraInvoiceRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<SukraInvoice> findAll() {
        return getSukraInvoiceRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getSukraInvoiceRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<SukraInvoice> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getSukraInvoiceRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<SukraInvoice> getEntityType() {
        return SukraInvoice.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
