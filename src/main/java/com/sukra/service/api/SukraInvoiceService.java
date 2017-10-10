package com.sukra.service.api;
import com.sukra.domain.SukraInvoice;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = SukraInvoiceService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = SukraInvoice.class)
public interface SukraInvoiceService extends EntityResolver<SukraInvoice, Long> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return SukraInvoice
     */
    public abstract SukraInvoice findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     */
    public abstract void delete(SukraInvoice sukraInvoice);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<SukraInvoice> save(Iterable<SukraInvoice> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return SukraInvoice
     */
    public abstract SukraInvoice save(SukraInvoice entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return SukraInvoice
     */
    public abstract SukraInvoice findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<SukraInvoice> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<SukraInvoice> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<SukraInvoice> findAll(GlobalSearch globalSearch, Pageable pageable);
}
