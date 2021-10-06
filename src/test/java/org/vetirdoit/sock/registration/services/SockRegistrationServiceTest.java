package org.vetirdoit.sock.registration.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vetirdoit.sock.registration.domain.BiPredicate;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.domain.entities.SockType;
import org.vetirdoit.sock.registration.repositories.SockRepository;
import org.vetirdoit.sock.registration.services.exceptions.InvalidOperationException;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SockRegistrationServiceTest {

    @Mock
    SockRepository sockRepository;
    @InjectMocks
    SockRegistrationService sockRegistrationService;

    @Test
    void getCountOfRequiredSocks() {
        Color color = Color.RED;
        int cottonPartValue = 50;
        var expectedValues = Map.of(
                BiPredicate.EQUAL, 10L,
                BiPredicate.MORE_THAN, 20L,
                BiPredicate.LESS_THAN, 30L
        );
        given(sockRepository.countSockTypesWhenCottonPartEqual(color, cottonPartValue)).willReturn(10L);
        given(sockRepository.countSockTypesWhenCottonPartGreaterThan(color, cottonPartValue)).willReturn(20L);
        given(sockRepository.countSockTypesWhenCottonPartLessThan(color, cottonPartValue)).willReturn(30L);

        for (BiPredicate predicate : BiPredicate.values()) {
            long actual = sockRegistrationService.countRequiredSocks(color, predicate, cottonPartValue);
            assertThat(actual).isEqualTo( expectedValues.get(predicate) );
        }
    }

    @Test
    void whenRegisterOutgoingSocksWithTooManySockOrSocksDoNotExist_thenThrowInvalidOperationException() {
        SockType outgoingSocks = SockType.createSockType(Color.RED, 1, 10);
        given(sockRepository.findSockTypeByColorAndCottonPart(outgoingSocks.getColor(), outgoingSocks.getCottonPart()))
                .willReturn(Optional.empty());
        assertThatThrownBy( () -> sockRegistrationService.registerOutgoingSocks(outgoingSocks) )
                .isInstanceOf(InvalidOperationException.class);

        given(sockRepository.findSockTypeByColorAndCottonPart(outgoingSocks.getColor(), outgoingSocks.getCottonPart()))
                .willReturn( Optional.of(SockType.createSockType(Color.RED, 1, 1)) );
        assertThatThrownBy( () -> sockRegistrationService.registerOutgoingSocks(outgoingSocks) )
                .isInstanceOf(InvalidOperationException.class);
    }
}