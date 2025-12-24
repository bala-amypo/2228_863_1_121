@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repo;
    private final StudentRepository studentRepo;

    public ExamSessionServiceImpl(ExamSessionRepository repo, StudentRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    public ExamSession createSession(ExamSession s) {
        if (s.getExamDate().isBefore(LocalDate.now()))
            throw new ApiException("past");

        if (s.getStudents() == null || s.getStudents().isEmpty())
            throw new ApiException("at least 1 student");

        return repo.save(s);
    }

    public ExamSession getSession(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
