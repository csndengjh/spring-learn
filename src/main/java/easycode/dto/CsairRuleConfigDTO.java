package easycode.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description：规则配置(CsairRuleConfig)DTO
 * @author：dengjianhan
 * @create：2020/11/26 
 */
@Data
public class CsairRuleConfigDTO implements Serializable {

    private static final long serialVersionUID = -13325517414912128L;
    /**
     * 主键
     */     
    private String id;
    /**
     * 名称
     */     
    private String name;
    /**
     * 状态
     */     
    private String status;
    /**
     * 生效开始时间
     */     
    private Date effectiveStartTime;
    /**
     * 生效结束时间
     */     
    private Date effectiveEndTime;
    /**
     * 规则内容
     */     
    private String dsl;
    /**
     * 规则属性文本
     */     
    private String ruleConfigAttributesText;
    /**
     * 修改时间
     */     
    private Date updateTime;
    /**
     * 创建时间
     */     
    private Date createTime;
    /**
     * 审批状态
     */     
    private String checkStatus;
    /**
     * 序列
     */     
    private Integer sequence;
    /**
     * 是否可修改状态
     */     
    private String modifyStatus;
         
    private String dslType;
         
    private String departure;
         
    private String distributionChannel;
         
    private String flightdate;
         
    private String takeofftime;
         
    private String flightnumbe;
         
    private String operationLog;
         
    private String destination;
         
    private Integer logIndex;
    /**
     * 产品ID
     */     
    private String productId;
    /**
     * 到达地操作符
     */     
    private String destinationOper;
    /**
     * 出发地操作符
     */     
    private String departureOper;
    /**
     * cangwei
     */     
    private String cabin;
    /**
     * 创建人
     */     
    private String createBy;
    /**
     * 国内运价组
     */     
    private String domesticFlightGroup;
    /**
     * 航线
     */     
    private String airLine;

}