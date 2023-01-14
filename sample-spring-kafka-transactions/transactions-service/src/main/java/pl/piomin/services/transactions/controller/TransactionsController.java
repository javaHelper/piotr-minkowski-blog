package pl.piomin.services.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.services.transactions.domain.OrderGroup;
import pl.piomin.services.transactions.producer.TransactionsProducer;
import pl.piomin.services.transactions.repository.OrderGroupRepository;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionsProducer producer;

    @Autowired
    OrderGroupRepository repository;

    @PostMapping
    public void sendTransaction(@RequestBody boolean error) throws InterruptedException {
        producer.sendOrderGroup(error);
    }

    @GetMapping
    public List<OrderGroup> findAll() {
        return (List<OrderGroup>) repository.findAll();
    }
}