package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.example.oneannotatedclass.Example;
import com.alltheducks.javamodeltoclosure.model.ConvertedField;
import com.alltheducks.javamodeltoclosure.model.ConvertedModel;
import com.alltheducks.javamodeltoclosure.model.ConvertedType;
import com.alltheducks.javamodeltoclosure.service.ConvertedFieldService;
import com.alltheducks.javamodeltoclosure.service.ConvertedModelService;
import com.alltheducks.javamodeltoclosure.translator.SimplePackageTranslator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConvertedModelServiceTest {

    ConvertedModelService convertedModelService;
    ConvertedFieldService convertedFieldService;

    @Before
    public void setUp() {
        convertedFieldService = mock(ConvertedFieldService.class);

        convertedModelService = new ConvertedModelService();
        convertedModelService.setConvertedFieldService(convertedFieldService);
        convertedModelService.setPackageTranslator(new SimplePackageTranslator());
    }

    @Test
    public void testGetConvertedModel() throws Exception {
        ConvertedType convertedType = new ConvertedType();
        convertedType.setName("String");

        ConvertedField convertedField = new ConvertedField();
        convertedField.setName("aString");
        convertedField.setType(convertedType);

        Set<ConvertedField> convertedFields = new HashSet<ConvertedField>();
        convertedFields.add(convertedField);

        when(convertedFieldService.getAllConvertedFields(Example.class)).thenReturn(convertedFields);

        ConvertedModel convertedModel = convertedModelService.getConvertedModel(Example.class);
        assertEquals("com.alltheducks.javamodeltoclosure.example.oneannotatedclass.Example", convertedModel.getName());
        assertEquals(convertedFields, convertedModel.getConvertedFields());
    }
}
