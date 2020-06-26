package com.cms.api.domain.recruitment.domain;

import com.cms.api.domain.comment.domain.RecruitmentComment;
import com.cms.api.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "recruitment_id"))
public class Recruitment extends Post {

    @OneToMany(mappedBy = "recruitment")
    private List<RecruitmentComment> recruitmentComments = new ArrayList<>();
}
