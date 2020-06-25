package com.cms.api.domain.recruitment.domain;

import com.cms.api.domain.comment.domain.RecruitmentComment;
import com.cms.api.domain.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@AttributeOverride(name = "post_id", column = @Column(name = "recruitment_id"))
public class Recruitment extends Post {

    @OneToMany(mappedBy = "recruitment_id")
    private List<RecruitmentComment> recruitmentComments = new ArrayList<>();
}
