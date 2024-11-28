package solve.lab.coffeeMachine.services;

import org.springframework.stereotype.Service;
import solve.lab.coffeeMachine.models.Resource;
import solve.lab.coffeeMachine.repositories.ResourcesRepository;

import java.util.List;

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
        return resourcesRepository.findByName(name).orElse(null);
    }

    public void save(Resource resource) {
        resourcesRepository.save(resource);
    }

    public void addQuantity(String name,Integer quantity) {
        resourcesRepository.addQuantity(name, quantity);
    }

    public void useQuantity(String name, int quantity) {
        resourcesRepository.useQuantity(name, quantity);
    }
}
