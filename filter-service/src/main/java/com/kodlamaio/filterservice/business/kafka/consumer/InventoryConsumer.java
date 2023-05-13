package com.kodlamaio.filterservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.commonpackage.events.inventory.BrandDeletedEvent;
import com.kodlamaio.commonpackage.events.inventory.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.inventory.CarDeletedEvent;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener( // consume edilecek eventin topics bilgilerini vermemiz lazımki hangi producer i consumes edebilmesini bilmesini lazım.
            topics = "car-created",
            groupId = "filter-car-create" // unique olmak zorunda.
    )
    public void consumeCreate(CarCreatedEvent event){
        var filter = mapper.forRequest().map(event, Filter.class);
        service.add(filter);
        log.info("Car created event consumed {}", event);

    }

    @KafkaListener(
            topics = "car-deleted",
            groupId = "car-delete"
    )
    public void consume(CarDeletedEvent event) {
        service.deleteByCarId(event.getCarId());
        log.info("Car deleted event consumed {}", event);
    }

    @KafkaListener(
            topics = "brand-deleted",
            groupId = "brand-delete"
    )
    public void consume(BrandDeletedEvent event) {
        service.deleteAllByBrandId(event.getBrandId());
        log.info("Brand deleted event consumed {}", event);
    }
}
