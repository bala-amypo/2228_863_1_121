@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepo,
                           BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public User register(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent())
            throw new ApiException("email exists");

        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRole() == null)
            user.setRole("STAFF");

        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ApiException("user not found"));
    }
}
