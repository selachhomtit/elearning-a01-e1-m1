package co.istad.sela.elearning.features.video;

import co.istad.sela.elearning.config.auditing.BasedEntity;
import co.istad.sela.elearning.features.comment.Comment;
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
@Table(name = "videos")
public class Video extends BasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String slug;
    private String title;
    private String thumbnail;
    private String duration;

    private String youtube;

    private Boolean isPublished;
    private Boolean isDeleted;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "video")
    private List<Comment> comments;
}
