package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application implements CommandLineRunner {
        
    @Autowired
    public LedgerHandler ledgerHandler;
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        log.info("Starting rest server");
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    public void run(String... strings) throws Exception{
        log.info("Checking if main ledger exists");  
        ledgerHandler.handleLedger();
    }
}
