public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {
    java.util.List<ExamSession> findByExamDate(java.time.LocalDate date);
}
