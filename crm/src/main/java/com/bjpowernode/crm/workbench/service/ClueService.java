package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Clue;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-06-19  01:22
 * @Description TODO
 * @since 1.0
 */
public interface ClueService {
    int saveCreateClue(Clue clue);

    Clue queryClueForDetailById(String id);
}
