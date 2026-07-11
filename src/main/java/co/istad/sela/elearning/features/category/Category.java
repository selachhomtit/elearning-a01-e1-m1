package co.istad.sela.elearning.features.category;

import co.istad.sela.elearning.config.auditing.BasedEntity;
import co.istad.sela.elearning.features.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;
    private String icon;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;

}
