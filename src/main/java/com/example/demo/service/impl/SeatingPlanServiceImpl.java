@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(
            ExamSessionRepository sessionRepo,
            SeatingPlanRepository planRepo,
            ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        List<ExamRoom> rooms = roomRepo.findAll();
        if (rooms.isEmpty())
            throw new ApiException("no room");

        ExamRoom room = rooms.get(0);

        SeatingPlan p = new SeatingPlan();
        p.setExamSession(session);
        p.setRoom(room);
        p.setGeneratedAt(LocalDateTime.now());
        p.setArrangementJson("{\"students\":[\"" +
                session.getStudents().iterator().next().getRollNumber() + "\"]}");

        return planRepo.save(p);
    }

    public SeatingPlan getPlan(Long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    public List<SeatingPlan> getPlansBySession(Long id) {
        return planRepo.findByExamSessionId(id);
    }
}
