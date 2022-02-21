package net.javaguides.springboot.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.PackageDto;
import net.javaguides.springboot.springsecurity.model.dto.ChangePackageRequest;
import net.javaguides.springboot.springsecurity.service.PathFileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/packages")
public class PackageController {

    private final PathFileService pathFileService;


    @GetMapping("{name}")
    public PackageDto getPath(@PathVariable("name") String name) {


        return pathFileService.getPath(name);
    }

    @DeleteMapping("{name}")
    public void deletePackage(@PathVariable("name") String name) {
        pathFileService.deletePackage(name);
    }

    @PutMapping
    public void changePackage(@RequestBody ChangePackageRequest changePackageRequest) {
        pathFileService.changePackage(changePackageRequest);
    }
}
