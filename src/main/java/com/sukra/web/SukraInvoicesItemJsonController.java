package com.sukra.web;
import com.sukra.domain.SukraInvoice;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import com.sukra.service.api.SukraInvoiceService;
import io.springlets.web.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = SukraInvoicesItemJsonController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = SukraInvoice.class, type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/sukrainvoices/{sukraInvoice}", name = "SukraInvoicesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class SukraInvoicesItemJsonController {

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
    public SukraInvoicesItemJsonController(SukraInvoiceService sukraInvoiceService) {
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
     * @param id
     * @return SukraInvoice
     */
    @ModelAttribute
    public SukraInvoice getSukraInvoice(@PathVariable("sukraInvoice") Long id) {
        SukraInvoice sukraInvoice = sukraInvoiceService.findOne(id);
        if (sukraInvoice == null) {
            throw new NotFoundException(String.format("SukraInvoice with identifier '%s' not found", id));
        }
        return sukraInvoice;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute SukraInvoice sukraInvoice) {
        return ResponseEntity.ok(sukraInvoice);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @return UriComponents
     */
    public static UriComponents showURI(SukraInvoice sukraInvoice) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(SukraInvoicesItemJsonController.class).show(sukraInvoice)).buildAndExpand(sukraInvoice.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedSukraInvoice
     * @param sukraInvoice
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute SukraInvoice storedSukraInvoice, @Valid @RequestBody SukraInvoice sukraInvoice, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        sukraInvoice.setId(storedSukraInvoice.getId());
        getSukraInvoiceService().save(sukraInvoice);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute SukraInvoice sukraInvoice) {
        getSukraInvoiceService().delete(sukraInvoice);
        return ResponseEntity.ok().build();
    }
}
