@RestController
@RequestMapping("/sessions")
public class ExamSessionController {

    private final ExamSessionService service;

    public ExamSessionController(ExamSessionService service) {
        this.service = service;
    }

    @PostMapping
    public ExamSession create(@RequestBody ExamSession s) {
        return service.create(s);
    }

    @GetMapping("/{id}")
    public ExamSession get(@PathVariable Long id) {
        return service.get(id);
    }
}
