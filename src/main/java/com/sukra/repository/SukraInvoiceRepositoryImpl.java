package com.sukra.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import com.sukra.domain.QSukraInvoice;
import com.sukra.domain.SukraInvoice;

import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;

/**
 * = SukraInvoiceRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = SukraInvoiceRepositoryCustom.class)
@Transactional(readOnly = true)
public class SukraInvoiceRepositoryImpl extends QueryDslRepositorySupportExt<SukraInvoice> implements SukraInvoiceRepositoryCustom {

    /**
     * TODO Auto-generated constructor documentation
     */
    SukraInvoiceRepositoryImpl() {
        super(SukraInvoice.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INVOICE_DATE = "invoiceDate";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PURPOSE_OF = "purposeOf";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String SUM_OF_RUPEES = "sumOfRupees";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String SUKRA_NO = "sukraNo";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PAID_TO = "paidTo";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<SukraInvoice> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QSukraInvoice sukraInvoice = QSukraInvoice.sukraInvoice;
        JPQLQuery<SukraInvoice> query = from(sukraInvoice);
		Path<?>[] paths = new Path<?>[] { sukraInvoice.invoiceDate, sukraInvoice.paidTo, sukraInvoice.purposeOf,
				sukraInvoice.sumOfRupees };
        applyGlobalSearch(globalSearch, query, paths);
		AttributeMappingBuilder mapping = buildMapper().map(INVOICE_DATE, sukraInvoice.invoiceDate)
				.map(PAID_TO, sukraInvoice.paidTo).map(PURPOSE_OF, sukraInvoice.purposeOf)
				.map(SUM_OF_RUPEES, sukraInvoice.sumOfRupees);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, sukraInvoice);
    }
}
