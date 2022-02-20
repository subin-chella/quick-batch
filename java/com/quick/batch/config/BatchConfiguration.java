package com.quick.batch.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.quick.batch.processor.RecordProcessor;
import com.quick.batch.reader.NumberToDollarReader;
import com.quick.batch.types.read.IntegerToDollarRead;
import com.quick.batch.types.write.IntegerToDollarWrite;

@Configuration

public class BatchConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

	@Bean
	public ItemProcessor<IntegerToDollarRead, IntegerToDollarWrite> processor() {
		return new RecordProcessor();
	}

	@Bean
	public ItemWriter<IntegerToDollarWrite> writer(DataSource dataSource,
			ItemPreparedStatementSetter<IntegerToDollarWrite> setter) {
		JdbcBatchItemWriter<IntegerToDollarWrite> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setItemPreparedStatementSetter(setter);
		writer.setSql("insert into number_to_dollor ( number, doallar_string) values (?,?)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public ItemPreparedStatementSetter<IntegerToDollarWrite> setter() {
		return (item, ps) -> {
			// ps.setLong(1, item.getId());
			ps.setInt(1, item.getNumber());
			ps.setString(2, item.getDoallarString());
		};
	}

	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<IntegerToDollarRead> reader,
			ItemWriter<IntegerToDollarWrite> writer,
			ItemProcessor<IntegerToDollarRead, IntegerToDollarWrite> processor) {
		return stepBuilderFactory.get("step1").<IntegerToDollarRead, IntegerToDollarWrite>chunk(5).reader(reader)
				.processor(processor).writer(writer).build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}