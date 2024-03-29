package pl.piomin.services.accounts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import pl.piomin.services.accounts.domain.Account;
import pl.piomin.services.accounts.listener.TransactionsListener;
import pl.piomin.services.accounts.repository.AccountRepository;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableKafka
public class AccountsService {

    public static void main(String[] args) {
        SpringApplication.run(AccountsService.class, args);
    }

    private static final Logger LOG = LoggerFactory.getLogger(TransactionsListener.class);

    Random r = new Random();

    @Autowired
    AccountRepository repository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            repository.save(new Account(ThreadLocalRandom.current().nextInt(1000, 2000)));
        }
    }
}