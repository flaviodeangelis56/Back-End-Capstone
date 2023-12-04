package flaviodeangelis.noleggioAuto.services;

import flaviodeangelis.noleggioAuto.entities.User;
import flaviodeangelis.noleggioAuto.exceptions.NotFoundException;
import flaviodeangelis.noleggioAuto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public User findUtenteById(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findUtenteByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public void findByIdAndDelate(int id) throws NotFoundException {
        User found = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        userRepository.delete(found);
    }

    
}
