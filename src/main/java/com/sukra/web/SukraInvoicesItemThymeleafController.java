package com.sukra.web;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

import com.sukra.domain.SukraInvoice;
import com.sukra.service.api.SukraInvoiceService;
import com.sukra.web.reports.GeneratePdf;

import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;

/**
 * = SukraInvoicesItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = SukraInvoice.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/sukrainvoices/{sukraInvoice}", name = "SukraInvoicesItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class SukraInvoicesItemThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<SukraInvoicesItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SukraInvoiceService sukraInvoiceService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param sukraInvoiceService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public SukraInvoicesItemThymeleafController(SukraInvoiceService sukraInvoiceService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setSukraInvoiceService(sukraInvoiceService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(SukraInvoicesItemThymeleafController.class));
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
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<SukraInvoicesItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<SukraInvoicesItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return SukraInvoice
     */
    @ModelAttribute
    public SukraInvoice getSukraInvoice(@PathVariable("sukraInvoice") Long id, Locale locale, HttpMethod method) {
        SukraInvoice sukraInvoice = null;
        if (HttpMethod.PUT.equals(method)) {
            sukraInvoice = sukraInvoiceService.findOneForUpdate(id);
        } else {
            sukraInvoice = sukraInvoiceService.findOne(id);
        }
        if (sukraInvoice == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "SukraInvoice", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
		GeneratePdf.getInstance().generatePdf(sukraInvoice, "sukra_invoice_template.pdf",
				sukraInvoice.getPaidTo().replaceAll(" ", "") + "_Invoice_" + LocalDate.now() + ".pdf");
        return sukraInvoice;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute SukraInvoice sukraInvoice, Model model) {
        model.addAttribute("sukraInvoice", sukraInvoice);
        return new ModelAndView("sukrainvoices/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute SukraInvoice sukraInvoice, Model model) {
        model.addAttribute("sukraInvoice", sukraInvoice);
        return new ModelAndView("sukrainvoices/showInline :: inline-content");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dataBinder
     */
    @InitBinder("sukraInvoice")
    public void initSukraInvoiceBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("invoiceDate_date_format", "dd/MM/yyyy");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute SukraInvoice sukraInvoice, Model model) {
        populateForm(model);
        model.addAttribute("sukraInvoice", sukraInvoice);
        return new ModelAndView("sukrainvoices/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @param version
     * @param concurrencyControl
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute SukraInvoice sukraInvoice, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, BindingResult result, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView("sukrainvoices/edit");
        }
        // Concurrency control
        SukraInvoice existingSukraInvoice = getSukraInvoiceService().findOne(sukraInvoice.getId());
        if (sukraInvoice.getVersion() != existingSukraInvoice.getVersion() && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            model.addAttribute("sukraInvoice", sukraInvoice);
            model.addAttribute("concurrency", true);
            return new ModelAndView("sukrainvoices/edit");
        } else if (sukraInvoice.getVersion() != existingSukraInvoice.getVersion() && "discard".equals(concurrencyControl)) {
            populateForm(model);
            model.addAttribute("sukraInvoice", existingSukraInvoice);
            model.addAttribute("concurrency", false);
            return new ModelAndView("sukrainvoices/edit");
        } else if (sukraInvoice.getVersion() != existingSukraInvoice.getVersion() && "apply".equals(concurrencyControl)) {
            // Update the version field to be able to override the existing values
            sukraInvoice.setVersion(existingSukraInvoice.getVersion());
        }
        SukraInvoice savedSukraInvoice = getSukraInvoiceService().save(sukraInvoice);
        UriComponents showURI = getItemLink().to(SukraInvoicesItemThymeleafLinkFactory.SHOW).with("sukraInvoice", savedSukraInvoice.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sukraInvoice
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute SukraInvoice sukraInvoice) {
        getSukraInvoiceService().delete(sukraInvoice);
        return ResponseEntity.ok().build();
    }
}
