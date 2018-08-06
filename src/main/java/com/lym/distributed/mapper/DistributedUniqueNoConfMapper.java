package com.lym.distributed.mapper;

import com.lym.distributed.entity.DistributedUniqueNoConf;
import com.lym.distributed.entity.DistributedUniqueNoConfExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DistributedUniqueNoConfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    long countByExample(DistributedUniqueNoConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int deleteByExample(DistributedUniqueNoConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String bizTag);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int insert(DistributedUniqueNoConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int insertSelective(DistributedUniqueNoConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    List<DistributedUniqueNoConf> selectByExample(DistributedUniqueNoConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    DistributedUniqueNoConf selectByPrimaryKey(String bizTag);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DistributedUniqueNoConf record, @Param("example") DistributedUniqueNoConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DistributedUniqueNoConf record, @Param("example") DistributedUniqueNoConfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistributedUniqueNoConf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_distributed_unique_no_conf
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistributedUniqueNoConf record);

    int updateMaxId(String bizTag);

    DistributedUniqueNoConf selectByBizTag(String bizTag);
}