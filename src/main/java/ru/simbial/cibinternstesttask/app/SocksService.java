package ru.simbial.cibinternstesttask.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.simbial.cibinternstesttask.app.model.SocksDBModel;
import ru.simbial.cibinternstesttask.app.model.SocksRequestData;

import javax.transaction.Transactional;

import static ru.simbial.cibinternstesttask.app.SocksUtil.checkGetAllSocksRequestFiltersOk;


@Service
public class SocksService {
    private final SocksRepository repository;

    @Autowired
    public SocksService(SocksRepository repository) {
        this.repository = repository;
    }

    public SocksDBModel getById(SocksDBModel.SocksId id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<SocksDBModel> registerSocksIncome(SocksRequestData data) {

        SocksDBModel dbModelData = SocksUtil.getSockFromRequestData(data);
        if (dbModelData == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            repository.findById(dbModelData.getId()).ifPresent(dbSocks -> dbModelData.setQuantity(dbModelData.getQuantity() + dbSocks.getQuantity()));
            SocksDBModel dbSocks = repository.save(dbModelData);
            return new ResponseEntity<>(dbSocks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<SocksDBModel> registerSocksOutcome(SocksRequestData data) {
        SocksDBModel dbModelData = SocksUtil.getSockFromRequestData(data);

        try {
            if (dbModelData == null
                    || getById(dbModelData.getId()) == null
                    || countQuantityAfterOutcome(dbModelData) < 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            SocksDBModel dbSocks = getById(dbModelData.getId());

            long quantityAfterSubtraction = dbSocks.getQuantity() - data.getQuantity();

            dbSocks.setQuantity(quantityAfterSubtraction);
            repository.save(dbSocks);
            return new ResponseEntity<>(dbSocks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> countSocksByFilter(String color,
                                                String oper,
                                                Integer cottonPart) {


        if (!checkGetAllSocksRequestFiltersOk(color, oper, cottonPart)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CottonPartComparisonOperation operation =
                CottonPartComparisonOperation.getByLabel(oper);

        try {
         long result;
            switch (operation) {
                case EQUAL: {
                    result = repository.getQuantityByColorAndCottonPartEqual(color, cottonPart);
                    break;
                }
                case LESS_THAN:
                    result = repository.getQuantityByColorAndCottonPartLessThan(color, cottonPart);
                    break;
                case MORE_THAN:
                    result = repository.getQuantityByColorAndCottonPartMoreThan(color, cottonPart);
                    break;
                default:
                    result = 0L;
            }
            return result == 0L ?
                    new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                    : new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Long countQuantityAfterOutcome(SocksDBModel data) {
        SocksDBModel dbSocks = getById(data.getId());
        return dbSocks.getQuantity() - data.getQuantity();
    }
}
