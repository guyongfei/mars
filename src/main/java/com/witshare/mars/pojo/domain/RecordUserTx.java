package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RecordUserTx {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.user_gid
     *
     * @mbggenerated
     */
    private String userGid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.user_email
     *
     * @mbggenerated
     */
    private String userEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.project_gid
     *
     * @mbggenerated
     */
    private String projectGid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.project_token
     *
     * @mbggenerated
     */
    private String projectToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.pay_coin_type
     *
     * @mbggenerated
     */
    private int payCoinType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.pay_tx
     *
     * @mbggenerated
     */
    private String payTx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.pay_tx_id
     *
     * @mbggenerated
     */
    private Integer payTxId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.pay_amount
     *
     * @mbggenerated
     */
    private BigDecimal payAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.price_rate
     *
     * @mbggenerated
     */
    private BigDecimal priceRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.hope_get_amount
     *
     * @mbggenerated
     */
    private BigDecimal hopeGetAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.actual_pay_amount
     *
     * @mbggenerated
     */
    private BigDecimal actualPayAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.actual_get_amount
     *
     * @mbggenerated
     */
    private BigDecimal actualGetAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.user_tx_status
     *
     * @mbggenerated
     */
    private int userTxStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.platform_tx
     *
     * @mbggenerated
     */
    private String platformTx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.eth_fee
     *
     * @mbggenerated
     */
    private BigDecimal ethFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.platform_tx_status
     *
     * @mbggenerated
     */
    private int platformTxStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.distribution_time
     *
     * @mbggenerated
     */
    private Timestamp distributionTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.create_time
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column record_user_tx.update_time
     *
     * @mbggenerated
     */
    private Timestamp updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.id
     *
     * @return the value of record_user_tx.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.id
     *
     * @param id the value for record_user_tx.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.user_gid
     *
     * @return the value of record_user_tx.user_gid
     *
     * @mbggenerated
     */
    public String getUserGid() {
        return userGid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.user_gid
     *
     * @param userGid the value for record_user_tx.user_gid
     *
     * @mbggenerated
     */
    public void setUserGid(String userGid) {
        this.userGid = userGid == null ? null : userGid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.user_email
     *
     * @return the value of record_user_tx.user_email
     *
     * @mbggenerated
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.user_email
     *
     * @param userEmail the value for record_user_tx.user_email
     *
     * @mbggenerated
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.project_gid
     *
     * @return the value of record_user_tx.project_gid
     *
     * @mbggenerated
     */
    public String getProjectGid() {
        return projectGid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.project_gid
     *
     * @param projectGid the value for record_user_tx.project_gid
     *
     * @mbggenerated
     */
    public void setProjectGid(String projectGid) {
        this.projectGid = projectGid == null ? null : projectGid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.project_token
     *
     * @return the value of record_user_tx.project_token
     *
     * @mbggenerated
     */
    public String getProjectToken() {
        return projectToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.project_token
     *
     * @param projectToken the value for record_user_tx.project_token
     *
     * @mbggenerated
     */
    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken == null ? null : projectToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.pay_coin_type
     *
     * @return the value of record_user_tx.pay_coin_type
     *
     * @mbggenerated
     */
    public int getPayCoinType() {
        return payCoinType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.pay_coin_type
     *
     * @param payCoinType the value for record_user_tx.pay_coin_type
     *
     * @mbggenerated
     */
    public void setPayCoinType(int payCoinType) {
        this.payCoinType = payCoinType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.pay_tx
     *
     * @return the value of record_user_tx.pay_tx
     *
     * @mbggenerated
     */
    public String getPayTx() {
        return payTx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.pay_tx
     *
     * @param payTx the value for record_user_tx.pay_tx
     *
     * @mbggenerated
     */
    public void setPayTx(String payTx) {
        this.payTx = payTx == null ? null : payTx.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.pay_tx_id
     *
     * @return the value of record_user_tx.pay_tx_id
     *
     * @mbggenerated
     */
    public Integer getPayTxId() {
        return payTxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.pay_tx_id
     *
     * @param payTxId the value for record_user_tx.pay_tx_id
     *
     * @mbggenerated
     */
    public void setPayTxId(Integer payTxId) {
        this.payTxId = payTxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.pay_amount
     *
     * @return the value of record_user_tx.pay_amount
     *
     * @mbggenerated
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.pay_amount
     *
     * @param payAmount the value for record_user_tx.pay_amount
     *
     * @mbggenerated
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.price_rate
     *
     * @return the value of record_user_tx.price_rate
     *
     * @mbggenerated
     */
    public BigDecimal getPriceRate() {
        return priceRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.price_rate
     *
     * @param priceRate the value for record_user_tx.price_rate
     *
     * @mbggenerated
     */
    public void setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.hope_get_amount
     *
     * @return the value of record_user_tx.hope_get_amount
     *
     * @mbggenerated
     */
    public BigDecimal getHopeGetAmount() {
        return hopeGetAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.hope_get_amount
     *
     * @param hopeGetAmount the value for record_user_tx.hope_get_amount
     *
     * @mbggenerated
     */
    public void setHopeGetAmount(BigDecimal hopeGetAmount) {
        this.hopeGetAmount = hopeGetAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.actual_pay_amount
     *
     * @return the value of record_user_tx.actual_pay_amount
     *
     * @mbggenerated
     */
    public BigDecimal getActualPayAmount() {
        return actualPayAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.actual_pay_amount
     *
     * @param actualPayAmount the value for record_user_tx.actual_pay_amount
     *
     * @mbggenerated
     */
    public void setActualPayAmount(BigDecimal actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.actual_get_amount
     *
     * @return the value of record_user_tx.actual_get_amount
     *
     * @mbggenerated
     */
    public BigDecimal getActualGetAmount() {
        return actualGetAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.actual_get_amount
     *
     * @param actualGetAmount the value for record_user_tx.actual_get_amount
     *
     * @mbggenerated
     */
    public void setActualGetAmount(BigDecimal actualGetAmount) {
        this.actualGetAmount = actualGetAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.user_tx_status
     *
     * @return the value of record_user_tx.user_tx_status
     *
     * @mbggenerated
     */
    public int getUserTxStatus() {
        return userTxStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.user_tx_status
     *
     * @param userTxStatus the value for record_user_tx.user_tx_status
     *
     * @mbggenerated
     */
    public void setUserTxStatus(int userTxStatus) {
        this.userTxStatus = userTxStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.platform_tx
     *
     * @return the value of record_user_tx.platform_tx
     *
     * @mbggenerated
     */
    public String getPlatformTx() {
        return platformTx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.platform_tx
     *
     * @param platformTx the value for record_user_tx.platform_tx
     *
     * @mbggenerated
     */
    public void setPlatformTx(String platformTx) {
        this.platformTx = platformTx == null ? null : platformTx.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.eth_fee
     *
     * @return the value of record_user_tx.eth_fee
     *
     * @mbggenerated
     */
    public BigDecimal getEthFee() {
        return ethFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.eth_fee
     *
     * @param ethFee the value for record_user_tx.eth_fee
     *
     * @mbggenerated
     */
    public void setEthFee(BigDecimal ethFee) {
        this.ethFee = ethFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.platform_tx_status
     *
     * @return the value of record_user_tx.platform_tx_status
     *
     * @mbggenerated
     */
    public int getPlatformTxStatus() {
        return platformTxStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.platform_tx_status
     *
     * @param platformTxStatus the value for record_user_tx.platform_tx_status
     *
     * @mbggenerated
     */
    public void setPlatformTxStatus(int platformTxStatus) {
        this.platformTxStatus = platformTxStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.distribution_time
     *
     * @return the value of record_user_tx.distribution_time
     *
     * @mbggenerated
     */
    public Timestamp getDistributionTime() {
        return distributionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.distribution_time
     *
     * @param distributionTime the value for record_user_tx.distribution_time
     *
     * @mbggenerated
     */
    public void setDistributionTime(Timestamp distributionTime) {
        this.distributionTime = distributionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.create_time
     *
     * @return the value of record_user_tx.create_time
     *
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.create_time
     *
     * @param createTime the value for record_user_tx.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column record_user_tx.update_time
     *
     * @return the value of record_user_tx.update_time
     *
     * @mbggenerated
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column record_user_tx.update_time
     *
     * @param updateTime the value for record_user_tx.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}