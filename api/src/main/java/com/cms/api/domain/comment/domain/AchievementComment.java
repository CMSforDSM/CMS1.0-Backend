package com.cms.api.domain.comment.domain;

import com.cms.api.domain.achievement.domain.Achievement;
import com.cms.api.domain.model.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AchievementComment extends Comment {

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievementId;

    @OneToOne
    @JoinColumn(name = "child_comment")
    private AchievementComment childComment;
}
