@Service
public class ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomService(ExamRoomRepository repo) {
        this.repo = repo;
    }

    public ExamRoom add(ExamRoom r) {
        if (r.getRows() <= 0 || r.getColumns() <= 0)
            throw new ApiException("invalid rows or columns");

        if (repo.findByRoomNumber(r.getRoomNumber()).isPresent())
            throw new ApiException("exists");

        r.ensureCapacityMatches();
        return repo.save(r);
    }

    public List<ExamRoom> all() {
        return repo.findAll();
    }
}
