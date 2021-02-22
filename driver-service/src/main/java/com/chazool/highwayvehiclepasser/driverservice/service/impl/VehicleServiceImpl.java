package com.chazool.highwayvehiclepasser.driverservice.service.impl;

import com.chazool.highwayvehiclepasser.driverservice.repository.VehicleRepository;
import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.driverservice.service.EmailSenderService;
import com.chazool.highwayvehiclepasser.driverservice.service.VehicleService;
import com.chazool.highwayvehiclepasser.driverservice.thread.EmailSender;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.exception.DuplicateEntryException;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DriverService driverService;
    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public Vehicle save(Vehicle vehicle, String authorization) throws DuplicateEntryException {

        Optional<Vehicle> optionalVehicle = vehicleRepository
                .findByOwnerIdAndVehicleNo(vehicle.getOwnerId(), vehicle.getVehicleNo());

        if (optionalVehicle.isPresent()) {
            throw new DuplicateEntryException("Vehicle Number is Already Exists");
        } else {
            vehicle.setRegistrationDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
            vehicle = vehicleRepository.save(vehicle);

            Email email = new Email();
            email.setSubject("Vehicle Registration Successfully");
            email.setEmail(driverService.findById(vehicle.getOwnerId()).getEmail());
            email.setSubject("Registration");
            email.setMessage("your Vehicle registration is completed \n Registration No: " + vehicle.getId());

            EmailSender emailSender = new EmailSender(email, emailSenderService, authorization);
            emailSender.start();
            return vehicle;
        }
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle delete(int id) {

        Vehicle vehicle = findById(id);
        vehicle.setActive(false);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle findById(int id) throws VehicleNotFoundException {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isPresent())
            return optionalVehicle.get();
        else
            throw new VehicleNotFoundException("Vehicle Not Found");
    }

    @Override
    public List<Vehicle> findByAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findByOwnerId(int ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }


}
