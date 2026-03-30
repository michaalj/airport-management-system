package org.example.project.service;

import org.example.project.model.Passenger;
import org.example.project.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Transactional(readOnly = true)
    public Optional<Passenger> findByIdentityDocument(int identityDocument) {
        return passengerRepository.findByIdentityDocument(identityDocument);
    }

}