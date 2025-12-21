@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService service;

    public ExamRoomController(ExamRoomService service) {
        this.service = service;
    }

    @PostMapping
    public ExamRoom add(@RequestBody ExamRoom r) {
        return service.add(r);
    }

    @GetMapping
    public List<ExamRoom> all() {
        return service.all();
    }
}
