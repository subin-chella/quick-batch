package com.quick.batch.reader;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.quick.batch.NumberToDollarsResponse;
import com.quick.batch.soap.client.NumberToDollarsClient;
import com.quick.batch.types.read.IntegerToDollarRead;

@Configuration 
public class NumberToDollarReader implements ItemReader<IntegerToDollarRead> {
	private boolean batchJobState = false;
	@Autowired
	NumberToDollarsClient numberToDollarsClient ;
    @Override
    public IntegerToDollarRead read() throws Exception {
    	//NumberToDollarsClient numberToDollarsClient = new NumberToDollarsClient();
    	 if(batchJobState) return null;
    	
    	NumberToDollarsResponse dollars = numberToDollarsClient.getDollars(10);
    	IntegerToDollarRead integerToDollarRead = new IntegerToDollarRead();
    	integerToDollarRead.setId(10);
    	integerToDollarRead.setDollarInString(dollars.getNumberToDollarsResult());
    	batchJobState=true;
        return integerToDollarRead;
    }
}