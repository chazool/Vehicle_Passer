package com.chazool.highwayvehiclepasser.driverservice.controller;

import com.chazool.highwayvehiclepasser.driverservice.service.VehicleService;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.DuplicateEntryException;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public Response save(@RequestBody Vehicle vehicle, @RequestHeader Map<String, String> headers) {
        String authorization = headers.get("authorization");
        try {
            return Response.success(vehicleService.save(vehicle, authorization));
        } catch (DuplicateEntryException duplicateEntryException) {
            return Response.fail(duplicateEntryException.getMessage());
        }
    }

    @PutMapping
    public Vehicle update(@RequestBody Vehicle vehicle) {
        return vehicleService.update(vehicle);
    }

    @DeleteMapping
    public Vehicle delete(@PathVariable int id) {
        return vehicleService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Response findById(@PathVariable int id) {
        try {
            return Response.success(vehicleService.findById(id));
        } catch (VehicleNotFoundException vehicleNotFoundException) {
            return Response.fail(vehicleNotFoundException.getMessage());
        }
    }

    @GetMapping
    public List<Vehicle> findByAll() {
        return vehicleService.findByAll();
    }

    @GetMapping(value = "/owner/{ownerId}")
    public List<Vehicle> findByOwnerId(@PathVariable int ownerId) {
        return vehicleService.findByOwnerId(ownerId);
    }


}
