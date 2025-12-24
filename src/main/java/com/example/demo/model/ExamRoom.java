@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamRoom {

    @Id
    @GeneratedValue
    private Long id;

    private String roomNumber;
    private Integer rows;
    private Integer columns;
    private Integer capacity;

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            capacity = rows * columns;
        }
    }
}
