package com.witshare.mars.service.impl;

import com.github.pagehelper.PageInfo;
import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.EnumDistrubiteStatus;
import com.witshare.mars.constant.EnumProjectStatus;
import com.witshare.mars.constant.EnumResponseText;
import com.witshare.mars.constant.EnumUserTxStatus;
import com.witshare.mars.exception.WitshareException;
import com.witshare.mars.pojo.dto.*;
import com.witshare.mars.pojo.vo.DistributionStatusVo;
import com.witshare.mars.pojo.vo.SysProjectBeanVo;
import com.witshare.mars.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by user on 2018/7/1.
 */
@Service
public class ExportServiceImpl implements ExportService {

    private final String FILEPATH_PROJECT_INFO_TEMPLATE = "/templates/excel/project-info.xls";
    private static final Logger LOG = LoggerFactory.getLogger(ExportServiceImpl.class);
    @Autowired
    private SysProjectService sysProjectService;
    @Autowired
    private ProjectStatisticService projectStatisticService;
    @Autowired
    private UserTxService userTxService;
    @Autowired
    private ChannelService channelService;

    @Override
    public void exportProjectExcel(String projectGid) {
        if (StringUtils.isAnyBlank(projectGid)) {
            throw new WitshareException(EnumResponseText.ErrorRequest);
        }
        try (HSSFWorkbook book = this.loadExcelTemplate(FILEPATH_PROJECT_INFO_TEMPLATE)) {
            if (book == null) {
                LOG.info("exportProjectExcel fail projectGid:{},FILEPATH_PROJECT_INFO_TEMPLATE unload", projectGid);
                return;
            }
            HSSFCellStyle cellStyleLeft = book.createCellStyle();
            cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 居左
            HSSFCellStyle cellStyleCenter = book.createCellStyle();
            cellStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
            HSSFCellStyle cellStyleRight = book.createCellStyle();
            cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居右

            HSSFSheet projectInfoSheet = book.getSheet("项目详情");
            HSSFSheet dailyInfoSheet = book.getSheet("每日统计");
            HSSFSheet channelStatisticInfoSheet = book.getSheet("渠道统计");
            HSSFSheet txSheet = book.getSheet("交易统计");
            HSSFSheet distributeSheet = book.getSheet("打币统计");
            HSSFSheet txInfoSheet = book.getSheet("交易明细");
            //获取数据
            SysProjectBeanVo sysProjectBeanVo = sysProjectService.selectManagementByProjectGid(projectGid);
            try {
                this.assembleProjectInfo(projectInfoSheet, sysProjectBeanVo, cellStyleLeft);
            } catch (Exception e) {
                LOG.error("exportProjectExcel fail projectGid:{},assembleProjectInfo .", projectGid, e);
            }

            ProjectSummaryBean summary = projectStatisticService.getSummary(projectGid);
            PageInfo<ProjectStatisticBean> dailyInfoList = projectStatisticService.getList(projectGid);
            try {
                this.assembleStatisticInfo(dailyInfoSheet, summary, dailyInfoList, cellStyleLeft, cellStyleCenter, cellStyleRight);
            } catch (Exception e) {
                LOG.error("exportProjectExcel fail projectGid:{},assembleStatisticInfo .", projectGid, e);
            }
            PageInfo<ProjectStatisticBean> channelList = projectStatisticService.getChannelList(projectGid);
            try {
                this.assembleChannelStatisticInfo(channelStatisticInfoSheet, summary, channelList, cellStyleLeft, cellStyleCenter, cellStyleRight);
            } catch (Exception e) {
                LOG.error("exportProjectExcel fail projectGid:{},assembleStatisticInfo .", projectGid, e);
            }

            PageInfo<DistributionStatusVo> userTxStatusCount = userTxService.getUserTxStatusCount(projectGid);

            PageInfo<DistributionStatusVo> platformStatusCount = userTxService.getPlatformStatusCount(projectGid);
            try {
                this.assembleDistributeInfo(distributeSheet, platformStatusCount, cellStyleLeft, cellStyleCenter, cellStyleRight);
            } catch (Exception e) {
                LOG.error("exportProjectExcel fail projectGid:{},assembleDistributeInfo .", projectGid, e);
            }


            try {
                this.assembleTxStatus(txSheet, userTxStatusCount, cellStyleLeft, cellStyleCenter, cellStyleRight);
            } catch (Exception e) {
                LOG.error("exportProjectExcel fail projectGid:{},assembleTxStatus .", projectGid, e);
            }

            PageInfo<RecordUserTxBean> txList = userTxService.getList(projectGid);
            try {
                this.assembleUserTxInfo(txInfoSheet, txList, cellStyleLeft, cellStyleCenter, cellStyleRight);
            } catch (Exception e) {
                LOG.error("exportProjectExcel fail projectGid:{},assembleUserTxInfo .", projectGid, e);
            }
            //修改名称等
            //修改excel的名称
            String excelName = sysProjectBeanVo.getProjectToken() + "_" + projectGid + "_" + System.currentTimeMillis();
            excelName = excelName.replace("\\s", "");
            book.createName().setNameName(excelName);

            //输出
            HttpServletResponse response = CurrentThreadContext.getResponse();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xls");
            book.write(response.getOutputStream());

        } catch (Exception e) {
            LOG.error("exportProjectExcel fail projectGid:{}.", projectGid, e);
        }
    }


    /**
     * 组装项目详情表
     */
    private void assembleProjectInfo(HSSFSheet sheet, SysProjectBeanVo sysProjectBeanVo, HSSFCellStyle... cellStyleLeft) {
        if (sysProjectBeanVo == null) {
            return;
        }
        String projectGid = sysProjectBeanVo.getProjectGid();
        String projectToken = sysProjectBeanVo.getProjectToken();
        String tokenAddress = sysProjectBeanVo.getTokenAddress();
        String projectAddress = sysProjectBeanVo.getProjectAddress();
        String platformAddress = sysProjectBeanVo.getPlatformAddress();
        String projectLogoLink = sysProjectBeanVo.getProjectLogoLink();
        BigDecimal softCap = sysProjectBeanVo.getSoftCap();
        BigDecimal hardCap = sysProjectBeanVo.getHardCap();
        BigDecimal minPurchaseAmount = sysProjectBeanVo.getMinPurchaseAmount();
        BigDecimal maxPurchaseAmount = sysProjectBeanVo.getMaxPurchaseAmount();
        Timestamp startTime = sysProjectBeanVo.getStartTime();
        Timestamp endTime = sysProjectBeanVo.getEndTime();
        BigDecimal priceRate = sysProjectBeanVo.getPriceRate();
        Integer tokenDecimal = sysProjectBeanVo.getTokenDecimal();
        BigDecimal soldAmount = sysProjectBeanVo.getSoldAmount();
        int isAvailable = sysProjectBeanVo.getIsAvailable();
        int projectStatus = sysProjectBeanVo.getProjectStatus();
        Timestamp createTime = sysProjectBeanVo.getCreateTime();
        Timestamp updateTime = sysProjectBeanVo.getUpdateTime();
        Map<String, String> websites = sysProjectBeanVo.getWebsites();
        Map<String, ProjectDescriptionBean> descriptions = sysProjectBeanVo.getDescriptions();
        ProjectDescriptionBean descriptionBeanEn = descriptions.get("en");
        ProjectDescriptionBean descriptionBeanCn = descriptions.get("cn");
        String projectName = descriptionBeanEn.getProjectName();
        String projectNameCn = descriptionBeanCn.getProjectName();
        String projectContent = descriptionBeanEn.getProjectContent();
        String projectInstructionCn = descriptionBeanCn.getProjectInstruction();
        String projectInstruction = descriptionBeanEn.getProjectInstruction();
        String projectContentCn = descriptionBeanCn.getProjectContent();
        String whitePaperLink = descriptionBeanEn.getWhitePaperLink();
        String whitePaperLinkCn = descriptionBeanCn.getWhitePaperLink();
        int row = 1;
        int column = 1;
        sheet.getRow(row++).createCell(column).setCellValue(projectGid);
        sheet.getRow(row++).createCell(column).setCellValue(projectToken);
        sheet.getRow(row++).createCell(column).setCellValue(projectName);
        sheet.getRow(row++).createCell(column).setCellValue(StringUtils.isEmpty(projectNameCn) ? "-" : projectNameCn);
        sheet.getRow(row++).createCell(column).setCellValue(tokenAddress);
        sheet.getRow(row++).createCell(column).setCellValue(platformAddress);
        sheet.getRow(row++).createCell(column).setCellValue(projectAddress);
        sheet.getRow(row++).createCell(column).setCellValue(tokenDecimal.toString());
        sheet.getRow(row++).createCell(column).setCellValue(minPurchaseAmount.stripTrailingZeros().toPlainString());
        sheet.getRow(row++).createCell(column).setCellValue(maxPurchaseAmount.stripTrailingZeros().toPlainString());
        sheet.getRow(row++).createCell(column).setCellValue(priceRate.stripTrailingZeros().toPlainString());
        sheet.getRow(row++).createCell(column).setCellValue(softCap.stripTrailingZeros().toPlainString());
        sheet.getRow(row++).createCell(column).setCellValue(hardCap.stripTrailingZeros().toPlainString());
        sheet.getRow(row++).createCell(column).setCellValue(soldAmount.stripTrailingZeros().toPlainString());
        sheet.getRow(row++).createCell(column).setCellValue(EnumProjectStatus.get(projectStatus).getDes());
        sheet.getRow(row++).createCell(column).setCellValue(startTime.toLocalDateTime().toString());
        sheet.getRow(row++).createCell(column).setCellValue(endTime.toLocalDateTime().toString());
        sheet.getRow(row++).createCell(column).setCellValue(projectInstruction);
        sheet.getRow(row++).createCell(column).setCellValue(StringUtils.isEmpty(projectInstructionCn) ? "-" : projectInstructionCn);
        sheet.getRow(row++).createCell(column).setCellValue(projectContent);
        sheet.getRow(row++).createCell(column).setCellValue(StringUtils.isEmpty(projectContentCn) ? "-" : projectContentCn);
        sheet.getRow(row++).createCell(column).setCellValue(whitePaperLink);
        sheet.getRow(row++).createCell(column).setCellValue(StringUtils.isEmpty(whitePaperLinkCn) ? "-" : whitePaperLinkCn);
        sheet.getRow(row++).createCell(column).setCellValue(projectLogoLink);
        Iterator<Map.Entry<String, String>> webs = websites.entrySet().iterator();


        while (webs.hasNext()) {
            Map.Entry<String, String> next = webs.next();
            sheet.createRow(row);
            sheet.getRow(row).createCell(column - 1).setCellValue(next.getKey());
            sheet.getRow(row++).createCell(column).setCellValue(next.getValue());
        }
        sheet.createRow(row);
        sheet.getRow(row).createCell(column - 1).setCellValue("创建时间");
        sheet.getRow(row++).createCell(column).setCellValue(createTime.toLocalDateTime().toString());
        sheet.createRow(row);
        sheet.getRow(row).createCell(column - 1).setCellValue("最新修改");
        sheet.getRow(row++).createCell(column).setCellValue(updateTime.toLocalDateTime().toString());

        for (int i = 2; i < row; i++) {
            sheet.getRow(i).getCell(column).setCellStyle(cellStyleLeft[0]);
        }

    }

    /**
     * 组装统计信息表
     */
    private void assembleStatisticInfo(HSSFSheet sheet, ProjectSummaryBean projectSummaryBean, PageInfo<ProjectStatisticBean> dailyInfoList, HSSFCellStyle... cellStyleLeft) {
        if (projectSummaryBean == null || dailyInfoList == null) {
            return;
        }
        List<ProjectStatisticBean> list = dailyInfoList.getList();
        ProjectStatisticBean projectStatisticBean = ProjectStatisticBean.newInstance();
        BeanUtils.copyProperties(projectSummaryBean, projectStatisticBean);
        list.add(0, projectStatisticBean);
        int size = list.size();
        int column = 0;
        for (int row = 1; row < size + 1; row++) {
            sheet.createRow(row);
            ProjectStatisticBean bean = list.get(row - 1);
            Date currentDay = bean.getCurrentDay();
            BigDecimal getEthAmount = bean.getGetEthAmount();
            BigDecimal actualGetEthAmount = bean.getActualGetEthAmount();
            BigDecimal payTokenAmount = bean.getPayTokenAmount();
            BigDecimal actualPayTokenAmount = bean.getActualPayTokenAmount();
            Integer userCount = bean.getUserCount();
            Integer actualUserCount = bean.getActualUserCount();
            Integer txCount = bean.getTxCount();
            Integer actualTxCount = bean.getActualTxCount();
            LocalDate localDate = null;
            if (row != 1) {
                Instant instant = currentDay.toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                localDate = instant.atZone(zoneId).toLocalDate();
            }
            sheet.getRow(row).createCell(column++).setCellValue(localDate == null ? "汇总" : localDate.toString());
            sheet.getRow(row).createCell(column++).setCellValue(getEthAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(actualGetEthAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(payTokenAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(actualPayTokenAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(userCount);
            sheet.getRow(row).createCell(column++).setCellValue(actualUserCount);
            sheet.getRow(row).createCell(column++).setCellValue(txCount);
            sheet.getRow(row).createCell(column++).setCellValue(actualTxCount);
            for (int i = 0; i < column - 2; i++) {
                sheet.getRow(row).getCell(i).setCellStyle(cellStyleLeft[2]);
            }
            column = 0;
        }
    }

    /**
     * 组装统计信息表
     */
    private void assembleChannelStatisticInfo(HSSFSheet sheet, ProjectSummaryBean projectSummaryBean, PageInfo<ProjectStatisticBean> dailyInfoList, HSSFCellStyle... cellStyleLeft) {
        if (projectSummaryBean == null || dailyInfoList == null) {
            return;
        }
        List<ProjectStatisticBean> list = dailyInfoList.getList();
        ProjectStatisticBean projectStatisticBean = ProjectStatisticBean.newInstance();
        BeanUtils.copyProperties(projectSummaryBean, projectStatisticBean);
        list.add(0, projectStatisticBean);
        int size = list.size();
        int column = 1;
        for (int row = 1; row < size + 1; row++) {
            sheet.createRow(row);
            ProjectStatisticBean bean = list.get(row - 1);
            BigDecimal getEthAmount = bean.getGetEthAmount();
            BigDecimal actualGetEthAmount = bean.getActualGetEthAmount();
            BigDecimal payTokenAmount = bean.getPayTokenAmount();
            BigDecimal actualPayTokenAmount = bean.getActualPayTokenAmount();
            Integer userCount = bean.getUserCount();
            Integer actualUserCount = bean.getActualUserCount();
            Integer txCount = bean.getTxCount();
            Integer actualTxCount = bean.getActualTxCount();
            String channel = bean.getChannel();
            if (row != 1) {
                sheet.getRow(row).createCell(0).setCellValue(channel);
            }
            SysChannelBean sysChannelBean = channelService.get(channel);
            sheet.getRow(row).createCell(column++).setCellValue(row == 1 ? "汇总" : sysChannelBean == null ? "" : sysChannelBean.getName());
            sheet.getRow(row).createCell(column++).setCellValue(getEthAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(actualGetEthAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(payTokenAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(actualPayTokenAmount.stripTrailingZeros().toPlainString());
            sheet.getRow(row).createCell(column++).setCellValue(userCount);
            sheet.getRow(row).createCell(column++).setCellValue(actualUserCount);
            sheet.getRow(row).createCell(column++).setCellValue(txCount);
            sheet.getRow(row).createCell(column++).setCellValue(actualTxCount);
            for (int i = 0; i < column - 2; i++) {
                HSSFCell cell = sheet.getRow(row).getCell(i);
                if (cell == null) {
                    cell = sheet.getRow(row).createCell(i);
                }
                cell.setCellStyle(cellStyleLeft[2]);
            }
            column = 1;
        }
    }

    /**
     * 组装打币信息表
     */
    private void assembleUserTxInfo(HSSFSheet sheet, PageInfo<RecordUserTxBean> txList, HSSFCellStyle... cellStyleLeft) {
        if (txList == null) {
            return;
        }
        List<RecordUserTxBean> list = txList.getList();
        int row = 1;
        for (RecordUserTxBean p : list) {
            String userEmail = p.getUserEmail();
            String channel = p.getChannel();
            SysChannelBean sysChannelBean = channelService.get(channel);
            String channelName = sysChannelBean == null ? "" : sysChannelBean.getName();
            Long id = p.getId();
            String payTx = p.getPayTx();
            Timestamp createTime = p.getCreateTime();
            Timestamp actualTxTime = p.getActualTxTime();
            String payEthAddress = p.getPayEthAddress();
            String actualSendingAddress = p.getActualSendingAddress();
            String platformAddress = p.getPlatformAddress();
            String actualReceivingAddress = p.getActualReceivingAddress();
            BigDecimal payAmount = p.getPayAmount();
            BigDecimal actualPayAmount = p.getActualPayAmount();
            Integer userTxStatus = p.getUserTxStatus();
            Integer platformTxStatus = p.getPlatformTxStatus();
            HSSFRow row_ = sheet.createRow(row);
            int column = 0;
            row_.createCell(column++).setCellValue(row);
            row_.createCell(column++).setCellValue(userEmail);
            row_.createCell(column++).setCellValue(channel);
            row_.createCell(column++).setCellValue(channelName);
            row_.createCell(column++).setCellValue(id + 10000);
            row_.createCell(column++).setCellValue(payTx);
            row_.createCell(column++).setCellValue(createTime.toLocalDateTime().toString());
            int year = actualTxTime.toLocalDateTime().getYear();
            row_.createCell(column++).setCellValue(year == 2000 ? "" : actualTxTime.toLocalDateTime().toString());
            row_.createCell(column++).setCellValue(payEthAddress);
            row_.createCell(column++).setCellValue(actualSendingAddress);
            row_.createCell(column++).setCellValue(platformAddress);
            row_.createCell(column++).setCellValue(actualReceivingAddress);
            row_.createCell(column++).setCellValue(payAmount.stripTrailingZeros().toPlainString());
            row_.createCell(column++).setCellValue(actualPayAmount.stripTrailingZeros().toPlainString());
            row_.createCell(column++).setCellValue(EnumUserTxStatus.getByOrder(userTxStatus).getDes());
            row_.createCell(column).setCellValue(EnumDistrubiteStatus.get(platformTxStatus).getDes());
            row++;
        }
    }


    /**
     * 组装交易状态表
     */
    private void assembleTxStatus(HSSFSheet sheet, PageInfo<DistributionStatusVo> platformStatusCount, HSSFCellStyle... cellStyleLeft) {
        int column = 0;
        List<DistributionStatusVo> list = platformStatusCount.getList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            DistributionStatusVo distributionStatusVo = list.get(i);
            Integer order = distributionStatusVo.getOrder();
            EnumUserTxStatus orderStatus = EnumUserTxStatus.getByOrder(order);
            sheet.getRow(i + 1).createCell(column).setCellValue(i + 1);
            sheet.getRow(i + 1).createCell(column + 1).setCellValue(orderStatus.getDes());
            sheet.getRow(i + 1).createCell(column + 2).setCellValue(distributionStatusVo.getCount());
        }
    }


    /**
     * 组装打币表
     */
    private void assembleDistributeInfo(HSSFSheet sheet, PageInfo<DistributionStatusVo> platformStatusCount, HSSFCellStyle... cellStyleLeft) {
        int column = 2;
        List<DistributionStatusVo> list = platformStatusCount.getList();
        int[] ints = new int[10];
        list.forEach(p -> {
            Integer platformTxStatus = p.getPlatformTxStatus();
            Integer count = p.getCount();
            LinkedList<DistributionStatusVo> child = p.getChild();
            if (count != null) {
                ints[platformTxStatus] = count;
            }
        });
        int row = 1;
        int i1 = ints[EnumDistrubiteStatus.Status0.getStatus()];
        int i2 = ints[EnumDistrubiteStatus.Status1.getStatus()];
        int i3 = ints[EnumDistrubiteStatus.Status2.getStatus()];
        int i4 = ints[EnumDistrubiteStatus.Status3.getStatus()];
        sheet.getRow(row++).createCell(column).setCellValue(i1);
        sheet.getRow(row++).createCell(column).setCellValue(i2);
        sheet.getRow(row++).createCell(column).setCellValue(i3);
        sheet.getRow(row).createCell(column).setCellValue(i4);

    }


    /**
     * 加载月账单模板
     *
     * @param templateFilePath
     * @return
     */
    private HSSFWorkbook loadExcelTemplate(String templateFilePath) {

        /** 定位并判断指定的模板文件是否存在 */
        URL templateFileUrl = this.getClass().getClassLoader().getResource(templateFilePath);
        if (templateFileUrl == null) {
            LOG.error("missed excel template file: {}", templateFileUrl);
            return null;
        }
        InputStream templateIn = null;      //用于读取模板文件的输入流;
        HSSFWorkbook book = null;           //由于持有excel文件数据的workbook;
        /** 加载模板文件 */
        try {
            templateIn = templateFileUrl.openStream();
            book = new HSSFWorkbook(templateIn);
        } catch (IOException e) {
            LOG.error("loading excel template failed", e);
            return null;
        } finally {
            if (templateIn != null) {
                try {
                    templateIn.close();
                } catch (IOException e) {
                }
            }
        }
        return book;
    }
}
