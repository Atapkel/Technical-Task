package solve.lab.coffeeMachine.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import solve.lab.coffeeMachine.dto.QuantityDTO;
import solve.lab.coffeeMachine.dto.ResourceDTO;
import solve.lab.coffeeMachine.models.Resource;
import solve.lab.coffeeMachine.services.ResourcesService;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {
    private final ResourcesService resourcesService;
    private final ModelMapper modelMapper;

    public ResourcesController(ResourcesService resourcesService, ModelMapper modelMapper) {
        this.resourcesService = resourcesService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/")
    public List<ResourceDTO> allResources(){
        return resourcesService.findAll()
                .stream().map(this::convertToResourceDTO).toList();
    }
    @GetMapping("/{name}")
    public ResourceDTO getResource(@PathVariable("name") String name){
        return convertToResourceDTO(resourcesService.findByName(name));
    }
    @PostMapping("/new")
    public String createIngredient(@RequestBody ResourceDTO resourceDTO){
        resourcesService.save(convertToResource(resourceDTO));
        return "OK";
    }
    @PatchMapping("/{name}/add")
    public String addQuantity(@PathVariable("name") String name
            , @RequestBody QuantityDTO quantityDTO){
        resourcesService.addQuantity(name,quantityDTO.getQuantity());
        return "Added successfully!";
    }
    private ResourceDTO convertToResourceDTO(Resource resource){
        return modelMapper.map(resource, ResourceDTO.class);
    }
    private Resource convertToResource(ResourceDTO resourceDTO){
        return modelMapper.map(resourceDTO, Resource.class);
    }
}
