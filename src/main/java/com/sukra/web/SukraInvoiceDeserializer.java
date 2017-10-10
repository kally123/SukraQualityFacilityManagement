package com.sukra.web;
import com.sukra.domain.SukraInvoice;
import com.sukra.service.api.SukraInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = SukraInvoiceDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = SukraInvoice.class)
@JsonComponent
public class SukraInvoiceDeserializer extends JsonObjectDeserializer<SukraInvoice> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SukraInvoiceService sukraInvoiceService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param sukraInvoiceService
     * @param conversionService
     */
    @Autowired
    public SukraInvoiceDeserializer(@Lazy SukraInvoiceService sukraInvoiceService, ConversionService conversionService) {
        this.sukraInvoiceService = sukraInvoiceService;
        this.conversionService = conversionService;
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
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return SukraInvoice
     * @throws IOException
     */
    public SukraInvoice deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        SukraInvoice sukraInvoice = sukraInvoiceService.findOne(id);
        if (sukraInvoice == null) {
            throw new NotFoundException("SukraInvoice not found");
        }
        return sukraInvoice;
    }
}
