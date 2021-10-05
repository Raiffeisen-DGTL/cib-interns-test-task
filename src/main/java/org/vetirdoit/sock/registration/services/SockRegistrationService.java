package org.vetirdoit.sock.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.vetirdoit.sock.registration.domain.BiPredicate;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.domain.entities.SockType;
import org.vetirdoit.sock.registration.repositories.SockRepository;
import org.vetirdoit.sock.registration.dtos.utils.DtoConverter;
import org.vetirdoit.sock.registration.dtos.SockTypeDto;

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
    public long getCountOfRequiredSocks(Color color, BiPredicate operation, int cottonPartValue) {
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
    public boolean registerOutgoingSocks(@Valid SockTypeDto sockTypeDto) {

        var maybeSockType = sockRepository
                .findSockTypeByColorAndCottonPart(sockTypeDto.getColor(), sockTypeDto.getCottonPart());

        if (maybeSockType.isPresent()) {

            SockType sockType = maybeSockType.get();
            int newQuantity = sockType.getQuantity() - sockTypeDto.getQuantity();

            if (newQuantity < 0) {
                return false;
            } else if (newQuantity == 0) {
                sockRepository.delete(sockType);
                return  true;
            } else {
                sockType.setQuantity( newQuantity );
                sockRepository.save(sockType);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void registerIncomingSocks(@Valid SockTypeDto sockTypeDto) {

        var maybeSockType = sockRepository
                .findSockTypeByColorAndCottonPart(sockTypeDto.getColor(), sockTypeDto.getCottonPart());

        maybeSockType.ifPresentOrElse(
                sockType -> {
                    sockType.setQuantity( sockType.getQuantity() + sockTypeDto.getQuantity() );
                    sockRepository.save(sockType);
                },
                () -> sockRepository.save( DtoConverter.toSockType(sockTypeDto) )
        );
    }
}
