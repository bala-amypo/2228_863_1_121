public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {
    java.util.List<SeatingPlan> findByExamSessionId(Long sessionId);
}
