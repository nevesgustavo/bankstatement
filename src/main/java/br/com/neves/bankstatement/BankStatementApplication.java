package br.com.neves.bankstatement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Bank statement apis", version = "1.0", description = "Apis used for tests purpose, this show the movement history on account"))
public class BankStatementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankStatementApplication.class, args);
    }

}
