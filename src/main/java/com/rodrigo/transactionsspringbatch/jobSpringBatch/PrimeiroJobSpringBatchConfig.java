package com.rodrigo.transactionsspringbatch.jobSpringBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rodrigo.transactionsspringbatch.stepSpringBatch.ImprimeOlaStepConfig;

@EnableBatchProcessing
@Configuration
public class PrimeiroJobSpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job ImprimeOlaJob(Step imprimeOlaStep){
        return jobBuilderFactory
                .get("ImprimeOlaJob")
                .start(imprimeOlaStep)
                .incrementer(new RunIdIncrementer()) // executa o job e adiciona um RunId a cada execucao , add automaticamente um parametro(batch_job_execution_params)
                .build();
    }

}
