package com.rodrigo.transactionsspringbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class ParImparBatchConfig {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parImparBatchJob() {
        return jobBuilderFactory.get("imprimeParImparJob")
                .start(imprimeParImparStep())
                .incrementer(new RunIdIncrementer()).build();
    }

    public Step imprimeParImparStep(){
        return stepBuilderFactory
                .get("imprimeParImparStep")
                .<Integer, String>chunk(1)   // vai ler um INteger e escrever uma String , do tamanho 1 (commitInterval)
                .reader(contaAteDezReader()) // leitor recebe uma colecao
                .processor(parOuImparProcessor()) // o processador recebe um item dessa colecao de cada vez
                .writer(imprimeWriter()) //  e o leitor recebe a colecao processada
                .build();
    }

    public IteratorItemReader<Integer> contaAteDezReader(){
        List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<Integer>(numerosDeUmAteDez.iterator());

    }

    public FunctionItemProcessor<Integer, String> parOuImparProcessor(){
        return new FunctionItemProcessor<Integer, String>(
                item -> item % 2 ==0 ?
                        String.format("item %s Par", item):
                        String.format("item %s Impar", item));


    }

    public ItemWriter<String> imprimeWriter(){
        return itens -> itens.forEach(System.out::println);
    }
}
