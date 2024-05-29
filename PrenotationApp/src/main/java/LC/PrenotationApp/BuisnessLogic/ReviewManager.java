package LC.PrenotationApp.BuisnessLogic;

import LC.PrenotationApp.DAO.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewManager {
    @Autowired
    private ReviewDao reviewDao;
}
