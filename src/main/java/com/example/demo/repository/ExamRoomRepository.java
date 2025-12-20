public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {
    Optional<ExamRoom> findByRoomNumber(String roomNumber);
    java.util.List<ExamRoom> findByCapacityGreaterThanEqual(Integer capacity);
}
