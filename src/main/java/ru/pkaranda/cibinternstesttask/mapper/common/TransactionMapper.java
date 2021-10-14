package ru.pkaranda.cibinternstesttask.mapper.common;

import org.mapstruct.Mapper;
import ru.pkaranda.cibinternstesttask.mapper.BaseMapper;
import ru.pkaranda.cibinternstesttask.model.domain.Transaction;
import ru.pkaranda.cibinternstesttask.model.dto.TransactionDTO;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper<Transaction, TransactionDTO> {
}
