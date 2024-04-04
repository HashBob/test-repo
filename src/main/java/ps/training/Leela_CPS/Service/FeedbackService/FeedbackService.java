package ps.training.Leela_CPS.Service.FeedbackService;

import ps.training.Leela_CPS.Model.Feedback;

import java.util.List;

public interface FeedbackService{
    void addFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacks();
    Feedback getFeedbackById(long id);
}
