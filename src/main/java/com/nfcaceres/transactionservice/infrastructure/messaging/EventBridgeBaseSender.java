package com.nfcaceres.transactionservice.infrastructure.messaging;


import com.amazonaws.services.eventbridge.AmazonEventBridge;
import com.amazonaws.services.eventbridge.model.PutEventsRequest;
import com.amazonaws.services.eventbridge.model.PutEventsRequestEntry;
import com.amazonaws.services.eventbridge.model.PutEventsResult;
import com.google.gson.Gson;
import com.nfcaceres.transactionservice.infrastructure.dto.TransactionEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventBridgeBaseSender {

    private final AmazonEventBridge eventBridge;

    @Value("${aws.event-bridge.bus}")
    private String eventBusName;

    @Value("${aws.event-bridge.source}")
    private String eventBusSource;

    protected EventBridgeBaseSender(AmazonEventBridge eventBridge) {
        this.eventBridge = eventBridge;
    }

    public boolean sendEvent(TransactionEventDTO eventData,String deatailType) {
        String json = new Gson().toJson(eventData);
        PutEventsRequestEntry requestEntry = new PutEventsRequestEntry()
                .withEventBusName(eventBusName)
                .withSource(eventBusSource)
                .withDetailType(deatailType)
                .withDetail(json);

        PutEventsRequest request = new PutEventsRequest().withEntries(requestEntry);
        log.info("Publish event to EventBridge with source {}, detail-type: {} this detail {}", eventBusSource,deatailType, json);
        PutEventsResult eventsResult = eventBridge.putEvents(request);
        log.info("Event created {}", eventsResult);
        return (eventsResult.getFailedEntryCount()==0);

    }
}