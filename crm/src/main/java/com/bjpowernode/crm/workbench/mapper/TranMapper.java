package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.FunnelVO;
import com.bjpowernode.crm.workbench.domain.Tran;

import java.util.List;

public interface TranMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Mon Jul 31 21:07:24 HKT 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Mon Jul 31 21:07:24 HKT 2023
     */
    int insert(Tran row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Mon Jul 31 21:07:24 HKT 2023
     */
    int insertSelective(Tran row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Mon Jul 31 21:07:24 HKT 2023
     */
    Tran selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Mon Jul 31 21:07:24 HKT 2023
     */
    int updateByPrimaryKeySelective(Tran row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Mon Jul 31 21:07:24 HKT 2023
     */
    int updateByPrimaryKey(Tran row);

    /**
     * 保存创建的交易
     * @param tran
     * @return
     */
    int insertTran(Tran tran);

    /**
     * 查询交易表中各个阶段的数据量
     * @return
     */
    List<FunnelVO> selectCountOfTranGroupByStage();
}