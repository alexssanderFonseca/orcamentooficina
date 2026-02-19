package br.com.oficina.orcamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class OrcamentoApplication {

    private static final Logger logger = LoggerFactory.getLogger(OrcamentoApplication.class);

    public static void main(String[] args) {
        logger.info("Aplicação iniciada com sucesso.");
        SpringApplication.run(OrcamentoApplication.class, args);
    }

}
