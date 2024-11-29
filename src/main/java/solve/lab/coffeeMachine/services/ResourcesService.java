package solve.lab.coffeeMachine.services;

import org.springframework.stereotype.Service;
import solve.lab.coffeeMachine.models.Resource;
import solve.lab.coffeeMachine.repositories.ResourcesRepository;
import solve.lab.coffeeMachine.utils.ResourceAlreadyExists;
import solve.lab.coffeeMachine.utils.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ResourcesService {
    private final ResourcesRepository resourcesRepository;

    public ResourcesService(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    public List<Resource> findAll() {
        return resourcesRepository.findAll();
    }

    public Resource findByName(String name) {
        return resourcesRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

    public void save(Resource resource) {
        Optional<Resource> check = resourcesRepository.findByName(resource.getName());
        if (check.isPresent()){
            throw new ResourceAlreadyExists();
        }
        resourcesRepository.save(resource);
    }

    public void addQuantity(String name,Integer quantity) {
        Optional<Resource> check = resourcesRepository.findByName(name);
        if (check.isEmpty()){
            throw new ResourceNotFoundException();
        }
        resourcesRepository.addQuantity(name, quantity);
    }

    public void useQuantity(String name, int quantity) {
        resourcesRepository.useQuantity(name, quantity);
    }
}
