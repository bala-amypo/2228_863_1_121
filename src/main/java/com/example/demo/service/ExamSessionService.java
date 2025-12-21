@Service
public class ExamSessionService {

    private final ExamSessionRepository repo;

    public ExamSessionService(ExamSessionRepository repo) {
        this.repo = repo;
    }

    public ExamSession create(ExamSession s) {
        if (s.getExamDate().isBefore(LocalDate.now()))
            throw new ApiException("past");

        if (s.getStudents() == null || s.getStudents().isEmpty())
            throw new ApiException("at least 1 student");

        return repo.save(s);
    }

    public ExamSession get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
