package fr.charisma.bvj.rc_bvj.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import fr.charisma.bvj.rc_bvj.batch.component.GoogleSheetDataReader;
import fr.charisma.bvj.rc_bvj.batch.component.SheetDataProcessor;
import fr.charisma.bvj.rc_bvj.batch.component.GoogleSheetDataWriter;
import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfiguration extends DefaultBatchConfiguration{
	
	private final GoogleSheetDataReader googleSheetsReader;
	private final GoogleSheetDataWriter sheetDataWriter;

	private final SheetDataProcessor sheetDataProcessor;
	
	@Bean
    public Step myStep(JobRepository jobRepository, PlatformTransactionManager  plateforManager) {
        return new StepBuilder("myStep", jobRepository)
                .<List<Object>, List<Object>>     chunk(10,plateforManager)
                .reader(googleSheetsReader)
                .processor(sheetDataProcessor)
                .writer(sheetDataWriter)
                .build();
    }

    @Bean
    public Job myJob(JobRepository jobRepository, PlatformTransactionManager plateforManager) {
        return new JobBuilder("myJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(myStep(jobRepository, plateforManager))
                .end()
                .build();
    }

}
