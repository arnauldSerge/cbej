package fr.charisma.bvj.rc_bvj;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableBatchProcessing
@Data
public class BatchConfiguration {
	
	
	private final  JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

}
