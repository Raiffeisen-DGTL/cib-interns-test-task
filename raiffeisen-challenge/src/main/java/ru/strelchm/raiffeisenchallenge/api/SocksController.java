package ru.strelchm.raiffeisenchallenge.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.domain.SockColor;
import ru.strelchm.raiffeisenchallenge.dto.InOutComeSockDto;
import ru.strelchm.raiffeisenchallenge.dto.SockCompareOperation;
import ru.strelchm.raiffeisenchallenge.dto.SockCriteria;
import ru.strelchm.raiffeisenchallenge.dto.SockDto;
import ru.strelchm.raiffeisenchallenge.exception.BadRequestException;
import ru.strelchm.raiffeisenchallenge.service.SockService;
import ru.strelchm.raiffeisenchallenge.util.MappingUtil;
import ru.strelchm.raiffeisenchallenge.util.SockMapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@Api("REST controller 4 DatahubRequest operations")
@RequestMapping("/api/socks")
@Validated
public class SocksController extends ParentController {
    private static final Logger logger = Logger.getLogger(SocksController.class.getName());
    private final SockService sockService;
    private final MappingUtil mappingUtil;

    @Autowired
    public SocksController(SockService sockService, MappingUtil mappingUtil) {
        this.sockService = sockService;
        this.mappingUtil = mappingUtil;
    }

    @GetMapping
    @ApiOperation("Общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    public List<SockDto> getSocksCount(@ApiParam(value = "Sock color, string") @Validated @RequestParam(required = false) SockColor color,
                                       @ApiParam(value = "Cotton compare operator, " +
                                            "one value of: moreThan, lessThan, equal")
                                    @Validated @RequestParam(required = false) String compareOperation,
                                       @ApiParam(value = "Cotton part") @Max(value = 100, message = "Cotton part can't be greater then 100%")
                                    @Min(value = 0, message = "Cotton part can't be less then 0%") @Validated @RequestParam(required = false) Integer comparedCottonPart) {
        if ((compareOperation == null && comparedCottonPart != null) || (compareOperation != null && comparedCottonPart == null)) {
            throw new BadRequestException("Cotton part and compare operation must set together");
        }
        SockCriteria sockCriteria = new SockCriteria().setColor(color).setComparedCottonPart(comparedCottonPart)
                .setCompareOperation(compareOperation == null ? null : SockCompareOperation.getByName(compareOperation));
        return sockService.getAll(sockCriteria).stream().map(mappingUtil::mapToSockDto).collect(Collectors.toList());
    }

    @PostMapping("/income")
    @ApiOperation("Регистрация прихода носков на склад")
    public SockDto sockIncome(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @RequestBody InOutComeSockDto incomeSockDto) {
        if (incomeSockDto.getQuantity() == 0) {
            logger.warning("Income sock count is 0");
        }
        return mappingUtil.mapToSockDto(sockService.sockOutcome(incomeSockDto));
    }

    @PostMapping("/outcome")
    @ApiOperation("Регистрация отпуска носков на склад")
    public SockDto sockOutcome(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @RequestBody InOutComeSockDto outcomeSockDto) {
        if (outcomeSockDto.getQuantity() == 0) {
            logger.warning("Outcome sock count is 0");
        }
        return mappingUtil.mapToSockDto(sockService.sockIncome(outcomeSockDto));
    }
}
