package com.learnhood.springbatchheadstart.config;

import com.learnhood.springbatchheadstart.model.Policy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Policy>  reader(){
        FlatFileItemReader<Policy> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("demodata.csv"));
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    private LineMapper<Policy> getLineMapper() {
        DefaultLineMapper<Policy> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] {"policyID", "statecode", "county", "eq_site_limit", "hu_site_limit", "fl_site_limit", "fr_site_limit", "tiv_2011", "tiv_2012", "eq_site_deductible", "hu_site_deductible", "fl_site_deductible", "fr_site_deductible", "point_latitude", "point_longitude", "line", "construction", "point_granularity"});
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17});

        BeanWrapperFieldSetMapper<Policy> fieldSetMapper = new BeanWrapperFieldSetMapper<Policy>();
        fieldSetMapper.setTargetType(Policy.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    public PolicyItemProcessor processor(){
        return new PolicyItemProcessor();
    }

    @Bean
    public PolicyItemWriter writer(){
        return new PolicyItemWriter();
    }

    @Bean
    public Job importPolicies(){
        return this.jobBuilderFactory.get("import-policy-job")
                .incrementer(new RunIdIncrementer())
                .start(getStep())
                .build();
    }
    @Bean
    public Step getStep() {
        return this.stepBuilderFactory.get("import-step")
                .<Policy, Policy>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }


}
