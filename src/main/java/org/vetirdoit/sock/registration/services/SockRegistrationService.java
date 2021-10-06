package org.vetirdoit.sock.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.vetirdoit.sock.registration.domain.BiPredicate;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.domain.entities.SockType;
import org.vetirdoit.sock.registration.repositories.SockRepository;
import org.vetirdoit.sock.registration.services.exceptions.InvalidOperationException;

import javax.validation.Valid;


@Service
@Validated
public class SockRegistrationService {

    private final SockRepository sockRepository;

    @Autowired
    public SockRegistrationService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @Transactional(readOnly = true)
    public long countRequiredSocks(Color color, BiPredicate operation, int cottonPartValue) {
        long count = 0;
        switch (operation) {
            case GREATER_THAN:
                count = sockRepository.countSockTypesWhenCottonPartGreaterThan(color, cottonPartValue);
                break;
            case EQUAL:
                count = sockRepository.countSockTypesWhenCottonPartEqual(color, cottonPartValue);
                break;
            case LESS_THAN:
                count = sockRepository.countSockTypesWhenCottonPartLessThan(color, cottonPartValue);
                break;
        }
        return count;
    }
    @Transactional
    public void registerOutgoingSocks(@Valid SockType outgoingSockType) throws InvalidOperationException {

        SockType sockType = sockRepository
                .findSockTypeByColorAndCottonPart(outgoingSockType.getColor(), outgoingSockType.getCottonPart())
                .orElseThrow( () -> new InvalidOperationException("No such sock type!") );

        int newQuantity = sockType.getQuantity() - outgoingSockType.getQuantity();
        if (newQuantity < 0) {
            throw new InvalidOperationException("You have registered too many socks!");
        } else if (newQuantity == 0) {
            sockRepository.delete(sockType);
        } else {
            sockType.setQuantity( newQuantity );
            sockRepository.save(sockType);
        }
    }

    @Transactional
    public void registerIncomingSocks(@Valid SockType incomingSockType) {

        sockRepository
                .findSockTypeByColorAndCottonPart( incomingSockType.getColor(), incomingSockType.getCottonPart() )
                .ifPresentOrElse(
                        sockType -> {
                            sockType.setQuantity( sockType.getQuantity() + incomingSockType.getQuantity() );
                            sockRepository.save(sockType);
                            },
                        () -> sockRepository.save( incomingSockType )
        );
    }
}
