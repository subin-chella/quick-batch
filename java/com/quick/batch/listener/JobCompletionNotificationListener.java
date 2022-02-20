package com.quick.batch.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.quick.batch.types.write.IntegerToDollarWrite;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<IntegerToDollarWrite> results = jdbcTemplate.query("SELECT * FROM number_to_dollor", (rs, row) -> {
            	IntegerToDollarWrite writerSO = new IntegerToDollarWrite();
                writerSO.setId(rs.getInt("id"));
                writerSO.setNumber(rs.getInt("number"));
                writerSO.setDoallarString(rs.getString("doallar_string"));
                return writerSO;
            });

            for (IntegerToDollarWrite writerSO : results) {
                System.err.println("Found <" + writerSO + "> in the database.");
            }
        }
    }
}