package pl.piomin.services.transactions.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import pl.piomin.services.common.model.Order;

@Service
public class TransactionsResultCallback implements ListenableFutureCallback<SendResult<Long, Order>> {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionsResultCallback.class);

    @Override
    public void onFailure(Throwable ex) {
        LOG.error("Error", ex);
    }

    @Override
    public void onSuccess(SendResult<Long, Order> result) {
        if (result != null) {
            LOG.info("Sent({}): {}", result.getProducerRecord().key(), result.getProducerRecord().value());
        }
    }
}