package pl.piomin.services.transactions.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private int totalNoOfOrders;
    private int processedNoOfOrders;

    public OrderGroup(String status, int totalNoOfOrders, int processedNoOfOrders) {
        this.status = status;
        this.totalNoOfOrders = totalNoOfOrders;
        this.processedNoOfOrders = processedNoOfOrders;
    }
}