package com.chtrembl.functions.reserver;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.FixedDelayRetry;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;

/**
 * Azure Functions with Service Bus Trigger.
 */
public class OrdersHandler {
    /**
     * This function will be invoked when a new message is received at the Service Bus Queue and stores it in a blob storage.
     */
    @FunctionName("OrdersHandler")
    @FixedDelayRetry(maxRetryCount = 3, delayInterval = "00:00:10")
    public void saveOrder(
            @ServiceBusQueueTrigger(name = "message", queueName = "orders-queue", connection = "AZURE_SB_QUEUE_CONNECTION_STRING") String message,
            @BlobOutput(name = "payload", path = "orders/{sessionId}.json", connection = "AZURE_STORAGE_CONNECTION_STRING") OutputBinding<String> blobOrder,
            final ExecutionContext context
    ) {
        context.getLogger().info(() -> "OrdersHandler function received a message [%s]".formatted(message));
        context.getLogger().info(message);
        blobOrder.setValue(message);
    }
}
