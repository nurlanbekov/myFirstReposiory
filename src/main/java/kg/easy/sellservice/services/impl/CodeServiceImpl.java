package kg.easy.sellservice.services.impl;

import kg.easy.sellservice.dao.CodeRepo;
import kg.easy.sellservice.models.entities.Code;
import kg.easy.sellservice.models.entities.User;
import kg.easy.sellservice.models.enums.CodeStatus;
import kg.easy.sellservice.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepo codeRepo;

    @Override
    public Integer randomCode() {
        return (int) (Math.random() * 9000) + 1000;
    }

    @Override
    public Code findUserCode(User user) {
        return codeRepo.findByUserAndStatus(user, CodeStatus.NEW);
    }

    @Override
    public Code saveCode(Code code) {

        return codeRepo.save(code);
    }


}
