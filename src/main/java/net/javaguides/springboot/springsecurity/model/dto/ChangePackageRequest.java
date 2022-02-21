package net.javaguides.springboot.springsecurity.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePackageRequest {

    private String oldPath;
    private String newPath;

}
