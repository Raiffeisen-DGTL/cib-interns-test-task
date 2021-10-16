package ru.pkaranda.cibinternstesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pkaranda.cibinternstesttask.model.domain.SockColor;
import ru.pkaranda.cibinternstesttask.model.domain.SocksTransaction;

import java.util.Collection;

@Repository
public interface SocksTransactionRepository extends JpaRepository<SocksTransaction, Long> {

    Collection<SocksTransaction> getSocksTransactionsByColorId(Long colorId);

    Collection<SocksTransaction> getSocksTransactionsByColor(SockColor color);

    Collection<SocksTransaction> getSocksTransactionsByColorIdAndCottonPartGreaterThan(Long colorId, int cottonPart);

    Collection<SocksTransaction> getSocksTransactionsByColorIdAndCottonPartLessThan(Long colorId, int cottonPart);

    Collection<SocksTransaction> getSocksTransactionsByColorIdAndCottonPartEquals(Long colorId, int cottonPart);

}
