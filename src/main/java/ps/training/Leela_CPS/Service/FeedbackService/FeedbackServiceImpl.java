package ps.training.Leela_CPS.Service.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.training.Leela_CPS.Model.Feedback;
import ps.training.Leela_CPS.Repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public void addFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(long id) {
        Optional<Feedback> optionalFeedback= feedbackRepository.findById(id);
        return optionalFeedback.orElse(null);
    }
}
