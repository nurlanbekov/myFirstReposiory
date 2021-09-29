package kg.easy.sellservice.services.impl;

import kg.easy.sellservice.dao.RequestRepo;
import kg.easy.sellservice.models.entities.Code;
import kg.easy.sellservice.models.entities.Request;
import kg.easy.sellservice.services.RequestService;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepo requestRepo;

    public RequestServiceImpl(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    @Override
    public void saveRequest(Request request) {
        requestRepo.save(request);
    }

    @Override
    public int countFailedAttempts(Code code) {
        return requestRepo.countByCodeAndSuccess(code, false);
    }
}
