package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-07-18  20:39
 * @Description TODO
 * @since 1.0
 */
public interface ClueActivityRelationService {
    int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list);
    int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation);
}
