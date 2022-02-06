package net.javaguides.springboot.springsecurity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "")
@Data
@Builder
public class PathFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String path;
    private String name;
    private Boolean privacy;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_file_id", referencedColumnName = "id")
    private InfoFile infoFile;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "path_file_id", referencedColumnName = "id")
    private Set<Comment> comments = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics_id", referencedColumnName = "id")
    private StatisticsFile statisticsFile;
}
