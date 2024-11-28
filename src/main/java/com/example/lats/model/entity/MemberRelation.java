package com.example.lats.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "MEMBER_RELATION")
public class MemberRelation extends BaseClass<MemberRelation>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_RELATION_SEQ")
    @SequenceGenerator(sequenceName = "MEMBER_RELATION_SEQ", allocationSize = 1, name = "MEMBER_RELATION_SEQ")
    private String Id;
    private Long householdId; // Reference to Household entity
    private Long memberId1; // Reference to Member entity
    private Long memberId2; // Reference to Member entity
    private String relationType;
}
