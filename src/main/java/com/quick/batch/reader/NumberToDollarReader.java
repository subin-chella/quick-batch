package com.quick.batch.reader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.quick.batch.NumberToDollarsResponse;
import com.quick.batch.soap.client.NumberToDollarsClient;
import com.quick.batch.types.read.IntegerToDollarRead;


@Configuration 
@StepScope
public class NumberToDollarReader implements ItemReader<IntegerToDollarRead> {
	
	private boolean batchJobState = false;
	private int number ;
	
	@Autowired
	NumberToDollarsClient numberToDollarsClient ;
	
	
	@Value("#{jobParameters['number']}")
	  public void setFileName(final long number) {
		this.number= (int) number;
	  }
	
    @Override
    public IntegerToDollarRead read() throws Exception {
    	
    	 if(batchJobState) return null;
    	
    	NumberToDollarsResponse dollars = numberToDollarsClient.getDollars(number);
    	IntegerToDollarRead integerToDollarRead = new IntegerToDollarRead();
    	integerToDollarRead.setId(number);
    	integerToDollarRead.setDollarInString(dollars.getNumberToDollarsResult());
    	batchJobState=true;
        return integerToDollarRead;
    }
}