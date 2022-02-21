package com.quick.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.quick.batch.types.read.IntegerToDollarRead;
import com.quick.batch.types.write.IntegerToDollarWrite;

public class RecordProcessor implements ItemProcessor<IntegerToDollarRead, IntegerToDollarWrite> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordProcessor.class);

    @Override
    public IntegerToDollarWrite process(IntegerToDollarRead item) throws Exception {
        LOGGER.info("Processing Record: {}", item);
        IntegerToDollarWrite writer = new IntegerToDollarWrite();
        writer.setNumber(item.getId());
        writer.setDoallarString(item.getDollarInString());
        
        System.err.println("writer "+writer);
        return writer;
    }
}