package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.RecensioniDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestoreRecensioni {
    @Autowired
    private RecensioniDao recensioniDao;
}
