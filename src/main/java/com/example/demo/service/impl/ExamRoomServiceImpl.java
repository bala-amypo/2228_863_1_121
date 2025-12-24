@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    public ExamRoom addRoom(ExamRoom r) {
        if (repo.findByRoomNumber(r.getRoomNumber()).isPresent())
            throw new ApiException("exists");

        if (r.getRows() < 1 || r.getColumns() < 1)
            throw new ApiException("invalid");

        r.ensureCapacityMatches();
        return repo.save(r);
    }

    public List<ExamRoom> getAllRooms() {
        return repo.findAll();
    }
}
