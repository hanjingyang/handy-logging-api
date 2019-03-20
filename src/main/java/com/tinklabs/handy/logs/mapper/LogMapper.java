package com.tinklabs.handy.logs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tinklabs.handy.logs.bean.DeviceChangeLog;
import com.tinklabs.handy.logs.bean.DeviceOtaStatus;
import com.tinklabs.handy.logs.bean.DeviceOtaStatusContents;
import com.tinklabs.handy.logs.bean.DialerCallLog;
import com.tinklabs.handy.logs.bean.EmailQueueLog;
import com.tinklabs.handy.logs.bean.ImportLog;
import com.tinklabs.handy.logs.bean.InvalidBarcodes;

@Mapper
public interface LogMapper {

    public int addDeviceChangeLog(@Param("list") List<DeviceChangeLog> logs);

    public int addEmailQueueLog(@Param("list") List<EmailQueueLog> logs);

    public int addInvaldBarcodeLog(@Param("list") List<InvalidBarcodes> logs);

    public int addDeviceOtaStatusLog(@Param("list") List<DeviceOtaStatus> logs);

    public int addDeviceOtaStatusContentLog(@Param("list") List<DeviceOtaStatusContents> logs);

    public int addDialerCallLog(@Param("list") List<DialerCallLog> logs);

    public int addImportLog(@Param("list") List<ImportLog> logs);
}
