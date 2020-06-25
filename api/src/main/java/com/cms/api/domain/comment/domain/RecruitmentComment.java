package com.cms.api.domain.comment.domain;

import com.cms.api.domain.model.Comment;
import com.cms.api.domain.recruitment.domain.Recruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class RecruitmentComment extends Comment {

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;
}
