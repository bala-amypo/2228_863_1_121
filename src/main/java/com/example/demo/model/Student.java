@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String rollNumber;
    private String name;
    private String department;
    private Integer year;
}
