package socksRepositoryTestTask.service;

import socksRepositoryTestTask.controller.SockController;
import socksRepositoryTestTask.model.Color;
import socksRepositoryTestTask.model.SockDTO;

public interface SockService {
    int findAndCountSocks(Color color, SockController.Operation operation, int cottonPart);

    //добавляет в БД новую разновидность носков
    //если данная разновидность уже есть - добавляет количество носков (quantity)
    void income(SockDTO sockDTO);

    //проверяет наличие необходимого количества носков для отпуска из БД
    //если такой разновидности носков не найдено - кидает NoSuchEntityOnDatabaseException
    //если нет достаточного для отпуска количества - кидает MissingRequestedQuantityException
    //удаляет разновидность носков из БД при quantity = 0
    void outcome(SockDTO sockDTO);
}
