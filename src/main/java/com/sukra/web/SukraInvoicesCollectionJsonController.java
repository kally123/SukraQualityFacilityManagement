package com.sukra.web;
import com.sukra.domain.SukraInvoice;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import com.sukra.service.api.SukraInvoiceService;
import io.springlets.data.domain.GlobalSearch;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = SukraInvoicesCollectionJsonController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = SukraInvoice.class, type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/sukrainvoices", name = "SukraInvoicesCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class SukraInvoicesCollectionJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SukraInvoiceService sukraInvoiceService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param sukraInvoiceService
     */
    @Autowired
    public SukraInvoicesCollectionJsonController(SukraInvoiceService sukraInvoiceService) {
        this.sukraInvoiceService = sukraInvoiceService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return SukraInvoiceService
     */
    public SukraInvoiceService getSukraInvoiceService() {
        return sukraInvoiceService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoiceService
     */
    public void setSukraInvoiceService(SukraInvoiceService sukraInvoiceService) {
        this.sukraInvoiceService = sukraInvoiceService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<SukraInvoice>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<SukraInvoice> sukraInvoices = getSukraInvoiceService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(sukraInvoices);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(SukraInvoicesCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody SukraInvoice sukraInvoice, BindingResult result) {
        if (sukraInvoice.getId() != null || sukraInvoice.getVersion() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        SukraInvoice newSukraInvoice = getSukraInvoiceService().save(sukraInvoice);
        UriComponents showURI = SukraInvoicesItemJsonController.showURI(newSukraInvoice);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoices
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<SukraInvoice> sukraInvoices, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getSukraInvoiceService().save(sukraInvoices);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoices
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<SukraInvoice> sukraInvoices, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getSukraInvoiceService().save(sukraInvoices);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        getSukraInvoiceService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
