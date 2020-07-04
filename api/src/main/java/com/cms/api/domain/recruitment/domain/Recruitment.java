package com.cms.api.domain.recruitment.domain;

import com.cms.api.domain.comment.domain.RecruitmentComment;
import com.cms.api.domain.model.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "recruitment_id"))
public class Recruitment extends Post {

    @OneToMany(mappedBy = "recruitment")
    private List<RecruitmentComment> recruitmentComments = new ArrayList<>();
}
