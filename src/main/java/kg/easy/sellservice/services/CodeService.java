package kg.easy.sellservice.services;

import kg.easy.sellservice.models.entities.Code;
import kg.easy.sellservice.models.entities.User;

public interface CodeService {

    Integer randomCode();

    Code saveCode(Code code);

    Code findUserCode(User user);

}
