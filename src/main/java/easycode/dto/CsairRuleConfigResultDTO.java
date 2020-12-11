package easycode.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description：规则配置活动(CsairRuleConfigResult)DTO
 * @author：dengjianhan
 * @create：2020/11/26 
 */
@Data
public class CsairRuleConfigResultDTO implements Serializable {

    private static final long serialVersionUID = 631921063771996028L;
    /**
     * 主键
     */     
    private String id;
    /**
     * 规则配置主键ID
     */     
    private String ruleConfigId;
    /**
     * 属性类别主键ID
     */     
    private String categoryId;
    /**
     * 活动ID
     */     
    private String activityId;
    /**
     * 序列
     */     
    private Integer sequence;
    /**
     * 修改时间
     */     
    private Date updateTime;
    /**
     * 创建时间
     */     
    private Date createTime;
    /**
     * 产品名称(中文)
     */     
    private String productZhName;
    /**
     * 产品名称(英文)
     */     
    private String productEnName;
    /**
     * 子产品ID
     */     
    private String subproductId;
    /**
     * 子产品名称(中文)
     */     
    private String subproductZhName;
    /**
     * 子产品名称(英文)
     */     
    private String subproductEnName;
    /**
     * 套餐ID
     */     
    private String packageId;
    /**
     * 套餐名称(中文)
     */     
    private String packageZhName;
    /**
     * 套餐名称(英文)
     */     
    private String packageEnName;
    /**
     * 航班始发时间限制
     */
    private String timeLimit;
    /**
     * 产品说明链接(PC)
     */
    private String pcLink;
    /**
     * 产品说明链接(移动)
     */
    private String appLink;
    /**
     * 使用说明(中文)
     */     
    private String instructionZh;
    /**
     * 使用说明(英文)
     */     
    private String instructionEn;
    /**
     * 产品说明链接(PC英文)
     */     
    private String pcEnLink;
    /**
     * 产品说明链接(移动英文)
     */     
    private String appEnLink;
    /**
     * 是否支持邮寄（0：不支持；1：支持）
     */     
    private String isPost;
    /**
     * 是否与机票订单强关联（0：否，1：是）
     */     
    private String isStronglyCarorrelate;
    /**
     * 是否可退票（0：否，1：是）
     */     
    private String refundable;
    /**
     * 退改规则-成人
     */     
    private String adultRule;
    /**
     * 退改规则-儿童
     */     
    private String chileRule;
    /**
     * 退改规则-婴儿
     */     
    private String infantRule;
    /**
     * 是否开启库存校验（0：关闭；1：开启）
     */     
    private String isCheckStock;
    /**
     * 库存预扣地址
     */     
    private String stockSite;
    /**
     * 信息校验地址
     */     
    private String informationCheckSite;
    /**
     * 支付校验地址
     */     
    private String paymentCheckSite;
    /**
     * 销售类型
     */     
    private String saleType;
    /**
     * 订单类型：X；SO
     */     
    private String orderType;
    /**
     * 是否聚合：0:否；1:是
     */     
    private String aggregation;
    /**
     * 退改规则-成人
     */     
    private String adultEnRule;
    /**
     * 退改规则-儿童
     */     
    private String chileEnRule;
    /**
     * 退改规则-婴儿
     */     
    private String infantEnRule;
    /**
     * 可销售语言版本
     */     
    private String saleVersion;
    /**
     * 产品类型
     */     
    private String productCategory;
    /**
     * 拆分中转：0:否；1:是
     */     
    private String isTransit;
    /**
     * 退改规则-成人
     */     
    private String queryUrl;
    /**
     * 退票强制关联：N:否；Y:是
     */     
    private String refundAssociation;
    /**
     * 乘客类型：1:成人；2:儿童 3:婴儿
     */     
    private String passengerType;
    /**
     * X产品查询地址类型 1:增值产品 2:假期产品
     */     
    private String categoryType;
         
    private String productLabel;

}