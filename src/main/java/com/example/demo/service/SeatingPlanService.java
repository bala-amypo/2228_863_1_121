@Service
public class SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final ExamRoomRepository roomRepo;
    private final SeatingPlanRepository planRepo;

    public SeatingPlanService(ExamSessionRepository s,
                              ExamRoomRepository r,
                              SeatingPlanRepository p) {
        this.sessionRepo = s;
        this.roomRepo = r;
        this.planRepo = p;
    }

    public SeatingPlan generate(Long sessionId) {

        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int count = session.getStudents().size();

        ExamRoom room = roomRepo.findAll().stream()
                .filter(r -> r.getCapacity() >= count)
                .findFirst()
                .orElseThrow(() -> new ApiException("no room"));

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(room);
        plan.setArrangementJson("{\"students\":" + count + "}");

        return planRepo.save(plan);
    }
}
