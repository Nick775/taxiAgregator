package com.example.demo.testTaxiAggregator.connectors;

import com.example.demo.testTaxiAggregator.DTO.BookingConnectorRequest;
import com.example.demo.testTaxiAggregator.DTO.BookingResponse;
import com.example.demo.testTaxiAggregator.DTO.Car;
import com.example.demo.testTaxiAggregator.DTO.CarsConnectorRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SecondConnector implements IConnector {
    RestTemplate restTemplate = new RestTemplate();
    private String aggregatorName = "Second";

    @Override
    @Async
    public CompletableFuture<List<Car>> getCars(String startAddr, String endAddr) {
        List<Car> cars = restTemplate.postForObject("http://localhost:8889/getCars", new CarsConnectorRequest(startAddr, endAddr), ArrayList.class);
        return CompletableFuture.completedFuture(cars);
    }

    @Override
    @Async
    public CompletableFuture<BookingResponse> book(long idCar, String userPhone, String startAddr, String endAddr) {
        BookingResponse result = restTemplate.postForObject("http://localhost:8889/book",  new BookingConnectorRequest(idCar, userPhone, startAddr, endAddr),  BookingResponse.class);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<BookingResponse> refuseBooking(long idCar, String userPhone, String startAddr, String endAddr) {
        BookingResponse result = restTemplate.postForObject("http://localhost:8889/refuseBooking",  new BookingConnectorRequest(idCar, userPhone, startAddr, endAddr),  BookingResponse.class);
        return CompletableFuture.completedFuture(result);
    }

    public String getAggregatorName() {
        return aggregatorName;
    }
}
