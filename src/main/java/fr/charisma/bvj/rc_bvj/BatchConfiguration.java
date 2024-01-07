package fr.charisma.bvj.rc_bvj;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import fr.charisma.bvj.rc_bvj.batch.component.GoogleSheetsReader;
import fr.charisma.bvj.rc_bvj.batch.component.SheetDataProcessor;
import fr.charisma.bvj.rc_bvj.batch.component.SheetDataWriter;
import lombok.Data;

@Configuration
@EnableBatchProcessing
@Data
public class BatchConfiguration {
	
	
	private final  JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	private final JobRepository jobRepository;
	
	private final  JobBuilder jobBuilder;
	private final StepBuilder stepBuilder;
	private final GoogleSheetsReader googleSheetsReader;
	private final SheetDataProcessor sheetDataProcessor;
	private final SheetDataWriter sheetDataWriter;
	private final PlatformTransactionManager platformTransactionManager;
	
	@Bean
    public Step myStep() {
        return new StepBuilder("myStep", jobRepository)
                .<List<Object>, List<Object>>     chunk(10,platformTransactionManager)
                .reader(googleSheetsReader)
                .processor(sheetDataProcessor)
                .writer(sheetDataWriter)
                .build();
    }

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .flow(myStep())
                .end()
                .build();
    }

}
