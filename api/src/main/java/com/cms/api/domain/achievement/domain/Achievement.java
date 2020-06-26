package com.cms.api.domain.achievement.domain;

import com.cms.api.domain.comment.domain.AchievementComment;
import com.cms.api.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "achievement_id"))
public class Achievement extends Post {

    @OneToMany(mappedBy = "achievementId")
    private List<AchievementComment> achievementComments = new ArrayList<>();
}
