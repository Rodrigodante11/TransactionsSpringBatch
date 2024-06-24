package com.rodrigo.transactionsspringbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    /**
     *   Imprementando o primeiro step OLa mundo
     */
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job ImprimeOlaJob(){
        return jobBuilderFactory
                .get("ImprimeOlaJob")
                .start(imprimeOlaStep())
                .build();
    }

    public Step imprimeOlaStep(){
        return stepBuilderFactory
                .get("imprimiOlaStep")
                .tasklet(new Tasklet() {   // para pequenas tarefas

                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Ola Mundo");
                        return RepeatStatus.FINISHED;  // dizer que a tarefa ira terminar
                    }
                }).build();

    }
}
