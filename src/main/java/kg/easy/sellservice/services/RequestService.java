package kg.easy.sellservice.services;

import kg.easy.sellservice.models.entities.Code;
import kg.easy.sellservice.models.entities.Request;

public interface RequestService {

    void saveRequest(Request request);

    int countFailedAttempts(Code code);
}
